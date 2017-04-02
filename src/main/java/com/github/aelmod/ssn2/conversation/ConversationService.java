package com.github.aelmod.ssn2.conversation;

import com.github.aelmod.ssn2.user.User;
import com.github.aelmod.ssn2.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;

    private final UserService userService;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository, UserService userService) {
        this.conversationRepository = conversationRepository;
        this.userService = userService;
    }

    @Transactional
    public void startConversations(User conversationInitializer, List<Integer> invitedUserIds) {
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

    @Transactional
    public Conversation getByPk(Integer id) {
        return conversationRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public List<Conversation> getBy(ConversationSpecification conversationSpecification) {
        return conversationRepository.findBy(conversationSpecification);
    }

    @Transactional
    public List<Conversation> getAll(User user) {
        return user.getConversations();
    }
}
