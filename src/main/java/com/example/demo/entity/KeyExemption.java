package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "key_exemptions")
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "api_key_id")
    private ApiKey apiKey;

    @Column
    private String notes;

    @Column(nullable = false)
    private Boolean unlimitedAccess = false;

    @Column
    private Integer temporaryExtensionLimit;

    @Column(nullable = false)
    private LocalDateTime validUntil;

    // ===== getters & setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getUnlimitedAccess() {
        return unlimitedAccess;
    }

    public void setUnlimitedAccess(Boolean unlimitedAccess) {
        this.unlimitedAccess = unlimitedAccess;
    }

    public Integer getTemporaryExtensionLimit() {
        return temporaryExtensionLimit;
    }

    public void setTemporaryExtensionLimit(Integer temporaryExtensionLimit) {
        this.temporaryExtensionLimit = temporaryExtensionLimit;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
}
