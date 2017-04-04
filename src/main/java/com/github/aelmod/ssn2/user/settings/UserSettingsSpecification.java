package com.github.aelmod.ssn2.user.settings;

import com.github.aelmod.ssn2.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSettingsSpecification implements EntitySpecification<UserSettings> {

    private Integer userId;

    public UserSettingsSpecification(Integer userId) {
        this.userId = userId;
    }

    @Override
    public CriteriaQuery<UserSettings> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<UserSettings> userSettingsCriteriaQuery = cb.createQuery(UserSettings.class);
        Root<UserSettings> userSettingsEntity = userSettingsCriteriaQuery.from(UserSettings.class);

        Predicate userPredicate = cb.equal(userSettingsEntity.get("user"), userId);

        userSettingsCriteriaQuery.where(userPredicate);

        return userSettingsCriteriaQuery.select(userSettingsEntity);
    }
}
