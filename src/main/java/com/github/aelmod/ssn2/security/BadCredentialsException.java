package com.github.aelmod.ssn2.security;

import javax.persistence.PersistenceException;

public class BadCredentialsException extends PersistenceException {

    public BadCredentialsException() {}

    public BadCredentialsException(String message) {
        super(message);
    }
}
