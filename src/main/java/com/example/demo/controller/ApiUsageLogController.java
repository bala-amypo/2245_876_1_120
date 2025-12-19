package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.service.ApiUsageLogService;

@RestController
@RequestMapping("/api/usage-logs")
public class ApiUsageLogController {

    @Autowired
    private ApiUsageLogService ser;

    @PostMapping
    public ApiUsageLog logUsage(@RequestBody ApiUsageLog log) {
        return ser.logUsage(log);
    }

    @GetMapping("/key/{keyId}")
    public List<ApiUsageLog> getUsageForKey(@PathVariable Long keyId) {
        return ser.getUsageForApiKey(keyId);
    }

    @GetMapping("/key/{keyId}/today")
    public List<ApiUsageLog> getTodayUsage(@PathVariable Long keyId) {
        return ser.getUsageForToday(keyId);
    }

    @GetMapping("/key/{keyId}/count-today")
    public long countToday(@PathVariable Long keyId) {
        return ser.countRequestsToday(keyId);
    }
}
