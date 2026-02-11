package com.Major.Project.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private final Key key = Keys.hmacShaKeyFor(
            "medimate-super-secret-key-change-this-in-prod-123456".getBytes()
    );
    List<String> roles = Arrays.asList("ADMIN", "DOCTOR", "PATIENT", "STAFF");

    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    public String validateAndGetUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public List<String> getRolesFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        Object rolesClaim = claims.get("roles");
        if (rolesClaim instanceof List<?>) {
            return ((List<?>) rolesClaim).stream().map(Object::toString).toList();
        } else if (rolesClaim instanceof String) {
            return List.of(rolesClaim.toString());
        }
        return List.of();
    }
}
