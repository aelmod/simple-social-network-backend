package com.github.aelmod.ssn2.user;

import com.github.aelmod.ssn2.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends BaseRepository<User, Integer> {

    @Autowired
    public UserRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<User> findOneByUsername(String username) {
        List<User> users = entityManager.createQuery("SELECT user FROM User AS user WHERE user.username=:username", User.class)
                .setParameter("username", username)
                .getResultList();

        if (users.isEmpty()) return Optional.empty();
        if (users.size() > 1) throw new IllegalStateException("username is unique property");
        return Optional.of(users.get(0));
    }
}
