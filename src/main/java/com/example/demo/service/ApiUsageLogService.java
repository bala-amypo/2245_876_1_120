package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.ApiUsageLog;

public interface ApiUsageLogService {

    ApiUsageLog logUsage(ApiUsageLog log);

    List<ApiUsageLog> getUsageForApiKey(Long keyId);

    List<ApiUsageLog> getUsageForToday(Long keyId);

    int countRequestsToday(Long keyId);
}
