package com.github.aelmod.ssn2.country;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryDao countryDao;

    @Autowired
    public CountryController(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @GetMapping("")
//    @JsonView(User.MinimalView.class)
    @JsonView(Country.WithUsers.class)
    public Iterable<Country> getAll() {
        return countryDao.findAll();
    }
}
