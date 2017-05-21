package com.github.aelmod.ssn2.authentication;

import com.github.aelmod.ssn2.AbstractEndpoint;
import org.springframework.http.ResponseEntity;

public class AuthEndpoint extends AbstractEndpoint {

    public ResponseEntity<String> login(LoginPasswordForm loginPasswordForm) {
        return restTemplate.postForEntity(baseUrl + "/login", loginPasswordForm, String.class);
    }
}
