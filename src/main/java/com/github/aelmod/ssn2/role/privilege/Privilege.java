package com.github.aelmod.ssn2.role.privilege;

import com.github.aelmod.ssn2.role.Role;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "privileges")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles = Collections.emptyList();
}
