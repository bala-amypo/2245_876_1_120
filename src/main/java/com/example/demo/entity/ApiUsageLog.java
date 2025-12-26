package com.example.demo.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonFormat(
        pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][X]"
    )
    private LocalDateTime timestamp;

   
    public ApiUsageLog() {}

    
    public ApiUsageLog(ApiKey apiKey, String endpoint, LocalDateTime timestamp) {
        this.apiKey = apiKey;
        this.endpoint = endpoint;
        this.timestamp = timestamp;
    }

   
    public ApiUsageLog(ApiKey apiKey, String endpoint, Instant timestamp) {
        this.apiKey = apiKey;
        this.endpoint = endpoint;
        this.timestamp =
                LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
    }

  
    public Long getId() { return id; }
    public ApiKey getApiKey() { return apiKey; }
    public String getEndpoint() { return endpoint; }
    public LocalDateTime getTimestamp() { return timestamp; }

    
    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

  
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

  
    @JsonIgnore
    public void setTimestamp(Instant timestamp) {
        this.timestamp =
                LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
    }
}
