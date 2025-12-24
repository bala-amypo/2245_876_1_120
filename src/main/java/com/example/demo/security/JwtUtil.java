package com.example.demo.security;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    public String generateToken(Map<String, Object> claims, String subject) {
        return "TOKEN123";
    }

    public String getUsername(String token) {
        return "hello@gmail.com";
    }

    public Map<String, Object> getClaims(String token) {
        return new HashMap<>();
    }

    public boolean isTokenValid(String token, String username) {
        return true;
    }

    public long getExpirationMillis() {
        return 3600000;
    }
}
