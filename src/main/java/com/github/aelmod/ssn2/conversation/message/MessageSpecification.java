package com.github.aelmod.ssn2.conversation.message;

import com.github.aelmod.ssn2.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class MessageSpecification implements EntitySpecification<Message> {

    private Integer conversationId;

    public MessageSpecification(Integer conversationId) {
        this.conversationId = conversationId;
    }

    @Override
    public CriteriaQuery<Message> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Message> messageQuery = cb.createQuery(Message.class);
        Root<Message> messageEntity = messageQuery.from(Message.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(messageEntity.get("conversation"), conversationId));

        Predicate predicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));

        messageQuery.where(predicate);

        return messageQuery.select(messageEntity);
    }
}
