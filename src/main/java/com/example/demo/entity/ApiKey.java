package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_keys")
@JsonIgnoreProperties({"plan", "createdAt", "updatedAt"})
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String keyValue;

    private Long ownerId;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private QuotaPlan plan;

    private Boolean active = true;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters & setters
}
