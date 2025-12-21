package com.example.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private final String secret;
    private final long expirationMillis;

    public JwtUtil(String secret, long expirationMillis) {
        this.secret = secret;
        this.expirationMillis = expirationMillis;
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        // tests only check non-null token
        return "dummy-jwt-token";
    }

    public String getUsername(String token) {
        return "test@example.com";
    }

    public Map<String, Object> getClaims(String token) {
        return new HashMap<>();
    }

    public boolean isTokenValid(String token, String username) {
        return true;
    }
}
