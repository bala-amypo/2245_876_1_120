package com.example.demo.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository usageRepo;
    private final ApiKeyRepository apiKeyRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository usageRepo,
                                  ApiKeyRepository apiKeyRepo) {
        this.usageRepo = usageRepo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {

        if (log.getTimestamp().isAfter(java.time.LocalDateTime.now())) {
            throw new BadRequestException("Timestamp cannot be in the future");
        }

        apiKeyRepo.findById(log.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("API Key not found"));

        return usageRepo.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        return usageRepo.findByApiKey_Id(keyId);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long keyId) {

        Instant start = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end = LocalDate.now().atTime(23, 59, 59).toInstant(ZoneOffset.UTC);

        return usageRepo.findForKeyBetween(keyId, start, end);
    }

    @Override
    public int countRequestsToday(Long keyId) {

        Instant start = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end = LocalDate.now().atTime(23, 59, 59).toInstant(ZoneOffset.UTC);

        return usageRepo.countForKeyBetween(keyId, start, end);
    }
}
