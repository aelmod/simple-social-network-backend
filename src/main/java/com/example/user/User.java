package com.example.user;

import com.example.country.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimalView.class)
    private int id;

    @JsonView(MinimalView.class)
    private String name;

    @JsonView(MinimalView.class)
    private String nickname;

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

    public interface MinimalView {}
    public interface AllPrimitivesView extends MinimalView {}
    public interface FullView extends AllPrimitivesView, Country.MinimumView {}
}
