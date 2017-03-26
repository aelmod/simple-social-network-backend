package com.github.aelmod.ssn2.commentary;

import com.github.aelmod.ssn2.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CommentarySpecification implements EntitySpecification<Commentary> {
    @Override
    public CriteriaQuery<Commentary> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Commentary> commentaryQuery = cb.createQuery(Commentary.class);
        Root<Commentary> from = commentaryQuery.from(Commentary.class);
        commentaryQuery.select(from);
        return commentaryQuery;
    }
}
