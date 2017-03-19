package com.github.aelmod.ssn2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
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
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
//    private Map<String, Integer> tokenUserIdMap = new HashMap<>();

    @Autowired
    public WebSecurityConfig(UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider) {
        this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
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
                        String token = httpServletRequest.getParameter("X-Token");
                        Map<String, Integer> tokenUserIdMap = usernamePasswordAuthenticationProvider.getTokenUserIdMap();
                        if (tokenUserIdMap.containsKey(token)) {
                            SecurityContextHolder.getContext().setAuthentication(new SsnTokenAuthentication(token, tokenUserIdMap.get(token)));
                            filterChain.doFilter(servletRequest, servletResponse);
                        }
                        ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_FORBIDDEN);
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
