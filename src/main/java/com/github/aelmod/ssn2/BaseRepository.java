package com.github.aelmod.ssn2;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class BaseRepository<T, T1> {
    private final EntityManager entityManager;

    @Autowired
    public BaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
