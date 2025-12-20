package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private final QuotaPlanRepository quotaPlanRepository;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepository, QuotaPlanRepository quotaPlanRepository) {
        this.apiKeyRepository = apiKeyRepository;
        this.quotaPlanRepository = quotaPlanRepository;
    }

    @Override
    public ApiKey createApiKey(ApiKey apiKey) {
        // Validate unique key
        apiKeyRepository.findByKeyValue(apiKey.getKeyValue()).ifPresent(k -> {
            throw new BadRequestException("API Key already exists");
        });

        // Validate plan
        QuotaPlan plan = quotaPlanRepository.findById(apiKey.getPlan().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Quota Plan not found"));
        if (!plan.getActive()) {
            throw new BadRequestException("Quota Plan is inactive");
        }
        apiKey.setPlan(plan);
        return apiKeyRepository.save(apiKey);
    }

    @Override
    public ApiKey updateApiKey(Long id, ApiKey apiKey) {
        ApiKey existing = apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));
        existing.setKeyValue(apiKey.getKeyValue());
        existing.setOwnerId(apiKey.getOwnerId());

        QuotaPlan plan = quotaPlanRepository.findById(apiKey.getPlan().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Quota Plan not found"));
        existing.setPlan(plan);

        existing.setActive(apiKey.getActive());
        return apiKeyRepository.save(existing);
    }

    @Override
    public ApiKey getApiKeyById(Long id) {
        return apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));
    }

    @Override
    public Optional<ApiKey> getApiKeyByValue(String keyValue) {
        return apiKeyRepository.findByKeyValue(keyValue);
    }

    @Override
    public List<ApiKey> getAllApiKeys() {
        return apiKeyRepository.findAll();
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey existing = apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));
        existing.setActive(false);
        apiKeyRepository.save(existing);
    }
}
