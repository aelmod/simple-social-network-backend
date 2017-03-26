package com.github.aelmod.ssn2.microblog;

import com.github.aelmod.ssn2.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MicroblogRepository extends BaseRepository<Microblog, Integer> {

    public MicroblogRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
