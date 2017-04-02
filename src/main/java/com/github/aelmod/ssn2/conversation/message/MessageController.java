package com.github.aelmod.ssn2.conversation.message;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Message> getMessages(@PathVariable Integer conversationId) {
        return messageService.getBy(new MessageSpecification(conversationId));
    }

    @PostMapping
    public void add(@CurrentUser User user, @RequestBody MessageForm messageForm, @PathVariable Integer conversationId) {
        messageForm.setConversationId(conversationId);
        messageForm.setUser(user);
        messageService.addMessage(messageForm.toMessage());
    }
}
