package com.github.aelmod.ssn2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findBy(UserSpecification userSpecification) {
        return userRepository.findBy(userSpecification);
    }

    @Transactional(readOnly = true)
    public User getByPk(Integer id) {
        return userRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findOneByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void makeFriends(User user1, User user2) {
        if (Objects.equals(user1.getId(), user2.getId())) throw new IllegalStateException();
        user1 = getByPk(user1.getId());
        user2 = getByPk(user2.getId());
        user1.getFriends().add(user2);
        user2.getFriends().add(user1);
        userRepository.persist(user1);
        userRepository.persist(user2);
    }

    @Transactional
    public void save(User user) {
        if (!isUsernameExists(user.getUsername())) {
            String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.persist(user);
        } else {
            throw new UserAlreadyExistsException("Username already exists");
        }
    }

    private boolean isUsernameExists(String username) {
        try {
            User userByUsername = getUserByUsername(username);
            return Objects.equals(username, userByUsername.getUsername());
        } catch (EntityNotFoundException ignored) {}
        return false;
    }
}
