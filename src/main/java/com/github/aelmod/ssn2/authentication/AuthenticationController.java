package com.github.aelmod.ssn2.authentication;

import com.github.aelmod.ssn2.security.SsnJwtAuthentication;
import com.github.aelmod.ssn2.security.UsernamePasswordVerificationCodeAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;


    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("login")
    public String login(@RequestBody @Valid LoginPasswordVerificationCodeForm loginPasswordVerificationCodeForm) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordVerificationCodeAuthentication(
                loginPasswordVerificationCodeForm.getLogin(),
                loginPasswordVerificationCodeForm.getPassword(),
                loginPasswordVerificationCodeForm.getVerificationCode()
        ));
        return ((SsnJwtAuthentication) authenticate).getToken();
    }
}
