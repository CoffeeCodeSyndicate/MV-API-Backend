package com.coffeecodesyndicate.api.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * Service for creating and validating JWT tokens.
 */
@Service
public class JService {

    private final Key signingKey;
    private final long expirationMillis;

    public JService(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expMinutes:60}") long expMinutes
    ) {
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMillis = expMinutes * 60_000L;
    }

    /**
     * Generate a JWT token for the given subject (usually the user's email).
     */
    public String generateToken(String subject) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationMillis))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extract the subject (e.g. email) from the token.
     */
    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Validate token for subject and expiration.
     */
    public boolean isTokenValid(String token, String expectedSubject) {
        String subject = extractSubject(token);
        return subject.equalsIgnoreCase(expectedSubject) && !isTokenExpired(token);
    }

    /* ---------- Internal helpers ---------- */

    private boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return resolver.apply(claims);
    }
}
