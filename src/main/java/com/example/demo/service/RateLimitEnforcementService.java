package com.example.demo.service;

import com.example.demo.entity.RateLimitEnforcement;

import java.util.List;

public interface RateLimitEnforcementService {

    RateLimitEnforcement enforce(RateLimitEnforcement enforcement);

    List<RateLimitEnforcement> getForApiKey(Long apiKeyId);
}
