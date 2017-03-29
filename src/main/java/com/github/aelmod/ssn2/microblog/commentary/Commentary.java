package com.github.aelmod.ssn2.microblog.commentary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.microblog.Microblog;
import com.github.aelmod.ssn2.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "commentaries")
public class Commentary implements Serializable {

    @JsonView(MinimalView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(MinimalView.class)
    private String text;

    @JsonView(MinimalView.class)
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date creationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "microblog_id")
    private Microblog microblog;

    @JsonView(MinimalView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public interface MinimalView {}

    public interface WithUser extends MinimalView, User.MinimalView {}
}
