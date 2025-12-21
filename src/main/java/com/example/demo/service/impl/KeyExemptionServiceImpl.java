package com.example.demo.service.impl;

import com.example.demo.dto.KeyExemptionDto;
import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repository;
    private final ApiKeyRepository apiKeyRepository;
    private final ModelMapper mapper;

    public KeyExemptionServiceImpl(KeyExemptionRepository repository,
                                   ApiKeyRepository apiKeyRepository,
                                   ModelMapper mapper) {
        this.repository = repository;
        this.apiKeyRepository = apiKeyRepository;
        this.mapper = mapper;
    }

    @Override
    public KeyExemptionDto createExemption(KeyExemptionDto dto) {

        ApiKey apiKey = apiKeyRepository.findById(dto.getApiKeyId())
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        validate(dto);

        KeyExemption exemption = mapper.map(dto, KeyExemption.class);
        exemption.setApiKey(apiKey);

        return mapper.map(repository.save(exemption), KeyExemptionDto.class);
    }

    @Override
    public KeyExemptionDto updateExemption(Long id, KeyExemptionDto dto) {

        KeyExemption exemption = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exemption not found"));

        validate(dto);

        exemption.setNotes(dto.getNotes());
        exemption.setUnlimitedAccess(dto.getUnlimitedAccess());
        exemption.setTemporaryExtensionLimit(dto.getTemporaryExtensionLimit());
        exemption.setValidUntil(dto.getValidUntil());

        return mapper.map(repository.save(exemption), KeyExemptionDto.class);
    }

    @Override
    public KeyExemptionDto getExemptionByKey(Long apiKeyId) {

        KeyExemption exemption = repository.findByApiKey_Id(apiKeyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No exemption for API key " + apiKeyId));

        return mapper.map(exemption, KeyExemptionDto.class);
    }

    @Override
    public List<KeyExemptionDto> getAllExemptions() {
        return repository.findAll()
                .stream()
                .map(e -> mapper.map(e, KeyExemptionDto.class))
                .toList();
    }

    private void validate(KeyExemptionDto dto) {

        if (Boolean.TRUE.equals(dto.getUnlimitedAccess())
                && dto.getTemporaryExtensionLimit() != null) {
            throw new BadRequestException(
                    "Cannot set both unlimited access and temporary extension");
        }

        if (dto.getValidUntil().before(new Timestamp(System.currentTimeMillis()))) {
            throw new BadRequestException("Valid until must be in the future");
        }
    }
}
