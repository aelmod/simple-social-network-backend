package com.github.aelmod.ssn2.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Transactional(readOnly = true)
    public List<Country> findBy(CountrySpecification countrySpecification) {
        return countryRepository.findBy(countrySpecification);
    }

    @Transactional(readOnly = true)
    public Country getByPk(Integer id) {
        return countryRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void create(Country country) {
        if (Objects.nonNull(country.getId()))
            throw new IllegalStateException("You cannot create country with specified id");
        countryRepository.persist(country);
    }
}
