package com.example.demo.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.persistence.*;

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

    @Column(nullable = false)
    private Boolean unlimitedAccess = false;

    private Integer temporaryExtensionLimit;

    @Column(nullable = false)
    private LocalDateTime validUntil;

    // ✅ no-arg constructor
    public KeyExemption() {
    }

    // ✅ SPEC constructor
    public KeyExemption(ApiKey apiKey,
                        String notes,
                        Boolean unlimitedAccess,
                        Integer temporaryExtensionLimit,
                        LocalDateTime validUntil) {
        this.apiKey = apiKey;
        this.notes = notes;
        this.unlimitedAccess = unlimitedAccess;
        this.temporaryExtensionLimit = temporaryExtensionLimit;
        this.validUntil = validUntil;
    }

    // ✅ TEST-COMPATIBLE constructor (THIS FIXES LINE 469)
    public KeyExemption(ApiKey apiKey,
                        String notes,
                        Boolean unlimitedAccess,
                        Integer temporaryExtensionLimit,
                        Instant validUntil) {
        this.apiKey = apiKey;
        this.notes = notes;
        this.unlimitedAccess = unlimitedAccess;
        this.temporaryExtensionLimit = temporaryExtensionLimit;
        this.validUntil =
                LocalDateTime.ofInstant(validUntil, ZoneId.systemDefault());
    }

    // Getters
    public Long getId() {
        return id;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public String getNotes() {
        return notes;
    }

    public Boolean getUnlimitedAccess() {
        return unlimitedAccess;
    }

    public Integer getTemporaryExtensionLimit() {
        return temporaryExtensionLimit;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    // Setters
    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setUnlimitedAccess(Boolean unlimitedAccess) {
        this.unlimitedAccess = unlimitedAccess;
    }

    public void setTemporaryExtensionLimit(Integer temporaryExtensionLimit) {
        this.temporaryExtensionLimit = temporaryExtensionLimit;
    }

    // ✅ SPEC setter
    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    // ✅ REQUIRED for tests
    public void setValidUntil(Instant validUntil) {
        this.validUntil =
                LocalDateTime.ofInstant(validUntil, ZoneId.systemDefault());
    }
}
