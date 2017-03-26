package com.github.aelmod.ssn2.microblog;

import com.github.aelmod.ssn2.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MicroblogSpecification implements EntitySpecification<Microblog> {

    @Override
    public CriteriaQuery<Microblog> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Microblog> microblogQuery = cb.createQuery(Microblog.class);
        Root<Microblog> from = microblogQuery.from(Microblog.class);
        return microblogQuery.select(from);
    }
}
