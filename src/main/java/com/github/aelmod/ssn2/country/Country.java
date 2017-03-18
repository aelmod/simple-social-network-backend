package com.github.aelmod.ssn2.country;

import com.github.aelmod.ssn2.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "countries")
public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimumView.class)
    private int id;
    @JsonView(MinimumView.class)
    private String name;
    @OneToMany(mappedBy = "country")
    @JsonView(WithUsers.class)
    private Set<User> users = new HashSet<>();

    public interface WithUsers extends MinimumView, User.MinimalView {}

    public interface MinimumView {}
}
