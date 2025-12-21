package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.sql.Timestamp;
@Entity
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "api_key_id")
    @JsonIgnoreProperties({"plan", "createdAt", "updatedAt"})
    private ApiKey apiKey;

    private String notes;
    private Boolean unlimitedAccess;
    private Integer temporaryExtensionLimit;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Timestamp validUntil;
}
