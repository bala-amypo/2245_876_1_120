package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "key_exemptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyExemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "api_key_id", nullable = false, unique = true)
    private ApiKey apiKey;

    private Integer temporaryExtensionLimit;

    @Column(nullable = false)
    private Instant validUntil;
}
