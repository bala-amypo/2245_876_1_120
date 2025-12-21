package com.example.demo.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.persistence.*;

@Entity
@Table(name = "api_usage_logs")
public class ApiUsageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey;

    @Column(nullable = false)
    private String endpoint;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // ✅ REQUIRED no-arg constructor
    public ApiUsageLog() {
    }

    // ✅ SPEC-COMPLIANT constructor
    public ApiUsageLog(ApiKey apiKey, String endpoint, LocalDateTime timestamp) {
        this.apiKey = apiKey;
        this.endpoint = endpoint;
        this.timestamp = timestamp;
    }

    // ✅ TEST-COMPATIBLE constructor
    public ApiUsageLog(ApiKey apiKey, String endpoint, Instant timestamp) {
        this.apiKey = apiKey;
        this.endpoint = endpoint;
        this.timestamp = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
    }

    // Getters
    public Long getId() {
        return id;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    // ✅ ORIGINAL setter
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // ✅ THIS IS THE LINE YOUR TESTS REQUIRE
    public void setTimestamp(Instant timestamp) {
        this.timestamp = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
    }
}
