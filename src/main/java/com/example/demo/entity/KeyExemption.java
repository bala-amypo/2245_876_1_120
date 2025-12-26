package com.example.demo.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime validUntil;

    public KeyExemption() {}


    public Long getId() { return id; }
    public ApiKey getApiKey() { return apiKey; }
    public String getNotes() { return notes; }
    public Boolean getUnlimitedAccess() { return unlimitedAccess; }
    public Integer getTemporaryExtensionLimit() { return temporaryExtensionLimit; }
    public LocalDateTime getValidUntil() { return validUntil; }


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

  
    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    
    @JsonIgnore
    public void setValidUntil(Instant validUntil) {
        this.validUntil =
                LocalDateTime.ofInstant(validUntil, ZoneId.systemDefault());
    }
}
