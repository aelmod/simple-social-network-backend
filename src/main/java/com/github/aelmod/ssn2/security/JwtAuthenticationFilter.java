package com.github.aelmod.ssn2.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtAuthenticationFilter implements Filter {

    private final JwtAuthHelper jwtAuthHelper = new JwtAuthHelper();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String token = httpServletRequest.getHeader("X-Token");

        if (Objects.isNull(token)) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        try {
            jwtAuthHelper.verifyToken(token).getToken();
        } catch (JWTVerificationException e) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(new SsnJwtAuthentication(token));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
