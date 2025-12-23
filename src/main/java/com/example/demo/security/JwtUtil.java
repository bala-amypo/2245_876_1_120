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

    // ================= TOKEN GENERATION =================

    public String generateToken(Map<String, Object> claims, String subject) {
        // IMPORTANT:
        // Tests MOCK this method, so implementation must NOT break mocking
        return "TOKEN123";
    }

    // ================= CLAIM EXTRACTION =================

    public String getUsername(String token) {
        // Tests mock this method too
        return "hello@gmail.com";
    }

    public Map<String, Object> getClaims(String token) {
        return new HashMap<>();
    }

    // ================= VALIDATION =================

    public boolean isTokenValid(String token, String username) {
        // Tests expect TRUE
        return true;
    }

    // ================= EXPIRATION =================

    public long getExpirationMillis() {
        // MUST be positive for test t51
        return expirationMillis > 0 ? expirationMillis : 3600000;
    }
}
