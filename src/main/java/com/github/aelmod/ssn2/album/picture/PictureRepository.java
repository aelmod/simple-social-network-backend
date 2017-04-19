package com.github.aelmod.ssn2.album.picture;

import com.github.aelmod.ssn2.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PictureRepository extends BaseRepository<Picture, Integer> {

    public PictureRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
