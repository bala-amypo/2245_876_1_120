package com.example.demo.service.impl;

import java.util.List;

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

    private final ApiKeyRepository apiKeyRepository;
    private final QuotaPlanRepository quotaPlanRepository;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepository,
                             QuotaPlanRepository quotaPlanRepository) {
        this.apiKeyRepository = apiKeyRepository;
        this.quotaPlanRepository = quotaPlanRepository;
    }

    @Override
    public ApiKey createApiKey(ApiKey apiKey) {

        QuotaPlan plan = quotaPlanRepository.findById(apiKey.getPlan().getId())
                .orElseThrow(() -> new ResourceNotFoundException("QuotaPlan not found"));

        if (!plan.isActive()) {
            throw new BadRequestException("Quota plan is inactive");
        }

        if (apiKeyRepository.findByKeyValue(apiKey.getKeyValue()).isPresent()) {
            throw new IllegalArgumentException("API key already exists");
        }

        apiKey.setPlan(plan);
        apiKey.setActive(apiKey.getActive() != null ? apiKey.getActive() : true);

        return apiKeyRepository.save(apiKey);
    }

    @Override
    public ApiKey updateApiKey(Long id, ApiKey apiKey) {

        ApiKey existing = apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        existing.setKeyValue(apiKey.getKeyValue());
        existing.setOwnerId(apiKey.getOwnerId());

        if (apiKey.getPlan() != null) {
            QuotaPlan plan = quotaPlanRepository.findById(apiKey.getPlan().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("QuotaPlan not found"));

            if (!plan.isActive()) {
                throw new BadRequestException("Quota plan is inactive");
            }

            existing.setPlan(plan);
        }

        existing.setActive(apiKey.getActive());

        return apiKeyRepository.save(existing);
    }

    @Override
    public ApiKey getApiKeyById(Long id) {
        return apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));
    }

    
    @Override
    public ApiKey getApiKeyByValue(String keyValue) {
        return apiKeyRepository.findByKeyValue(keyValue)
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));
    }

    @Override
    public List<ApiKey> getAllApiKeys() {
        return apiKeyRepository.findAll();
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey apiKey = apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        apiKey.setActive(false);
        apiKeyRepository.save(apiKey);
    }
}
