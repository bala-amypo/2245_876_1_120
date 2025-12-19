package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

key_exemptions
@Entity
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private ApiKey apiKey;

    private String notes;

    @Column(nullable = false)
    private Boolean unlimitedAccess = false;

    private Integer temporaryExtensionLimit;

    private Instant validUntil;

    // getters and setters
}
