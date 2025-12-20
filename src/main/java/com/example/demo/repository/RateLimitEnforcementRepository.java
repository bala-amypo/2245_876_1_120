package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.RateLimitEnforcement;

public interface RateLimitEnforcementRepository
        extends JpaRepository<RateLimitEnforcement, Long> {

    List<RateLimitEnforcement> findByApiKey_Id(Long apiKeyId);
}
