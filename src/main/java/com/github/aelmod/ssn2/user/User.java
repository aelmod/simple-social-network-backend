package com.github.aelmod.ssn2.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.country.Country;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimalView.class)
    private Integer id;

    @JsonView(MinimalView.class)
    private String name;

    @Column(nullable = false)
    @JsonView(MinimalView.class)
    private String username;

    @Column(nullable = false)
    private String password;

    @JsonView(AllPrimitivesView.class)
    private Date birthday;

    @JsonView(AllPrimitivesView.class)
    private String email;

    @JsonView(AllPrimitivesView.class)
    private int phone;

    @JsonView(FullView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @JsonView(AllPrimitivesView.class)
    private int cityId;

    @JsonView(AllPrimitivesView.class)
    private String address;

    @ManyToMany
    @JoinTable(name = "friends",
            joinColumns = {@JoinColumn(name = "user1_id")},
            inverseJoinColumns = {@JoinColumn(name = "user2_id")}
    )

    private Set<User> friends = new HashSet<>();

    public interface MinimalView {}

    public interface AllPrimitivesView extends MinimalView {}

    public interface FullView extends AllPrimitivesView, Country.MinimumView {}
}
