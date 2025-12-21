package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateLimitEnforcementServiceImpl
        implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repository;
    private final ApiKeyRepository apiKeyRepository;

    public RateLimitEnforcementServiceImpl(
            RateLimitEnforcementRepository repository,
            ApiKeyRepository apiKeyRepository) {
        this.repository = repository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement) {

        if (enforcement.getApiKey() == null || enforcement.getApiKey().getId() == null) {
            throw new BadRequestException("ApiKey id is required");
        }

        if (enforcement.getBlockedAt() == null) {
            throw new BadRequestException("blockedAt is required");
        }

        if (enforcement.getLimitExceededBy() == null ||
            enforcement.getLimitExceededBy() < 1) {
            throw new BadRequestException("limitExceededBy must be >= 1");
        }

        ApiKey apiKey = apiKeyRepository.findById(enforcement.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        enforcement.setApiKey(apiKey);

        return repository.save(enforcement);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("RateLimitEnforcement not found"));
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long keyId) {
        return repository.findByApiKey_Id(keyId);
    }
}
