package com.github.aelmod.ssn2.authentication;

import com.github.aelmod.ssn2.AbstractEndpoint;
import org.springframework.http.ResponseEntity;

public class AuthEndpoint extends AbstractEndpoint {

    public ResponseEntity<String> login(LoginPasswordVerificationCodeForm loginPasswordVerificationCodeForm) {
        return restTemplate.postForEntity(baseUrl + "/login", loginPasswordVerificationCodeForm, String.class);
    }
}
