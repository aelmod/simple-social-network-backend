package com.github.aelmod.ssn2.chat.message;

import com.github.aelmod.ssn2.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MessageSpecification implements EntitySpecification<Message> {

    @Override
    public CriteriaQuery<Message> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Message> messageQuery = cb.createQuery(Message.class);
        Root<Message> messageEntity = messageQuery.from(Message.class);
        return messageQuery.select(messageEntity);
    }
}
