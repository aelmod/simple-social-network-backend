package com.github.aelmod.ssn2.chat;

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
    public void startConversations(User conversationInitializer, Integer invitedUserId) {
        Conversation conversation = new Conversation();
        User invitedUser = userService.getByPk(invitedUserId);
        List<User> users = conversation.getUsers();
        users.add(conversationInitializer);
        users.add(invitedUser);
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
