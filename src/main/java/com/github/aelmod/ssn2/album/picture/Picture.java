package com.github.aelmod.ssn2.album.picture;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(AllPrimitivesView.class)
    private Integer id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonView(AllPrimitivesView.class)
    private User user;

    @JsonView(AllPrimitivesView.class)
    private String name;

    @Transient
    @JsonView(AllPrimitivesView.class)
    private String fullPath;

    @JsonView(AllPrimitivesView.class)
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date timeOfAddition;

    public interface AllPrimitivesView extends User.MinimalView {}
}
