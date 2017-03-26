package com.github.aelmod.ssn2.user;

import com.github.aelmod.ssn2.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserSpecification implements EntitySpecification<User> {

    private Optional<String> oName = Optional.empty();

    private Optional<Integer> oCountryId = Optional.empty();

    private Optional<Integer> oCityId = Optional.empty();

    public void setName(String name) {
        this.oName = Optional.of(name);
    }

    public void setCountryId(Integer oCountryId) {
        this.oCountryId = Optional.of(oCountryId);
    }

    public void setCityId(Integer oCityId) {
        this.oCityId = Optional.of(oCityId);
    }

    @Override
    public CriteriaQuery<User> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<User> userCriteriaQuery = cb.createQuery(User.class);
        Root<User> userEntity = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userEntity);

        List<Predicate> predicateList = new ArrayList<>();

        oName.ifPresent(name -> predicateList.add(cb.equal(userEntity.get("name"), name)));
        oCountryId.ifPresent(countryId -> predicateList.add(cb.equal(userEntity.get("country").get("id"), countryId)));
        oCityId.ifPresent(cityId -> predicateList.add(cb.equal(userEntity.get("city").get("id"), cityId)));
        Predicate mainPredicate = cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

        userCriteriaQuery.where(mainPredicate);

        return userCriteriaQuery;
    }
}
