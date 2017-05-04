package com.github.aelmod.ssn2.security;

import com.github.aelmod.ssn2.user.User;
import com.github.aelmod.ssn2.user.UserService;
import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Component
public class UsernamePasswordVerificationCodeAuthenticationProvider implements AuthenticationProvider {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private final UserService userService;

    private final JwtAuthHelper jwtAuthHelper = new JwtAuthHelper();

    @Autowired
    public UsernamePasswordVerificationCodeAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User userByUsername;
        try {
            userByUsername = userService.getUserByUsername(authentication.getPrincipal().toString());
        } catch (EntityNotFoundException e) {
            throw new BadCredentialsException("auth error");
        }

        UsernamePasswordVerificationCodeAuthentication usernamePasswordVerificationCodeAuthentication =
                (UsernamePasswordVerificationCodeAuthentication) authentication;

        if (Objects.equals(authentication.getPrincipal(), userByUsername.getUsername())
                && bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), userByUsername.getPassword())
                //&& new TimeBasedOneTimePassword().isVerificationCodeValid(userByUsername.getSecret(),
                //Integer.parseInt(usernamePasswordVerificationCodeAuthentication.getVerificationCode()))
                && new Totp(userByUsername.getSecret()).verify(usernamePasswordVerificationCodeAuthentication.getVerificationCode())) {
            String token = jwtAuthHelper.createJwt(userByUsername.getId());
            return new SsnJwtAuthentication(token);
        }
        throw new BadCredentialsException("auth error");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordVerificationCodeAuthentication.class);
    }
}
