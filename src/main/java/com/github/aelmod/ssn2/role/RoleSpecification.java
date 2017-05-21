package com.github.aelmod.ssn2.role;

import com.github.aelmod.ssn2.EntitySpecification;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
public class RoleSpecification implements EntitySpecification<Role> {

    private Optional<String> oName = Optional.empty();

    @Override
    public CriteriaQuery<Role> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Role> roleQuery = cb.createQuery(Role.class);
        Root<Role> roleEntity = roleQuery.from(Role.class);

        List<Predicate> predicateList = new ArrayList<>();
        oName.ifPresent(name -> predicateList.add(cb.equal(roleEntity.get("name"), name)));
        Predicate predicate = cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        roleQuery.where(predicate);
        return roleQuery;
    }
}
