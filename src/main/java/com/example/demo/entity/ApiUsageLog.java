package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Table(name="api_usage_logs");

@Entity
public class ApiUsageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ApiKey apiKey;

    @Column(nullable = false)
    private String endpoint;

    @Column(nullable = false)
    private Instant timestamp;

    // getters and setters
}
