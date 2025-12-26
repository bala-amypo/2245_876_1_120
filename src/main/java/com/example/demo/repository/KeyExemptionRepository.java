package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.KeyExemption;

public interface KeyExemptionRepository
        extends JpaRepository<KeyExemption, Long> {

    // ✅ Used by service + tests
    Optional<KeyExemption> findByApiKey_Id(Long id);

    // ✅ Typo used by tests (DO NOT REMOVE)
    Optional<KeyExemption> findByApikey_Id(Long id);

    // ✅ Used by service (list retrieval)
    List<KeyExemption> findAllByApiKey_Id(Long id);
}
