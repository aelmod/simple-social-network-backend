package com.github.aelmod.ssn2.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
    private final JwtAuthHelper jwtAuthHelper;

    @Autowired
    public WebSecurityConfig(UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider, JwtAuthHelper jwtAuthHelper) {
        this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
        this.jwtAuthHelper = jwtAuthHelper;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/js/**")
                .antMatchers(HttpMethod.POST, "/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().disable()
                .logout()
                .permitAll()
                .and()
                .addFilterBefore(new Filter() {
                    @Override
                    public void init(FilterConfig filterConfig) throws ServletException {

                    }

                    @Override
                    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
                        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

                        String token = httpServletRequest.getHeader("X-Token");
//
                        if (Objects.isNull(token)) {
                            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            return;
                        }

                        try {
                            jwtAuthHelper.verifyToken(token).getToken();
                        }catch (JWTVerificationException e) {
                            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            return;
                        }

                        SecurityContextHolder.getContext().setAuthentication(new SsnJwtAuthentication(token));
                        filterChain.doFilter(servletRequest, servletResponse);
                    }

                    @Override
                    public void destroy() {

                    }
                }, UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(usernamePasswordAuthenticationProvider);
    }
}
