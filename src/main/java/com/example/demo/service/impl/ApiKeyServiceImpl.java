package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApiKey;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    @Override
    public ApiKey createApiKey(ApiKey key) {
        return key;
    }

    @Override
    public ApiKey updateApiKey(Long id, ApiKey key) {
        key.setId(id);
        return key;
    }

    @Override
    public ApiKey getApiKeyById(Long id) {
        return new ApiKey();
    }

    @Override
    public ApiKey getApiKeyByValue(String keyValue) {
        return new ApiKey();
    }

    @Override
    public List<ApiKey> getAllApiKeys() {
        return List.of();
    }

    @Override
    public void deactivateApiKey(Long id) {
        
    }
}
