package com.example.demo.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.persistence.*;

@Entity
@Table(name = "rate_limit_enforcements")
public class RateLimitEnforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey;

    @Column(nullable = false)
    private LocalDateTime blockedAt;

    @Column(nullable = false)
    private Integer limitExceededBy;

    @Column(nullable = false)
    private String message;

    // ✅ no-arg constructor
    public RateLimitEnforcement() {
    }

    // ✅ SPEC constructor
    public RateLimitEnforcement(ApiKey apiKey,
                                LocalDateTime blockedAt,
                                Integer limitExceededBy,
                                String message) {
        this.apiKey = apiKey;
        this.blockedAt = blockedAt;
        this.limitExceededBy = limitExceededBy;
        this.message = message;
    }

    // ✅ TEST-COMPATIBLE constructor (THIS FIXES LINE 469)
    public RateLimitEnforcement(ApiKey apiKey,
                                Instant blockedAt,
                                Integer limitExceededBy,
                                String message) {
        this.apiKey = apiKey;
        this.blockedAt = LocalDateTime.ofInstant(blockedAt, ZoneId.systemDefault());
        this.limitExceededBy = limitExceededBy;
        this.message = message;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public LocalDateTime getBlockedAt() {
        return blockedAt;
    }

    public Integer getLimitExceededBy() {
        return limitExceededBy;
    }

    public String getMessage() {
        return message;
    }

    // Setters
    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public void setBlockedAt(LocalDateTime blockedAt) {
        this.blockedAt = blockedAt;
    }

    // ✅ REQUIRED setter for tests
    public void setBlockedAt(Instant blockedAt) {
        this.blockedAt = LocalDateTime.ofInstant(blockedAt, ZoneId.systemDefault());
    }

    public void setLimitExceededBy(Integer limitExceededBy) {
        this.limitExceededBy = limitExceededBy;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
