package com.expenso.expense_tracker.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    private final String SECRET = "expenso-secret-key"; // keep this safe or move to application.properties
    private final long EXPIRATION_TIME = 86400000; // 1 day

    // ✅ Generate JWT using UUID
    public String generateToken(UUID userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();
    }

    // ✅ Decode JWT and extract userId
    public UUID extractUserId(String token) {
        token = token.replace("Bearer ", "");

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody();

        return UUID.fromString(claims.getSubject());
    }
}
