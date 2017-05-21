package com.github.aelmod.ssn2.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.aelmod.ssn2.microblog.Microblog;
import com.github.aelmod.ssn2.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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
    public User getById(@CurrentUser User currentUser, @PathVariable int userId) {
        User userByPk = userService.getByPk(userId);
        List<Microblog> microblogs = userByPk.getMicroblogs();
        if (Objects.nonNull(microblogs))
            microblogs.sort(Collections.reverseOrder(Comparator.comparing(Microblog::getCreationTime)));
        if (userByPk.getIgnoreList().contains(currentUser)) {
            User user = new User();
            user.setFullName(userByPk.getFullName());
            return user;
        }
        return userByPk;
    }

    @GetMapping("currentUser")
    @JsonView(User.MinimalView.class)
    public User getCurrentUser(@CurrentUser User user) {
        return user;
    }

    @PutMapping("{ignoredUserId}/ignore")
    public void addUserToIgnoreList(@CurrentUser User currentUser, @PathVariable Integer ignoredUserId) {
        userService.ignore(currentUser, ignoredUserId);
    }

    @PostMapping("register")
    public void registerUser(@RequestBody @Valid UserRegisterForm registerForm) {
        userService.save(registerForm.toUser());
    }
}
