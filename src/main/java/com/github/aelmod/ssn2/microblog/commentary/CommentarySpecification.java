package com.github.aelmod.ssn2.microblog.commentary;

import com.github.aelmod.ssn2.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CommentarySpecification implements EntitySpecification<Commentary> {

    private Integer microblogId;

    public void setMicroblogId(Integer microblogId) {
        this.microblogId = microblogId;
    }

    @Override
    public CriteriaQuery<Commentary> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Commentary> commentaryQuery = cb.createQuery(Commentary.class);
        Root<Commentary> commentaryEntity = commentaryQuery.from(Commentary.class);
        commentaryQuery.select(commentaryEntity);

        Predicate mainPredicate = cb.and(cb.equal(commentaryEntity.get("microblog").get("id"), microblogId));
        commentaryQuery.where(mainPredicate);
        return commentaryQuery;
    }
}
