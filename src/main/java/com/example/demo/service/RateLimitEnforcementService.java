package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.RateLimitEnforcement;

public interface RateLimitEnforcementService {

    RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement);

    RateLimitEnforcement getEnforcementById(Long id);

    List<RateLimitEnforcement> getEnforcementsForKey(Long keyId);
}
