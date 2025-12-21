package com.example.demo.service;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyExemptionService {

    private final KeyExemptionRepository keyExemptionRepository;
    private final ApiKeyRepository apiKeyRepository;

    public KeyExemptionService(KeyExemptionRepository keyExemptionRepository,
                               ApiKeyRepository apiKeyRepository) {
        this.keyExemptionRepository = keyExemptionRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    public KeyExemption create(KeyExemption exemption) {
        Long apiKeyId = exemption.getApiKey().getId();

        ApiKey apiKey = apiKeyRepository.findById(apiKeyId)
                .orElseThrow(() -> new RuntimeException("ApiKey not found"));

        exemption.setApiKey(apiKey);
        return keyExemptionRepository.save(exemption);
    }

    public List<KeyExemption> getAll() {
        return keyExemptionRepository.findAll();
    }

    public KeyExemption getById(Long id) {
        return keyExemptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KeyExemption not found"));
    }
}
