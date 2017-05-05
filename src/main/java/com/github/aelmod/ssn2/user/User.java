package com.github.aelmod.ssn2.user;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@RequiredArgsConstructor
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

    @NonNull
    @JsonView(MinimalView.class)
    private String fullName;

    @NonNull
    @JsonView(AllPrimitivesView.class)
    private String email;

    @NonNull
    @Column(nullable = false)
    @JsonView(MinimalView.class)
    private String username;

    @NonNull
    @Column(nullable = false)
    private String password;

    private String secret;

    public interface MinimalView {}

    public interface AllPrimitivesView extends MinimalView {}

    public interface FullView extends AllPrimitivesView {}
}
