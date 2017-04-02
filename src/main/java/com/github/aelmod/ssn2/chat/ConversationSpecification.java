package com.github.aelmod.ssn2.chat;

import com.github.aelmod.ssn2.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ConversationSpecification implements EntitySpecification<Conversation> {

    private Integer userId;

    public ConversationSpecification(Integer userId) {
        this.userId = userId;
    }

    @Override
    public CriteriaQuery<Conversation> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Conversation> conversationQuery = cb.createQuery(Conversation.class);
        Root<Conversation> conversationEntity = conversationQuery.from(Conversation.class);

        List<Predicate> predicateList = new ArrayList<>();

        predicateList.add(cb.equal(conversationEntity.get("id"), userId));

        cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

        return conversationQuery.select(conversationEntity);
    }
}
