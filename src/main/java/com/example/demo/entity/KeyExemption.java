package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "key_exemptions")
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKey apiKey;

    private String notes;

    private Boolean unlimitedAccess;

    private Integer temporaryExtensionLimit;

    private Instant validUntil;

    public KeyExemption() {}

    public KeyExemption(ApiKey apiKey, String notes, Boolean unlimitedAccess,
                        Integer temporaryExtensionLimit, Instant validUntil) {
        this.apiKey = apiKey;
        this.notes = notes;
        this.unlimitedAccess = unlimitedAccess;
        this.temporaryExtensionLimit = temporaryExtensionLimit;
        this.validUntil = validUntil;
    }

    // Getters and setters
    public Long getId() { return id; }
    public ApiKey getApiKey() { return apiKey; }
    public String getNotes() { return notes; }
    public Boolean getUnlimitedAccess() { return unlimitedAccess; }
    public Integer getTemporaryExtensionLimit() { return temporaryExtensionLimit; }
    public Instant getValidUntil() { return validUntil; }

    public void setNotes(String notes) { this.notes = notes; }
    public void setUnlimitedAccess(Boolean unlimitedAccess) { this.unlimitedAccess = unlimitedAccess; }
    public void setTemporaryExtensionLimit(Integer temporaryExtensionLimit) { this.temporaryExtensionLimit = temporaryExtensionLimit; }
    public void setValidUntil(Instant validUntil) { this.validUntil = validUntil; }
}
