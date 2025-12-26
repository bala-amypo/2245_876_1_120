package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.ApiUsageLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository usageRepository;
    private final ApiKeyRepository apiKeyRepository;

    public ApiUsageLogServiceImpl(
            ApiUsageLogRepository usageRepository,
            ApiKeyRepository apiKeyRepository) {
        this.usageRepository = usageRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {

        if (log.getTimestamp().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("Timestamp cannot be in the future");
        }

        ApiKey apiKey = apiKeyRepository.findById(log.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));

        log.setApiKey(apiKey);
        return usageRepository.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        return usageRepository.findByApiKey_Id(keyId);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long keyId) {

        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDateTime.now();

        return usageRepository.findForKeyBetween(keyId, start, end);
    }

    @Override
    public int countRequestsToday(Long keyId) {

        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDateTime.now();

        return usageRepository.countForKeyBetween(keyId, start, end);
    }
}
