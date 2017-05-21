package com.github.aelmod.ssn2.authentication;

import com.github.aelmod.ssn2.security.SsnJwtAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String authenticate(LoginPasswordForm loginPasswordForm) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginPasswordForm.getLogin(), loginPasswordForm.getPassword())
        );
        return ((SsnJwtAuthentication) authenticate).getToken();
    }
}
