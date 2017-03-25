package com.github.aelmod.ssn2.commentary;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.microblog.Microblog;
import com.github.aelmod.ssn2.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "commentaries")
public class Commentary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonView(MinimalView.class)
    private String text;
    @JsonView(MinimalView.class)
    private Date creationTime;
    @JsonView(MinimalView.class)
    @OneToOne(fetch = FetchType.LAZY)
    private User repliedToUser;
    @JsonView(WithUser.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "microblog_id")
    private Microblog microblog;

    public interface MinimalView {}
    public interface WithUser extends MinimalView {}
}
