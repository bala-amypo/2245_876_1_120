package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;

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

        if (exemption.getTemporaryExtensionLimit() != null
                && exemption.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException(
                    "temporaryExtensionLimit must be >= 0");
        }

        ApiKey apiKey = apiKeyRepository.findById(
                exemption.getApiKey().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("ApiKey not found"));

        // 🔥 NORMALIZE SWAGGER REQUEST
        KeyExemption clean = new KeyExemption();
        clean.setApiKey(apiKey);
        clean.setNotes(exemption.getNotes());

        clean.setUnlimitedAccess(
                exemption.getUnlimitedAccess() != null
                        ? exemption.getUnlimitedAccess()
                        : false
        );

        clean.setTemporaryExtensionLimit(
                exemption.getTemporaryExtensionLimit()
        );

        clean.setValidUntil(
                exemption.getValidUntil() != null
                        ? exemption.getValidUntil()
                        : LocalDateTime.now().plusDays(1)
        );

        return exemptionRepository.save(clean);
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption exemption) {

        KeyExemption existing = exemptionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("KeyExemption not found"));

        existing.setNotes(exemption.getNotes());
        existing.setUnlimitedAccess(exemption.getUnlimitedAccess());
        existing.setTemporaryExtensionLimit(
                exemption.getTemporaryExtensionLimit());
        existing.setValidUntil(exemption.getValidUntil());

        return exemptionRepository.save(existing);
    }

    @Override
    public KeyExemption getExemptionByKey(Long apiKeyId) {
        return exemptionRepository.findByApiKey_Id(apiKeyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("KeyExemption not found"));
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return exemptionRepository.findAll();
    }
}
