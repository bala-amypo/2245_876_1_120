package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class RateLimitEnforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ApiKey apiKey;

    @Column(nullable = false)
    private Instant blockedAt;

    @Column(nullable = false)
    private Integer limitExceededBy;

    @Column(nullable = false)
    private String message;

    // getters and setters
}
