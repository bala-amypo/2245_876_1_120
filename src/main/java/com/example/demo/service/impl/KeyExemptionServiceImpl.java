package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repository;
    private final ApiKeyRepository apiKeyRepository;

    public KeyExemptionServiceImpl(KeyExemptionRepository repository,
                                   ApiKeyRepository apiKeyRepository) {
        this.repository = repository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public KeyExemption create(KeyExemption exemption) {
        Long apiKeyId = exemption.getApiKey().getId();

        ApiKey apiKey = apiKeyRepository.findById(apiKeyId)
                .orElseThrow(() -> new RuntimeException("ApiKey not found"));

        exemption.setApiKey(apiKey);
        return repository.save(exemption);
    }

    @Override
    public KeyExemption getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("KeyExemption not found"));
    }

    @Override
    public List<KeyExemption> getAll() {
        return repository.findAll();
    }

    @Override
    public List<KeyExemption> getByApiKeyId(Long apiKeyId) {
        return repository.findByApiKey_Id(apiKeyId);
    }
}
