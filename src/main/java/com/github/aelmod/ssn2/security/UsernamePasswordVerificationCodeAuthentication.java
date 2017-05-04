package com.github.aelmod.ssn2.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.el.MethodNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

public class UsernamePasswordVerificationCodeAuthentication implements Authentication {


    private String login;

    private String password;

    private String verificationCode;

    public UsernamePasswordVerificationCodeAuthentication(String login, String password, String verificationCode) {
        this.login = login;
        this.password = password;
        this.verificationCode = verificationCode;
    }

    public UsernamePasswordVerificationCodeAuthentication() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return login;
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

    public String getVerificationCode() {
        return verificationCode;
    }
}
