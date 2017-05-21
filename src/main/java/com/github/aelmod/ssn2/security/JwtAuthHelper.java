package com.github.aelmod.ssn2.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

class JwtAuthHelper {

    private static final String ISSUER = "SimpleSocialNetwork2";

    private static final String SIGNING_KEY = "^@()LolKekCheburek@^($%*$%(((";

    private static final Algorithm ALGORITHM = Algorithm.HMAC512(SIGNING_KEY.getBytes());

    private static final long JWT_LIFETIME_IN_MONTHS = 1L;

    public static final String CLAIM_USER_ID = "userId";

    public static final String ROLES = "roles";

    static String createJwt(Integer userId, Collection<String> roles) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim(CLAIM_USER_ID, userId)
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withExpiresAt(getExpiresAt())
                .sign(ALGORITHM);
    }

    static DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .acceptExpiresAt(getExpiresAt().getTime())
                .build();
        return verifier.verify(token);
    }

    private static Date getExpiresAt() {
        return Date.valueOf(LocalDate.from(LocalDate.now()).plusMonths(JWT_LIFETIME_IN_MONTHS));
    }
}
