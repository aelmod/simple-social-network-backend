package com.github.aelmod.ssn2.country;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class Country implements Serializable {

    public Country(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimumView.class)
    private Integer id;

    @JsonView(MinimumView.class)
    private String name;

    @JsonView(WithUsers.class)
    @OneToMany(mappedBy = "country")
    private Set<User> users = new HashSet<>();

    public interface WithUsers extends MinimumView, User.MinimalView {}

    public interface MinimumView {}
}
