package com.github.aelmod.ssn2.conversation;

import com.github.aelmod.ssn2.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class ConversationSpecification implements EntitySpecification<Conversation> {

    @Override
    public CriteriaQuery<Conversation> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Conversation> conversationQuery = cb.createQuery(Conversation.class);
        Root<Conversation> conversationEntity = conversationQuery.from(Conversation.class);
        return conversationQuery.select(conversationEntity);
    }
}
