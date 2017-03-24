package com.github.aelmod.ssn2.user;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @JsonView(User.MinimalView.class)
    public List<User> getAll(UserSpecification userSpecification) {
        return userService.findBy(userSpecification);
    }

    @GetMapping("{userId}")
    @JsonView(User.FullView.class)
    public User getById(@PathVariable int userId) {
        return userService.getByPk(userId);
    }

    @GetMapping("{userId}/friends")
    @JsonView(User.AllPrimitivesView.class)
    public Set<User> getFriends(@PathVariable Integer userId) {
        return userService.getByPk(userId).getFriends();
    }

    @PostMapping("register")
    public void registerUser(@RequestBody @Valid UserRegisterForm user){
        userService.save(user.toUser());
    }
}
