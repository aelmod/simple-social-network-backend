package com.github.aelmod.ssn2.country;

import com.github.aelmod.ssn2.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CountryRepository extends BaseRepository<Country, Integer> {

    @Autowired
    public CountryRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
