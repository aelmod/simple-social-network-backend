package com.github.aelmod.ssn2.seed;

import com.github.aelmod.ssn2.country.Country;
import com.github.aelmod.ssn2.country.CountryService;
import com.github.aelmod.ssn2.user.User;
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
    private static final int USER_COUNT = 5;
    private static final int COUNTRY_COUNT = 3;

    private final UserService userService;
    private final CountryService countryService;

    @Autowired
    public FirstSeed(UserService userService, CountryService countryService) {
        this.userService = userService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void initData() {
        ArrayList<Country> countries = new ArrayList<>();
        for (int i = 0; i < COUNTRY_COUNT; i++) {
            Country country = new Country("country" + i);
            countries.add(country);
            countryService.create(country);
        }

        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < USER_COUNT; i++) {
            User user = new User();
            user.setName("user" + i);
            user.setUsername("user" + i);
            user.setPassword("pass1");
            user.setCountry(countries.get((int) (Math.random() * COUNTRY_COUNT)));
            users.add(user);
            userService.save(user);
        }

        for (int i = 0; i < USER_COUNT; i++) {
            User user = users.get(i);
            User friend = users.get((int) (Math.random() * USER_COUNT));
            if (Objects.equals(user.getId(), friend.getId())) continue;
            userService.makeFriends(user, friend);
        }
    }
}
