package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.service.ApiKeyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository repository;

    public ApiKeyServiceImpl(ApiKeyRepository repository) {
        this.repository = repository;
    }

    @Override
    public ApiKey create(ApiKey apiKey) {
        apiKey.setCreatedAt(LocalDateTime.now());
        apiKey.setUpdatedAt(LocalDateTime.now());
        return repository.save(apiKey);
    }

    @Override
    public ApiKey getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ApiKey not found"));
    }

    @Override
    public List<ApiKey> getAll() {
        return repository.findAll();
    }
}
