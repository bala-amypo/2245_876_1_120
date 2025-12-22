package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.service.ApiUsageLogService;

@RestController
@RequestMapping("/api/usage-logs")
public class ApiUsageLogController {

    private final ApiUsageLogService apiUsageLogService;

    public ApiUsageLogController(ApiUsageLogService apiUsageLogService) {
        this.apiUsageLogService = apiUsageLogService;
    }

    @PostMapping
    public ApiUsageLog logUsage(@RequestBody ApiUsageLog log) {
        return apiUsageLogService.logUsage(log);
    }

    @GetMapping("/key/{keyId}")
    public List<ApiUsageLog> getUsageForApiKey(@PathVariable Long keyId) {
        return apiUsageLogService.getUsageForApiKey(keyId);
    }

    @GetMapping("/key/{keyId}/today")
    public List<ApiUsageLog> getUsageForToday(@PathVariable Long keyId) {
        return apiUsageLogService.getUsageForToday(keyId);
    }

    @GetMapping("/key/{keyId}/count-today")
    public int countRequestsToday(@PathVariable Long keyId) {
        return apiUsageLogService.countRequestsToday(keyId);
    }
}
