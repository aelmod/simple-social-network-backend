package com.github.aelmod.ssn2.album.picture;

import com.github.aelmod.ssn2.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PictureSpecification implements EntitySpecification<Picture> {

    @Override
    public CriteriaQuery<Picture> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Picture> pictureCriteriaQuery = cb.createQuery(Picture.class);
        Root<Picture> from = pictureCriteriaQuery.from(Picture.class);
        return pictureCriteriaQuery.select(from);
    }
}
