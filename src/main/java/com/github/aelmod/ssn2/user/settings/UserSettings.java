package com.github.aelmod.ssn2.user.settings;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum CommentaryPrivacy {
        ONLY_FRIENDS("onlyFriends"), ALL_USERS("allUsers");

        String type;

        CommentaryPrivacy(String type) {
            this.type = type;
        }
    }

    @JsonView(MinimalView.class)
    @Enumerated(EnumType.STRING)
    private CommentaryPrivacy privacySettings;

    public interface MinimalView {}
}
