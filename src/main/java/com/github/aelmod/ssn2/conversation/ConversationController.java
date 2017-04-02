package com.github.aelmod.ssn2.conversation;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    @Autowired
    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping
    public void startConversation(@CurrentUser User conversationInitializer, @RequestBody List<Integer> invitedUserIds) {
        conversationService.startConversation(conversationInitializer, invitedUserIds);
    }

    @JsonView(Conversation.MinimalView.class)
    @GetMapping
    public List<Conversation> getAllUserConversation(@CurrentUser User user) {
        return conversationService.getAll(user);
    }
}
