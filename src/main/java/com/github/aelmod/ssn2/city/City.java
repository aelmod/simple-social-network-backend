package com.github.aelmod.ssn2.city;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.country.Country;
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

    @ManyToOne
    @JsonView(FullView.class)
    private Country country;

    @OneToMany(mappedBy = "city")
    @JsonView(FullView.class)
    private Set<User> users = new HashSet<>();

    public interface FullView extends MinimumView, User.MinimalView {}

    public interface MinimumView {}
}
