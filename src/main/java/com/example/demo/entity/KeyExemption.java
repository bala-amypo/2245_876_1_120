package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "key_exemptions")
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_key_id", nullable = false)
    @JsonIgnoreProperties({"plan", "createdAt", "updatedAt"})
    private ApiKey apiKey;

    private String notes;

    private Boolean unlimitedAccess;

    private Integer temporaryExtensionLimit;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Timestamp validUntil;

    // ===== getters & setters =====
    public Long getId() { return id; }

    public ApiKey getApiKey() { return apiKey; }
    public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Boolean getUnlimitedAccess() { return unlimitedAccess; }
    public void setUnlimitedAccess(Boolean unlimitedAccess) { this.unlimitedAccess = unlimitedAccess; }

    public Integer getTemporaryExtensionLimit() { return temporaryExtensionLimit; }
    public void setTemporaryExtensionLimit(Integer temporaryExtensionLimit) {
        this.temporaryExtensionLimit = temporaryExtensionLimit;
    }

    public Timestamp getValidUntil() { return validUntil; }
    public void setValidUntil(Timestamp validUntil) { this.validUntil = validUntil; }
}
