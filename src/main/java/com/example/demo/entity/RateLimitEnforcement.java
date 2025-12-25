package com.example.demo.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "rate_limit_enforcements")
public class RateLimitEnforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey;

    // ✅ ACCEPTS: 2025-12-25T14:09:28.957Z
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime blockedAt;

    @Column(nullable = false)
    private Integer limitExceededBy;

    @Column(nullable = false)
    private String message;

    public RateLimitEnforcement() {}

    public RateLimitEnforcement(
            ApiKey apiKey,
            LocalDateTime blockedAt,
            Integer limitExceededBy,
            String message) {
        this.apiKey = apiKey;
        this.blockedAt = blockedAt;
        this.limitExceededBy = limitExceededBy;
        this.message = message;
    }

    // ===== GETTERS =====
    public Long getId() { return id; }
    public ApiKey getApiKey() { return apiKey; }
    public LocalDateTime getBlockedAt() { return blockedAt; }
    public Integer getLimitExceededBy() { return limitExceededBy; }
    public String getMessage() { return message; }

    // ===== SETTERS =====
    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    // ✅ JSON setter
    public void setBlockedAt(LocalDateTime blockedAt) {
        this.blockedAt = blockedAt;
    }

    // ❌ TEST-ONLY setter (kept for tests)
    @JsonIgnore
    public void setBlockedAt(Instant blockedAt) {
        this.blockedAt =
                LocalDateTime.ofInstant(blockedAt, ZoneId.systemDefault());
    }

    public void setLimitExceededBy(Integer limitExceededBy) {
        this.limitExceededBy = limitExceededBy;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
