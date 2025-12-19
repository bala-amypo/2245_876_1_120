package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repo;
    private final ApiKeyRepository apiKeyRepo;

    public KeyExemptionServiceImpl(
            KeyExemptionRepository repo,
            ApiKeyRepository apiKeyRepo) {
        this.repo = repo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {

        if (exemption.getTemporaryExtensionLimit() != null &&
            exemption.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Invalid extension limit");
        }

        if (exemption.getValidUntil().isBefore(java.time.LocalDateTime.now())) {
            throw new BadRequestException("validUntil must be in the future");
        }

        apiKeyRepo.findById(exemption.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        return repo.save(exemption);
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption exemption) {

        KeyExemption existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exemption not found"));

        existing.setNotes(exemption.getNotes());
        existing.setUnlimitedAccess(exemption.getUnlimitedAccess());
        existing.setTemporaryExtensionLimit(exemption.getTemporaryExtensionLimit());
        existing.setValidUntil(exemption.getValidUntil());

        return repo.save(existing);
    }

    @Override
    public Optional<KeyExemption> getExemptionByKey(Long apiKeyId) {
        return repo.findByApiKey_Id(apiKeyId);
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return repo.findAll();
    }
}
