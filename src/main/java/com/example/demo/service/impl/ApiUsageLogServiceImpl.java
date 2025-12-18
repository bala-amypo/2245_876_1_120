package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApiUsageLog;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {
        return log;
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        return List.of();
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long keyId) {
        return List.of();
    }

    @Override
    public long countRequestsToday(Long keyId) {
        return 0;
    }
}
