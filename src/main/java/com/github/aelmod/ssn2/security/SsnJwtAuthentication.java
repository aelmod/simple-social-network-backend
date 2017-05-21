package com.github.aelmod.ssn2.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.el.MethodNotFoundException;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SsnJwtAuthentication implements Authentication {

    private final String token;

    private Collection<? extends GrantedAuthority> authorities;

    public SsnJwtAuthentication(String token) {
        this.token = token;

        DecodedJWT decodedJWT = JwtAuthHelper.verifyToken(token);
        String[] roles = decodedJWT.getClaim(JwtAuthHelper.ROLES).asArray(String.class);
        authorities = Stream.of(roles)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    public String getToken() {
        return token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        throw new MethodNotFoundException();
    }

    @Override
    public Object getDetails() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new MethodNotFoundException();
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
