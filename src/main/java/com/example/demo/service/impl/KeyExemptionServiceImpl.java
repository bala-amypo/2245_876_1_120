package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository exemptionRepository;
    private final ApiKeyRepository apiKeyRepository;

    public KeyExemptionServiceImpl(
            KeyExemptionRepository exemptionRepository,
            ApiKeyRepository apiKeyRepository) {
        this.exemptionRepository = exemptionRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {

        // Validate API key
        if (exemption.getApiKey() == null || exemption.getApiKey().getId() == null) {
            throw new BadRequestException("ApiKey id is required");
        }

        // Validate extension limit
        if (exemption.getTemporaryExtensionLimit() != null &&
                exemption.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Temporary extension limit must be >= 0");
        }

        // Validate expiry
        if (exemption.getValidUntil() == null ||
                exemption.getValidUntil().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("validUntil must be in the future");
        }

        ApiKey apiKey = apiKeyRepository.findById(exemption.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        exemption.setApiKey(apiKey);

        return exemptionRepository.save(exemption);
    }

    @Override
    public KeyExemption getExemptionById(Long id) {
        return exemptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KeyExemption not found"));
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return exemptionRepository.findAll();
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption updated) {

        KeyExemption existing = getExemptionById(id);

        existing.setNotes(updated.getNotes());
        existing.setUnlimitedAccess(updated.getUnlimitedAccess());
        existing.setTemporaryExtensionLimit(updated.getTemporaryExtensionLimit());
        existing.setValidUntil(updated.getValidUntil());

        return exemptionRepository.save(existing);
    }
}
