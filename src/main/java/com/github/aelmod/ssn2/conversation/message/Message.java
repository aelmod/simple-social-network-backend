package com.github.aelmod.ssn2.conversation.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.conversation.Conversation;
import com.github.aelmod.ssn2.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {

    @JsonView(MinimalView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(FullView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonView(FullView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @JsonView(MinimalView.class)
    private String message;

    @JsonView(MinimalView.class)
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date creationTime;

    public interface MinimalView {}

    public interface FullView extends MinimalView, User.MinimalView, Conversation.MinimalView {}
}
