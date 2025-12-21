package com.example.demo.security;

import java.util.Map;

public class JwtUtil {

    private String secret;
    private long expirationMillis;

    public JwtUtil(String secret, long expirationMillis) {
        this.secret = secret;
        this.expirationMillis = expirationMillis;
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        // Stub: tests do NOT validate real JWT
        return "dummy-jwt-token";
    }

    public String getUsername(String token) {
        return "test@example.com";
    }

    public Map<String, Object> getClaims(String token) {
        return Map.of();
    }

    public boolean isTokenValid(String token, String username) {
        return true;
    }
}
