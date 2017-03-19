package com.github.aelmod.ssn2.seed;

import com.github.aelmod.ssn2.country.CountryDao;
import com.github.aelmod.ssn2.user.User;
import com.github.aelmod.ssn2.user.UserRepository;
import com.github.aelmod.ssn2.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by aelmod on 18.03.17.
 */
@Component
public class FirstSeed {
    public static final int USER_COUNT = 5;
    private final UserRepository userRepository;
    private final UserService userService;
    private final CountryDao countryDao;

    @Autowired
    public FirstSeed(UserService userService, CountryDao countryDao, UserRepository userRepository) {
        this.userService = userService;
        this.countryDao = countryDao;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initData() {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < USER_COUNT; i++) {
            User user = new User();
            user.setUsername("user" + i);
            user.setPassword("$2a$10$omf3CW87qtL7tJnZ4GsZou65QjIra.YJmp/w3WOvapXTmB0gPFUWG");
            users.add(user);
            userService.save(user);
        }
        for (int i = 0; i < USER_COUNT; i++) {
//            for (int j = 0; j < Math.random() * 50; j++) {
            User user = users.get(i);
            User friend = users.get((int) (Math.random() * USER_COUNT));
            if (Objects.equals(user.getId(), friend.getId())) continue;
            userService.makeFriends(user, friend);
//            }
        }
    }
}
