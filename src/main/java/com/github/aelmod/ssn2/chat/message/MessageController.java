package com.github.aelmod.ssn2.chat.message;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

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
    public DeferredResult<List<Message>> getMessages(@RequestParam(required = false) Integer offset,
                                                     @PathVariable Integer conversationId) {
        return messageService.getMessagesByOffset(offset, conversationId);
    }

    @PostMapping
    public void add(@CurrentUser User user, @RequestBody MessageForm messageForm, @PathVariable Integer conversationId) {
        messageForm.setConversationId(conversationId);
        messageForm.setUser(user);
        messageService.addMessage(messageForm.toMessage(), conversationId);
    }
}
