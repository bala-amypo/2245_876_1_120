package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.KeyExemption;

public interface KeyExemptionRepository
        extends JpaRepository<KeyExemption, Long> {

    // ✅ MUST be apiKey (camelCase) — NOT apikey
    List<KeyExemption> findAllByApiKey_Id(Long apiKeyId);
}
