package com.github.aelmod.ssn2.chat;

import com.github.aelmod.ssn2.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ConversationRepository extends BaseRepository<Conversation, Integer> {

    public ConversationRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
