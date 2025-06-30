package com.expenso.expense_tracker.security;
 
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
 
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;
 
@Service
public class JwtService {
 
    private static final String SECRET = "expenso-secret-key-which-should-be-long-enough";
    private static final long EXPIRATION_TIME = 86400000L; // 1 day
 
    private SecretKey key;
    private JwtParser parser;
 
    @PostConstruct
    public void init() {
        // Ensure the secret key is strong enough (HS256 needs at least 256-bit key)
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        this.parser = Jwts.parser()
                          .verifyWith(key)
                          .build();
    }
 
    public String generateToken(UUID userId) {
        JwtBuilder builder = Jwts.builder();
 
        return builder
                .subject(userId.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
 
    public UUID extractUserId(String token) {
    if (token == null || !token.startsWith("Bearer ") || token.length() <= 7) {
        throw new RuntimeException("Invalid Authorization header");
    }
    token = token.substring(7);  // 🛡️ safe now

    Claims claims = parser.parseSignedClaims(token).getPayload();
    return UUID.fromString(claims.getSubject());
}

}