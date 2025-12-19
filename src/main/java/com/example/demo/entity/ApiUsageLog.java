package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "api_usage_logs")
public class ApiUsageLog {

    @Id
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "api_key_id")
    private ApiKey apiKey;

    @Column(nullable = false)
    private String endpoint;

    @Column(nullable = false)
    private Instant timestamp;

    public ApiUsageLog() {}

    public ApiUsageLog(ApiKey apiKey, String endpoint, Instant timestamp) {
        this.id=id;
        this.apiKey = apiKey;
        this.endpoint = endpoint;
        this.timestamp = timestamp;
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }

    public ApiKey getApiKey() { return apiKey; }
    public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }

    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
