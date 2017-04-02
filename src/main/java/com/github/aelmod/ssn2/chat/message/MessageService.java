package com.github.aelmod.ssn2.chat.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final Map<DeferredResult<List<Message>>, Integer> chatRequests =
            new ConcurrentHashMap<>();

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    private List<Message> getByPk(int index, Integer conversationId) {
        List<Message> messages = messageRepository.findBy(new MessageSpecification(conversationId));
        if (messages.isEmpty()) {
            return Collections.emptyList();
        }
        if (!verifyIndex(index, messages)) return Collections.emptyList();
        return messages.subList(index, messages.size());
    }

    private boolean verifyIndex(int index, List<Message> messages) {
        return (index >= 0) && (index <= messages.size());
    }

    public DeferredResult<List<Message>> getMessagesByOffset(Integer offset, Integer conversationId) {
        if (Objects.isNull(offset)) offset = 0;
        final DeferredResult<List<Message>> deferredResult = new DeferredResult<>(null, Collections.emptyList());
        chatRequests.put(deferredResult, offset);

        deferredResult.onCompletion(() -> chatRequests.remove(deferredResult));

        List<Message> messages = getByPk(offset, conversationId);
        if (!messages.isEmpty()) {
            deferredResult.setResult(messages);
        }
        return deferredResult;
    }

    @Transactional
    public void addMessage(Message message, Integer conversationId) {
        messageRepository.persist(message);
        for (Map.Entry<DeferredResult<List<Message>>, Integer> entry : chatRequests.entrySet()) {
            List<Message> messages = getByPk(entry.getValue(), conversationId);
            entry.getKey().setResult(messages);
        }
    }
}
