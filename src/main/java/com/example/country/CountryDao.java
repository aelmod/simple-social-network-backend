package com.example.country;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDao extends CrudRepository<Country, Integer>{

}
