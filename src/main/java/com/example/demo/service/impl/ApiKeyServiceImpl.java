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

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepository,
                             QuotaPlanRepository quotaPlanRepository) {
        this.apiKeyRepository = apiKeyRepository;
        this.quotaPlanRepository = quotaPlanRepository;
    }

    @Override
    public ApiKey createApiKey(ApiKey apiKey) {

        if (apiKeyRepository.findByKeyValue(apiKey.getKeyValue()).isPresent()) {
            throw new IllegalArgumentException("API key already exists");
        }

        Long planId = apiKey.getPlan().getId();

        QuotaPlan plan = quotaPlanRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("QuotaPlan not found"));

        if (!Boolean.TRUE.equals(plan.getActive())) {
            throw new BadRequestException("Quota plan is inactive");
        }

        apiKey.setPlan(plan);
        apiKey.setActive(true);

        return apiKeyRepository.save(apiKey);
    }

    @Override
    public ApiKey updateApiKey(Long id, ApiKey updated) {

        ApiKey existing = getApiKeyById(id);

        existing.setKeyValue(updated.getKeyValue());
        existing.setOwnerId(updated.getOwnerId());

        if (updated.getPlan() != null) {
            Long planId = updated.getPlan().getId();
            QuotaPlan plan = quotaPlanRepository.findById(planId)
                    .orElseThrow(() -> new ResourceNotFoundException("QuotaPlan not found"));

            if (!Boolean.TRUE.equals(plan.getActive())) {
                throw new BadRequestException("Quota plan is inactive");
            }

            existing.setPlan(plan);
        }

        return apiKeyRepository.save(existing);
    }

    @Override
    public ApiKey getApiKeyById(Long id) {
        return apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));
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
        ApiKey apiKey = getApiKeyById(id);
        apiKey.setActive(false);
        apiKeyRepository.save(apiKey);
    }
}
