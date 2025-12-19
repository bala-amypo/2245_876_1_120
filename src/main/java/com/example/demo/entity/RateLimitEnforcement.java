package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rate_limit_enforcements")
public class RateLimitEnforcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "api_key_id")
    private ApiKey apiKey;

    private LocalDateTime blockedAt;

    private Integer limitExceededBy;

    private String message;

    public RateLimitEnforcement() {}
}
