package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "key_exemptions")
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long apiKeyId;

    @Column(nullable = false)
    private String reason;

    private LocalDateTime exemptedAt = LocalDateTime.now();

    public KeyExemption() {}

    public KeyExemption(Long apiKeyId, String reason) {
        this.apiKeyId = apiKeyId;
        this.reason = reason;
    }

    public Long getId() { return id; }
    public Long getApiKeyId() { return apiKeyId; }
    public String getReason() { return reason; }
    public LocalDateTime getExemptedAt() { return exemptedAt; }

    public void setApiKeyId(Long apiKeyId) { this.apiKeyId = apiKeyId; }
    public void setReason(String reason) { this.reason = reason; }
    public void setExemptedAt(LocalDateTime exemptedAt) { this.exemptedAt = exemptedAt; }
}
