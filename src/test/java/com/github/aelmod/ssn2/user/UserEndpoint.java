package com.github.aelmod.ssn2.user;

import com.github.aelmod.ssn2.AbstractEndpoint;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class UserEndpoint extends AbstractEndpoint {

    public ResponseEntity<User[]> getAll() {
        return restTemplate.exchange(baseUrl + "/users", HttpMethod.GET, getHttpEntityWithHeaders(), User[].class);
    }

    public ResponseEntity<User> getById(int id) {
        return restTemplate.exchange(baseUrl + "/users/" + id, HttpMethod.GET, getHttpEntityWithHeaders(), User.class);
    }

    public ResponseEntity<User[]> getFriends(int id) {
        return restTemplate.exchange(baseUrl + "/users/" + id + "/friends", HttpMethod.GET, getHttpEntityWithHeaders(), User[].class);
    }

    public ResponseEntity<User> register(UserRegisterForm userRegisterForm) {
        return restTemplate.postForEntity(baseUrl + "/users/register", userRegisterForm, User.class);
    }
}
