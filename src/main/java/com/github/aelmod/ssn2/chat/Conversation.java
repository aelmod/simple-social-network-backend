package com.github.aelmod.ssn2.chat;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.chat.message.Message;
import com.github.aelmod.ssn2.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "conversations")
public class Conversation implements Serializable {

    @JsonView(MinimalView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(FullView.class)
    @OneToMany(mappedBy = "conversation")
    private List<Message> messages = new ArrayList<>();

    @JsonView(MinimalView.class)
    @ManyToMany
    @JoinTable(name = "user_conversation_map",
            joinColumns = {@JoinColumn(name = "conversation_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> users = new HashSet<>();

    public interface MinimalView extends User.MinimalView {}

    public interface FullView extends MinimalView, Message.MinimalView, User.MinimalView {}
}
