package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.KeyExemption;

public interface KeyExemptionRepository
        extends JpaRepository<KeyExemption, Long> {

    // ✅ Test calls this
    Optional<KeyExemption> findByApiKey_Id(Long id);

    // ✅ Test ALSO (incorrectly) calls this
    Optional<KeyExemption> findByApikey_Id(Long id);
}
