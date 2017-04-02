package com.github.aelmod.ssn2.conversation.message;

import com.github.aelmod.ssn2.conversation.Conversation;
import com.github.aelmod.ssn2.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class MessageForm {

    @NotNull
    private String messageBody;

    private User user;

    private Integer conversationId;

    private Date creationTime = new Date();

    public Message toMessage() {
        Message message = new Message();
        Conversation conversation = new Conversation();
        conversation.setId(conversationId);
        message.setConversation(conversation);
        message.setMessage(messageBody);
        message.setCreationTime(creationTime);
        message.setUser(user);
        return message;
    }
}
