package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String keyValue;

    @Column(nullable = false)
    private Long ownerId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private QuotaPlan plan;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

    
}
