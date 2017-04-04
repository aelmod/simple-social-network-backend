package com.github.aelmod.ssn2.user;

import javax.persistence.PersistenceException;

public class BadUserBehaviorException extends PersistenceException {

    public BadUserBehaviorException() {}

    public BadUserBehaviorException(String message) {
        super(message);
    }
}
