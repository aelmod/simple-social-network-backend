package com.github.aelmod.ssn2.security.google2fa;

import javax.persistence.PersistenceException;

/**
 * Exception modeling when something went wrong during verifying the code in second step.
 */
public class VerificationCodeException extends PersistenceException {

    public VerificationCodeException() {}

    public VerificationCodeException(String message) {
        super(message);
    }
}
