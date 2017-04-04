package com.github.aelmod.ssn2.user.settings;

import javax.persistence.PersistenceException;

public class PrivacyException extends PersistenceException {

    public PrivacyException() {}

    public PrivacyException(String message) {
        super(message);
    }
}
