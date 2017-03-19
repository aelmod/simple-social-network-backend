package com.github.aelmod.ssn2.user;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    @JsonView(User.MinimalView.class)
    @Transactional
    public Iterable<User> getAll(
            @RequestParam(required = false) Optional<String> name,
            @RequestParam(required = false) Optional<Integer> countryId
    ) {
        UserSpecification specification = new UserSpecification(name, countryId);
        return userRepository.find(specification);
    }

    @GetMapping("{id}")
    @JsonView(User.FullView.class)
    @Transactional
    public User getById(@PathVariable("id") int id) {
        return userRepository.findByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @JsonView(User.AllPrimitivesView.class)
    @GetMapping("{userId}/friends")
    public Set<User> getFriends(@PathVariable Integer userId) {
        return userRepository.findByPk(userId).orElseThrow(EntityNotFoundException::new).getFriends();
    }
}
