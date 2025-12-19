package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "api_keys")
public class ApiKey {

    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String keyValue;

    @Column(nullable = false)
    private Long ownerId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plan_id")
    private QuotaPlan plan;

    @Column(nullable = false)
    private Boolean active = true;

    private Instant createdAt;
    private Instant updatedAt;

    public ApiKey() {}

    public ApiKey(String keyValue, Long ownerId, QuotaPlan plan, Boolean active) {
        this.id=id;
        this.keyValue = keyValue;
        this.ownerId = ownerId;
        this.plan = plan;
        this.active = active != null ? active : true;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PrePersist
    public void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = Instant.now();
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }

    public String getKeyValue() { return keyValue; }
    public void setKeyValue(String keyValue) { this.keyValue = keyValue; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public QuotaPlan getPlan() { return plan; }
    public void setPlan(QuotaPlan plan) { this.plan = plan; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
