package com.github.aelmod.ssn2.user;

import javax.persistence.PersistenceException;

public class UserAlreadyExistsException extends PersistenceException {
    public UserAlreadyExistsException() {}

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
