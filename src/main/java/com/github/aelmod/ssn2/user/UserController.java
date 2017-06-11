package com.github.aelmod.ssn2.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.security.CurrentUser;
import com.github.aelmod.ssn2.security.google2fa.User2faToken;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("currentUser")
    @JsonView(User.MinimalView.class)
    public User getCurrentUser(@CurrentUser User user) {
        return user;
    }

    @PostMapping("register")
    public User2faToken registerUser(@RequestBody @Valid UserRegisterForm registerForm) {
        User registeredUser = registerForm.toUser();
        registeredUser.setSecret(Base32.random());
        return userService.save(registeredUser);
    }
}
