package com.github.aelmod.ssn2.city;

import com.github.aelmod.ssn2.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CitySpecification implements EntitySpecification<City> {
    @Override
    public CriteriaQuery<City> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<City> cityCriteriaQuery = cb.createQuery(City.class);
        Root<City> from = cityCriteriaQuery.from(City.class);
        return cityCriteriaQuery.select(from);
    }
}
