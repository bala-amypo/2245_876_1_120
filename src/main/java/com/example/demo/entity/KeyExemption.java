package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "key_exemptions")
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "api_key_id")
    private ApiKey apiKey;

    private String notes;

    private Boolean unlimitedAccess;

    private Integer temporaryExtensionLimit;

    private LocalDateTime validUntil;

    public KeyExemption() {}
}
