package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ApiKey;

public interface ApiKeyService {

    ApiKey createApiKey(ApiKey apiKey);

    ApiKey updateApiKey(Long id, ApiKey apiKey);

    ApiKey getApiKeyById(Long id);

    // ✅ MUST return ApiKey (NOT Optional)
    ApiKey getApiKeyByValue(String keyValue);

    List<ApiKey> getAllApiKeys();

    void deactivateApiKey(Long id);
}
