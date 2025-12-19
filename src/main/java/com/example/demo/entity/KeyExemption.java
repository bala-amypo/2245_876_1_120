package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "key_exemptions")
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "api_key_id")
    private ApiKey apiKey;

    private String notes;

    @Column(nullable = false)
    private Boolean unlimitedAccess = false;

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

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public ApiKey getApiKey() { return apiKey; }
    public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Boolean getUnlimitedAccess() { return unlimitedAccess; }
    public void setUnlimitedAccess(Boolean unlimitedAccess) {
        this.unlimitedAccess = unlimitedAccess;
    }

    public Integer getTemporaryExtensionLimit() {
        return temporaryExtensionLimit;
    }

    public void setTemporaryExtensionLimit(Integer temporaryExtensionLimit) {
        this.temporaryExtensionLimit = temporaryExtensionLimit;
    }

    public Instant getValidUntil() { return validUntil; }
    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }
}
