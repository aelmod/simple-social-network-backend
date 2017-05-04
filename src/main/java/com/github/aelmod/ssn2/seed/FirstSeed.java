package com.github.aelmod.ssn2.seed;

import com.github.aelmod.ssn2.user.User;
import com.github.aelmod.ssn2.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class FirstSeed {

    private final UserService userService;

    @Autowired
    public FirstSeed(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initData() {
        User user = new User("test", "test", "pass", "test@test.com");
        user.setSecret("D32K7NDRILUMMXP6");
        if (Objects.equals(user.getName(), userService.getUserByUsername("test").getName())) {
            return;
        }
        userService.save(user);
    }
}
