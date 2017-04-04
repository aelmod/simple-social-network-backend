package com.github.aelmod.ssn2.conversation.message;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.conversation.Conversation;
import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/conversations/{conversationId}/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @JsonView(Message.FullView.class)
    @GetMapping
    public List<Message> getMessages(@CurrentUser User user, @PathVariable Integer conversationId) {
        verifyPresenceUserInConversation(user, conversationId);
        return messageService.getBy(new MessageSpecification(conversationId));
    }

    @PostMapping
    public void add(@CurrentUser User user, @RequestBody @Valid MessageForm messageForm,
                    @PathVariable Integer conversationId) {
        verifyPresenceUserInConversation(user, conversationId);
        messageForm.setConversationId(conversationId);
        messageForm.setUser(user);
        messageService.addMessage(messageForm.toMessage());
    }

    private void verifyPresenceUserInConversation(@CurrentUser User user, @PathVariable Integer conversationId) {
        List<Conversation> currentUserConversations = user.getConversations();
        if (currentUserConversations.size() == 0) throw new EntityNotFoundException();
        user.getConversations().forEach(conversation -> {
            if (!Objects.equals(conversation.getId(), conversationId))
                throw new EntityNotFoundException();
        });
    }
}
