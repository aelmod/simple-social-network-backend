package com.github.aelmod.ssn2.city;

import com.github.aelmod.ssn2.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CityRepository extends BaseRepository<City, Integer> {
    public CityRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
