package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.ResourceNotFoundException;
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
    public KeyExemption createExemption(KeyExemption exemption) {

        Long apiKeyId = exemption.getApiKey().getId();

        ApiKey apiKey = apiKeyRepository.findById(apiKeyId)
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        exemption.setApiKey(apiKey);
        return repository.save(exemption);
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption data) {

        KeyExemption existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exemption not found"));

        existing.setNotes(data.getNotes());
        existing.setUnlimitedAccess(data.getUnlimitedAccess());
        existing.setTemporaryExtensionLimit(data.getTemporaryExtensionLimit());
        existing.setValidUntil(data.getValidUntil());

        return repository.save(existing);
    }

    @Override
    public KeyExemption getExemptionByKey(Long apiKeyId) {
        return repository.findByApiKey_Id(apiKeyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No exemption for API key " + apiKeyId));
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return repository.findAll();
    }
}
