package com.example.user;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserSpecification {
    private Optional<String> oName = Optional.empty();
    private Optional<Integer> oCountryId = Optional.empty();

    public UserSpecification() {
    }

    public UserSpecification(Optional<String> oName, Optional<Integer> oCountryId) {
        this.oName = oName;
        this.oCountryId = oCountryId;
    }

    public void setName(String name) {
        this.oName = Optional.of(name);
    }

    public void setCountryId(Integer oCountryId) {
        this.oCountryId = Optional.of(oCountryId);
    }

    public CriteriaQuery<User> toCriteria(CriteriaBuilder cb){
        CriteriaQuery<User> userCriteriaQuery = cb.createQuery(User.class);
        Root<User> userEntity = userCriteriaQuery.from(User.class);
        userCriteriaQuery.select(userEntity);

        List<Predicate> predicateList = new ArrayList<>();

        oName.ifPresent(name -> predicateList.add(cb.equal(userEntity.get("name"), name)));
        oCountryId.ifPresent(countryId -> predicateList.add(cb.equal(userEntity.get("country").get("id"), countryId)));


        Predicate mainPredicate = cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

        userCriteriaQuery.where(mainPredicate);

        return userCriteriaQuery;
    }
}
