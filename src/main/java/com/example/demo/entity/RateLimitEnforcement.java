package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "rate_limit_enforcements")
public class RateLimitEnforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "api_key_id")
    private ApiKey apiKey;

    @Column(nullable = false)
    private Integer enforcedLimit;

    @Column(nullable = false)
    private Instant enforcedAt;

    private String reason;

    public RateLimitEnforcement() {}

    // ===== Getters & Setters =====
    public Long getId() {
        return id;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getEnforcedLimit() {
        return enforcedLimit;
    }

    public void setEnforcedLimit(Integer enforcedLimit) {
        this.enforcedLimit = enforcedLimit;
    }

    public Instant getEnforcedAt() {
        return enforcedAt;
    }

    public void setEnforcedAt(Instant enforcedAt) {
        this.enforcedAt = enforcedAt;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
