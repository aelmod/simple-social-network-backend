package com.github.aelmod.ssn2.user.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserSettingsService {

    private final UserSettingsRepository userSettingsRepository;

    @Autowired
    public UserSettingsService(UserSettingsRepository userSettingsRepository) {
        this.userSettingsRepository = userSettingsRepository;
    }

    @Transactional
    public UserSettings getUserSettings(UserSettingsSpecification userSettingsSpecification) {
        List<UserSettings> userSettings = userSettingsRepository.findBy(userSettingsSpecification);
        if (userSettings.size() > 1) throw new IllegalStateException();
        if (userSettings.isEmpty()) return new UserSettings();
        return userSettings.get(0);
    }

    @Transactional
    public UserSettings getByPk(Integer id) {
        return userSettingsRepository.findOneByPk(id).orElseThrow(PrivacyException::new);
    }

    @Transactional
    public void updateSettings(UserSettings userSettings) {
        userSettingsRepository.persist(userSettings);
    }
}
