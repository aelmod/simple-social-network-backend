package com.github.aelmod.ssn2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User getByPk(Integer id) {
        return userRepository.findByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void makeFriends(User user1, User user2) {
        if (Objects.equals(user1.getId(), user2.getId())) throw new IllegalStateException();
        user1 = getByPk(user1.getId());
        user2 = getByPk(user2.getId());
        user1.getFriends().add(user2);
        user2.getFriends().add(user1);
        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }
}
