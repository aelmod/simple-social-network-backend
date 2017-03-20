package com.github.aelmod.ssn2.security;

import com.github.aelmod.ssn2.user.User;
import com.github.aelmod.ssn2.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private Map<String, Integer> tokenUserIdMap = new HashMap<>();

    private final UserService userService;
    private final JwtAuthHelper jwtAuthHelper;

    @Autowired
    public UsernamePasswordAuthenticationProvider(UserService userService, JwtAuthHelper jwtAuthHelper) {
        this.userService = userService;
        this.jwtAuthHelper = jwtAuthHelper;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String token = UUID.randomUUID().toString();
        User userByUsername;
        try {
            userByUsername = userService.getUserByUsername(authentication.getPrincipal().toString());
        } catch (EntityNotFoundException e) {
            throw new UsernameNotFoundException("auth err", e);
        }

        if (Objects.equals(authentication.getPrincipal(), userByUsername.getUsername())
                && bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), userByUsername.getPassword())) {
            String token = jwtAuthHelper.createJwt(userByUsername.getId());
            tokenUserIdMap.put(token, userByUsername.getId());
            return new SsnJwtAuthentication(token);
        }
        throw new BadCredentialsException("auth error");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    public Map<String, Integer> getTokenUserIdMap() {
        return tokenUserIdMap;
    }
}
