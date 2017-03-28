package com.github.aelmod.ssn2.city;

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
@Table(name = "cities")
public class City implements Serializable {

    public City(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimumView.class)
    private Integer id;

    @JsonView(MinimumView.class)
    private String name;

    @OneToMany(mappedBy = "city")
    @JsonView(WithUsers.class)
    private Set<User> users = new HashSet<>();

    public interface WithUsers extends MinimumView, User.MinimalView {}

    public interface MinimumView {}
}
