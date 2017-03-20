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
    private final EntityManager entityManager;

    @Autowired
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> find(UserSpecification specification) {
        CriteriaQuery<User> userCriteriaQuery = specification.toCriteria(entityManager.getCriteriaBuilder());
        return entityManager.createQuery(userCriteriaQuery).getResultList();
    }

    public Optional<User> findByPk(Integer id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    public void save(User user) {
        entityManager.persist(user);
    }

    public Optional<User> findByUsername(String username) {
        List<User> users = entityManager.createQuery("SELECT user from User as user where user.username=:username", User.class)
                .setParameter("username", username)
                .getResultList();
        if (users.isEmpty()) return Optional.empty();
        if (users.size() > 1) throw new IllegalStateException("username is unique property");
        return Optional.of(users.get(0));
    }
}
