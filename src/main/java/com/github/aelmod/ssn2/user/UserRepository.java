package com.github.aelmod.ssn2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

/**
 * Created by aelmod on 18.03.17.
 */
@Repository
public class UserRepository {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserDao userDao;

    public List<User> find(UserSpecification specification) {
        CriteriaQuery<User> userCriteriaQuery = specification.toCriteria(entityManager.getCriteriaBuilder());
        return entityManager.createQuery(userCriteriaQuery).getResultList();
    }

    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(userDao.findOne(id));
    }
}
