package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.ApiKey;

public interface ApiKeyService {

    ApiKey createApiKey(ApiKey key);

    ApiKey updateApiKey(Long id, ApiKey key);

    ApiKey getApiKeyById(Long id);

    ApiKey getApiKeyByValue(String keyValue);

    List<ApiKey> getAllApiKeys();

    void deactivateApiKey(Long id);
}
