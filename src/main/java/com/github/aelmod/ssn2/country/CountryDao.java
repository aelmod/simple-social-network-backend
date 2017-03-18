package com.github.aelmod.ssn2.country;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDao extends CrudRepository<Country, Integer>{

}
