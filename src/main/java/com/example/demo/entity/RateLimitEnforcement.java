package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rate_limit_enforcements")
public class RateLimitEnforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "api_key_id", nullable = false)
    @JsonIgnoreProperties({
        "plan",
        "createdAt",
        "updatedAt"
    })
    private ApiKey apiKey;

    @Column(nullable = false)
    private LocalDateTime blockedAt;

    @Column(nullable = false)
    private Integer limitExceededBy;

    private String message;

    public RateLimitEnforcement() {}

    // ===== GETTERS =====
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

    // ===== SETTERS =====
    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public void setBlockedAt(LocalDateTime blockedAt) {
        this.blockedAt = blockedAt;
    }

    public void setLimitExceededBy(Integer limitExceededBy) {
        this.limitExceededBy = limitExceededBy;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
