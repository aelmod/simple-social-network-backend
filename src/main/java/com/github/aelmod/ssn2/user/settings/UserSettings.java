package com.github.aelmod.ssn2.user.settings;

import com.github.aelmod.ssn2.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class UserSettings implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private User user;

    public enum CommentaryPrivacy {
        ONLY_FRIENDS, ALL_USERS
    }

    @Enumerated(EnumType.STRING)
    private CommentaryPrivacy privacySettings;
}
