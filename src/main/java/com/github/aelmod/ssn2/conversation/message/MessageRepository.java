package com.github.aelmod.ssn2.conversation.message;

import com.github.aelmod.ssn2.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MessageRepository extends BaseRepository<Message, Integer> {

    public MessageRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
