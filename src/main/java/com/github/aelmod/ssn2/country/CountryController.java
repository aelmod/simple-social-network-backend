package com.github.aelmod.ssn2.country;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    @JsonView(Country.MinimumView.class)
    public List<Country> getAll(CountrySpecification countrySpecification) {
        return countryService.findBy(countrySpecification);
    }

    @GetMapping("{countryId}")
    @JsonView(Country.WithUsers.class)
    public Country getById(@PathVariable Integer countryId) {
        return countryService.getByPk(countryId);
    }
}
