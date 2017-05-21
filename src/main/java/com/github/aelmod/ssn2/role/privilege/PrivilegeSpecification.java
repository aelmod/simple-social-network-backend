package com.github.aelmod.ssn2.role.privilege;

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
public class PrivilegeSpecification implements EntitySpecification<Privilege> {

    private Optional<String> oName = Optional.empty();

    @Override
    public CriteriaQuery<Privilege> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Privilege> privilegeQuery = cb.createQuery(Privilege.class);
        Root<Privilege> privilegeEntity = privilegeQuery.from(Privilege.class);

        List<Predicate> predicateList = new ArrayList<>();
        oName.ifPresent(name -> predicateList.add(cb.equal(privilegeEntity.get("name"), name)));
        Predicate predicate = cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        privilegeQuery.where(predicate);
        return privilegeQuery;
    }
}
