package com.github.aelmod.ssn2.chat;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    @Autowired
    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

//    @JsonView(Conversation.FullView.class)
//    @GetMapping("{conversationId}")
//    public Conversation getById(@PathVariable int conversationId) {
//        return conversationService.getByPk(conversationId);
//    }

    @JsonView(Conversation.MinimalView.class)
    @GetMapping
    public List<Conversation> getAll(@CurrentUser User user) {
        return conversationService.getBy(new ConversationSpecification(user.getId()));
    }
}
