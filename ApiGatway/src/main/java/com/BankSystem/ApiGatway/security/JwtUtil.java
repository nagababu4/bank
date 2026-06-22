
package com.BankSystem.ApiGatway.security;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET =
            "ThisIsBankingProjectSecretKeyForJwtAuthentication123456";

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public void validateToken(String token) {

        Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
    }

    public Claims getClaims(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
