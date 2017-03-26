package com.github.aelmod.ssn2.microblog.commentary;

import com.github.aelmod.ssn2.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CommentaryRepository extends BaseRepository<Commentary, Integer> {

    public CommentaryRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
