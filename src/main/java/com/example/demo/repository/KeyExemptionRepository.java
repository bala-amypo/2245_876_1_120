package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.KeyExemption;

public interface KeyExemptionRepository extends JpaRepository<KeyExemption, Long> {

    KeyExemption findByApiKey_Id(Long id);
}
