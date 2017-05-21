package com.github.aelmod.ssn2.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.album.picture.Picture;
import com.github.aelmod.ssn2.city.City;
import com.github.aelmod.ssn2.conversation.Conversation;
import com.github.aelmod.ssn2.conversation.message.Message;
import com.github.aelmod.ssn2.country.Country;
import com.github.aelmod.ssn2.microblog.Microblog;
import com.github.aelmod.ssn2.microblog.commentary.Commentary;
import com.github.aelmod.ssn2.role.Role;
import com.github.aelmod.ssn2.user.settings.UserSettings;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimalView.class)
    private Integer id;

    @NonNull
    @JsonView(MinimalView.class)
    private String fullName;

    @NonNull
    @Column(nullable = false)
    @JsonView(MinimalView.class)
    private String username;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @JsonFormat(pattern = "dd.MM.yyyy")
    @JsonView(AllPrimitivesView.class)
    private Date birthday;

    @NonNull
    @JsonView(AllPrimitivesView.class)
    private String email;

    @NonNull
    @JsonView(AllPrimitivesView.class)
    private String phone;

    @NonNull
    @JsonView(FullView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @NonNull
    @JsonView(FullView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @NonNull
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

    @OneToMany(mappedBy = "user")
    private List<Picture> pictures = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "friends",
            joinColumns = {@JoinColumn(name = "user1_id")},
            inverseJoinColumns = {@JoinColumn(name = "user2_id")}
    )
    private List<User> friends = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "friendship_requests_bucket",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "requested_friendship_user_id")}
    )
    private List<User> friendRequestsBucket = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "ignore_list",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "ignored_user_id")}
    )
    private List<User> ignoreList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private UserSettings userSettings;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles = Collections.emptyList();

    public interface MinimalView {}

    public interface AllPrimitivesView extends MinimalView {}

    public interface FullView extends AllPrimitivesView, Country.MinimumView, City.MinimumView, Microblog.MinimalView {}
}
