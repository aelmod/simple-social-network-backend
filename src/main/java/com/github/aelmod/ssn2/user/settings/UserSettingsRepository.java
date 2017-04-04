package com.github.aelmod.ssn2.user.settings;

import com.github.aelmod.ssn2.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserSettingsRepository extends BaseRepository<UserSettings, Integer> {

    public UserSettingsRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
