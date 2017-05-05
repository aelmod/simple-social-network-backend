package com.github.aelmod.ssn2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final static String QR_PREFIX =
            "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";

    private final static String APP_NAME = "ElectronicHealthCardSystem";

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
    public String save(User user) {
        if (!isUsernameExists(user.getUsername())) {
            String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.persist(user);
            return generateQRUrl(user);
        } else {
            throw new UserAlreadyExistsException("Username already exists");
        }
    }

    private String generateQRUrl(User user) {
        try {
            return QR_PREFIX + URLEncoder.encode(String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", APP_NAME, user.getEmail(), user.getSecret(), APP_NAME), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isUsernameExists(String username) {
        try {
            User userByUsername = getUserByUsername(username);
            return Objects.equals(username, userByUsername.getUsername());
        } catch (EntityNotFoundException ignored) {/* Exception handling not need for this situation */}
        return false;
    }
}
