package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repo;
    private final ApiKeyRepository apiKeyRepo;

    public RateLimitEnforcementServiceImpl(
            RateLimitEnforcementRepository repo,
            ApiKeyRepository apiKeyRepo) {
        this.repo = repo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement) {

        if (enforcement.getLimitExceededBy() < 1) {
            throw new BadRequestException("limitExceededBy must be >= 1");
        }

        apiKeyRepo.findById(enforcement.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        return repo.save(enforcement);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enforcement not found"));
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long keyId) {
        return repo.findByApiKey_Id(keyId);
    }
}
