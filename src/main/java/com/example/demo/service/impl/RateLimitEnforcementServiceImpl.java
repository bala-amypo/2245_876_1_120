package com.example.demo.service.impl;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
    public RateLimitEnforcement enforce(RateLimitEnforcement enforcement) {

        if (enforcement.getEnforcedLimit() == null ||
            enforcement.getEnforcedLimit() <= 0) {
            throw new BadRequestException("Enforced limit must be positive");
        }

        if (enforcement.getEnforcedAt() == null ||
            enforcement.getEnforcedAt().isAfter(Instant.now())) {
            throw new BadRequestException("enforcedAt cannot be in the future");
        }

        apiKeyRepository.findById(enforcement.getApiKey().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("API Key not found"));

        return repository.save(enforcement);
    }

    @Override
    public List<RateLimitEnforcement> getForApiKey(Long apiKeyId) {
        return repository.findByApiKey_Id(apiKeyId);
    }
}
