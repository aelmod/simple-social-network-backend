package com.github.aelmod.ssn2.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class CityService {
    @Autowired
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional(readOnly = true)
    public List<City> findBy(CitySpecification citySpecification) {
        return cityRepository.findBy(citySpecification);
    }

    @Transactional(readOnly = true)
    public City getByPk(Integer id) {
        return cityRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void create(City city) {
        if (Objects.nonNull(city.getId())) throw new IllegalStateException("You cannot create city with specified id");
        cityRepository.persist(city);
    }
}
