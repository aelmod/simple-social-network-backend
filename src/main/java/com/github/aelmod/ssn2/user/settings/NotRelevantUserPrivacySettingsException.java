package com.github.aelmod.ssn2.user.settings;

import javax.persistence.PersistenceException;

public class NotRelevantUserPrivacySettingsException extends PersistenceException {

    public NotRelevantUserPrivacySettingsException() {}

    public NotRelevantUserPrivacySettingsException(String message) {
        super(message);
    }
}
