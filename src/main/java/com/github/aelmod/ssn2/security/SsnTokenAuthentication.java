package com.github.aelmod.ssn2.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.el.MethodNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by aelmod on 18.03.17.
 */
public class SsnTokenAuthentication implements Authentication {
    private String token;
    private Integer userId;

    public SsnTokenAuthentication(String token, Integer userId) {
        this.token = token;
        this.userId = userId;
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
        return userId;
    }

    @Override
    public Object getPrincipal() {
        return userId;
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

    public String getToken() {
        return token;
    }

    public Integer getUserId() {
        return userId;
    }
}
