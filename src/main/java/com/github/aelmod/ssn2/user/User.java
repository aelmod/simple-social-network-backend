package com.github.aelmod.ssn2.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.city.City;
import com.github.aelmod.ssn2.country.Country;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ToString
@NoArgsConstructor
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

    @JsonFormat(pattern = "dd.MM.yyyy")
    @JsonView(AllPrimitivesView.class)
    private Date birthday;

    @JsonView(AllPrimitivesView.class)
    private String email;

    @JsonView(AllPrimitivesView.class)
    private String phone;

    @JsonView(FullView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @JsonView(FullView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @JsonView(AllPrimitivesView.class)
    private String address;

    private String secret;

    public User(String name, String username, String password, Date birthday, String email, String phone,
                Country country, City city, String address) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.city = city;
        this.address = address;
    }


    public interface MinimalView {}

    public interface AllPrimitivesView extends MinimalView {}

    public interface FullView extends AllPrimitivesView, Country.MinimumView, City.MinimumView {}
}
