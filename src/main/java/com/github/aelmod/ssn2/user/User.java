package com.github.aelmod.ssn2.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.city.City;
import com.github.aelmod.ssn2.conversation.Conversation;
import com.github.aelmod.ssn2.conversation.message.Message;
import com.github.aelmod.ssn2.country.Country;
import com.github.aelmod.ssn2.microblog.Microblog;
import com.github.aelmod.ssn2.microblog.commentary.Commentary;
import com.github.aelmod.ssn2.user.settings.UserSettings;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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

    @OneToMany(mappedBy = "user")
    private List<Message> messages = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "conversation_user_map",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "conversation_id")}
    )
    private List<Conversation> conversations = new ArrayList<>();

    @JsonView(FullView.class)
    @OneToMany(mappedBy = "user")
    private List<Microblog> microblogs = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Commentary> commentaries = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "friends",
            joinColumns = {@JoinColumn(name = "user1_id")},
            inverseJoinColumns = {@JoinColumn(name = "user2_id")}
    )
    private Set<User> friends = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "friendship_requests_bucket",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "requested_friendship_user_id")}
    )
    private Set<User> friendRequestsBucket = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "ignore_list",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "ignored_user_id")}
    )
    private Set<User> ignoreList = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private UserSettings userSettings;

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

    public interface FullView extends AllPrimitivesView, Country.MinimumView, City.MinimumView, Microblog.MinimalView {}
}
