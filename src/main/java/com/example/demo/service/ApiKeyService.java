package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.ApiKey;

public interface ApiKeyService {

    ApiKey createApiKey(ApiKey apiKey);

    ApiKey updateApiKey(Long id, ApiKey apiKey);

    ApiKey getApiKeyById(Long id);

    Optional<ApiKey> getApiKeyByValue(String keyValue);

    List<ApiKey> getAllApiKeys();

    void deactivateApiKey(Long id);
}
