package com.github.aelmod.ssn2.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.el.MethodNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

public class SsnJwtAuthentication implements Authentication {
    private String token;

    public SsnJwtAuthentication(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
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
