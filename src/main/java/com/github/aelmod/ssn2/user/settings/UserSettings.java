package com.github.aelmod.ssn2.user.settings;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class UserSettings implements Serializable {

    @JsonView(MinimalView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public enum CommentaryPrivacy {
        ONLY_FRIENDS, ALL_USERS
    }

    @JsonView(MinimalView.class)
    @Enumerated(EnumType.STRING)
    private CommentaryPrivacy commentaryPrivacy;

    public interface MinimalView {}
}
