package com.github.aelmod.ssn2.user;

import javax.persistence.PersistenceException;

public class UserCommonException extends PersistenceException {

    public UserCommonException() {}

    public UserCommonException(String message) {
        super(message);
    }
}
