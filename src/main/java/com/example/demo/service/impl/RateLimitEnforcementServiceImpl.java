package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.RateLimitEnforcement;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement) {
        return enforcement;
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return new RateLimitEnforcement();
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long keyId) {
        return List.of();
    }
}
