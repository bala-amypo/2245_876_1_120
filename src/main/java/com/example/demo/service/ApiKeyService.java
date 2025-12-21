package com.example.demo.service;

import com.example.demo.entity.ApiKey;

import java.util.List;

public interface ApiKeyService {
    ApiKey create(ApiKey apiKey);
    ApiKey getById(Long id);
    List<ApiKey> getAll();
}
