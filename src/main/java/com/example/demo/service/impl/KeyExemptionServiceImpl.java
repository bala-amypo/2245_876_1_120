package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;

@Service
@Transactional
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

        if (exemption.getApiKey() == null || exemption.getApiKey().getId() == null) {
            throw new BadRequestException("apiKey.id is required");
        }

        if (exemption.getValidUntil() == null) {
            throw new BadRequestException("validUntil is required");
        }

        ApiKey apiKey = apiKeyRepository.findById(
                exemption.getApiKey().getId()
        ).orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        exemption.setApiKey(apiKey);

        if (exemption.getUnlimitedAccess() == null) {
            exemption.setUnlimitedAccess(false);
        }

        if (exemption.getValidUntil().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("validUntil cannot be in the past");
        }

        return repository.save(exemption);
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption exemption) {

        KeyExemption existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KeyExemption not found"));

        existing.setNotes(exemption.getNotes());
        existing.setUnlimitedAccess(exemption.getUnlimitedAccess());
        existing.setTemporaryExtensionLimit(exemption.getTemporaryExtensionLimit());
        existing.setValidUntil(exemption.getValidUntil());

        return repository.save(existing);
    }

    @Override
    public Optional<KeyExemption> getExemptionByKey(Long keyId) {
        return repository.findByApiKey_Id(keyId);
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return repository.findAll();
    }
}
