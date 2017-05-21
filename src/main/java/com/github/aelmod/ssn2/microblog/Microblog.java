package com.github.aelmod.ssn2.microblog;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.microblog.commentary.Commentary;
import com.github.aelmod.ssn2.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "microblogs")
public class Microblog implements Serializable {

    @JsonView(MinimalView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(MinimalView.class)
    private String text;

    @JsonView(MinimalView.class)
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date creationTime;

    @JsonView(WithUser.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "microblog")
    @JsonView(FullView.class)
    private List<Commentary> commentaries = new ArrayList<>();

    public interface MinimalView {}

    public interface WithUser extends MinimalView, User.MinimalView {}

    public interface FullView extends WithUser, Commentary.MinimalView {}
}
