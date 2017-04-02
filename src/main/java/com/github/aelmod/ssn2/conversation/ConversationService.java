package com.github.aelmod.ssn2.conversation;

import com.github.aelmod.ssn2.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Transactional
    public void create(User conversationInitializer, List<Integer> invitedUserIds) {
        Conversation conversation = new Conversation();
        List<User> users = conversation.getUsers();
        users.add(conversationInitializer);
        for (Integer invitedUserId : invitedUserIds) {
            User invitedUser = new User();
            invitedUser.setId(invitedUserId);
            users.add(invitedUser);
        }
        conversationRepository.persist(conversation);
    }

    @Transactional(readOnly = true)
    public Conversation getByPk(Integer id) {
        return conversationRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Conversation> getBy(ConversationSpecification conversationSpecification) {
        return conversationRepository.findBy(conversationSpecification);
    }

    @Transactional(readOnly = true)
    public List<Conversation> getAll(User user) {
        return user.getConversations();
    }
}
