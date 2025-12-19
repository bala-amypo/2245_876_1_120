package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository usageRepository;
    private final ApiKeyRepository apiKeyRepository;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository usageRepository,
                                  ApiKeyRepository apiKeyRepository) {
        this.usageRepository = usageRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {

        if (log.getTimestamp().isAfter(Instant.now())) {
            throw new BadRequestException("Timestamp cannot be in the future");
        }

        apiKeyRepository.findById(log.getApiKey().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("ApiKey not found"));

        return usageRepository.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        return usageRepository.findByApiKey_Id(keyId);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long keyId) {

        Instant start = LocalDate.now()
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC);

        Instant end = LocalDate.now()
                .atTime(23, 59, 59)
                .toInstant(ZoneOffset.UTC);

        return usageRepository.findForKeyBetween(keyId, start, end);
    }

    @Override
    public int countRequestsToday(Long keyId) {

        Instant start = LocalDate.now()
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC);

        Instant end = LocalDate.now()
                .atTime(23, 59, 59)
                .toInstant(ZoneOffset.UTC);

        return usageRepository.countForKeyBetween(keyId, start, end);
    }
}
