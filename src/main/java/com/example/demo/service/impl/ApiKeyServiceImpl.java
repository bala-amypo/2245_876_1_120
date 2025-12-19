package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepo;
    private final QuotaPlanRepository quotaPlanRepo;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepo,
                             QuotaPlanRepository quotaPlanRepo) {
        this.apiKeyRepo = apiKeyRepo;
        this.quotaPlanRepo = quotaPlanRepo;
    }

    @Override
    public ApiKey createApiKey(ApiKey apiKey) {

        QuotaPlan plan = quotaPlanRepo.findById(apiKey.getPlan().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Quota plan not found"));

        if (!plan.getActive()) {
            throw new BadRequestException("Quota plan is inactive");
        }

        if (apiKeyRepo.findByKeyValue(apiKey.getKeyValue()).isPresent()) {
            throw new IllegalArgumentException("API Key already exists");
        }

        return apiKeyRepo.save(apiKey);
    }

    @Override
    public ApiKey updateApiKey(Long id, ApiKey apiKey) {

        ApiKey existing = apiKeyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        existing.setKeyValue(apiKey.getKeyValue());
        existing.setPlan(apiKey.getPlan());
        existing.setActive(apiKey.getActive());

        return apiKeyRepo.save(existing);
    }

    @Override
    public ApiKey getApiKeyById(Long id) {
        return apiKeyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));
    }

    @Override
    public Optional<ApiKey> getApiKeyByValue(String keyValue) {
        return apiKeyRepo.findByKeyValue(keyValue);
    }

    @Override
    public List<ApiKey> getAllApiKeys() {
        return apiKeyRepo.findAll();
    }

    @Override
    public void deactivateApiKey(Long id) {

        ApiKey key = apiKeyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        key.setActive(false);
        apiKeyRepo.save(key);
    }
}
