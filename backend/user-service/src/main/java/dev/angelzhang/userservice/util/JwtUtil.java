package dev.angelzhang.userservice.util;

import dev.angelzhang.userservice.enums.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String key;
    @Value("${jwt.refreshExpiration}")
    private Long REFRESH_EXPIRATION;
    @Value("${jwt.accessExpiration}")
    private Long ACCESS_EXPIRATION;
    @Value("${jwt.issuer}")
    private String issuer;

    public String generateAccessToken(Long userId, List<Role> userRole) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", List.of(userRole));
        claims.put("token_type", "access");
        return createToken(claims, userId.toString(), ACCESS_EXPIRATION);
    }

    public String generateRefreshToken(Long userId, List<Role> userRole) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userRole);
        claims.put("token_type", "refresh");
        return createToken(claims, userId.toString(), REFRESH_EXPIRATION);
    }

    private String createToken(Map<String, Object> claims, String subject, Long expiration) {
        return Jwts.builder()
                .claims(claims)

                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .issuer(issuer)

                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }

    public Long extractUserId(String token) {
        return Long.valueOf(Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject());
    }

    public boolean isValidToken(String token, Long userId) {
        return userId.equals(extractUserId(token)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    public boolean isRefreshToken(String refreshToken) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(refreshToken)
                .getPayload()
                .get("token_type", String.class).equals("refresh");
    }
}
