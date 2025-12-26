package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.KeyExemption;

public interface KeyExemptionRepository
        extends JpaRepository<KeyExemption, Long> {

    // ✅ EXACT camelCase match — REQUIRED
    Optional<KeyExemption> findByApiKey_Id(Long apiKeyId);
}
