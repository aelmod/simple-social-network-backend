package com.github.aelmod.ssn2.conversation.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional(readOnly = true)
    public List<Message> getBy(MessageSpecification messageSpecification) {
        return messageRepository.findBy(messageSpecification);
    }

    @Transactional
    public void addMessage(Message message) {
        messageRepository.persist(message);
    }
}
