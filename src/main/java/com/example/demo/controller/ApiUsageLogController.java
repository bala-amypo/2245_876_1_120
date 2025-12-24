package com.example.demo.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.service.ApiUsageLogService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/usage-logs")
public class ApiUsageLogController {

    private final ApiUsageLogService apiUsageLogService;

    public ApiUsageLogController(ApiUsageLogService apiUsageLogService) {
        this.apiUsageLogService = apiUsageLogService;
    }

    @Operation(
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ApiUsageLog.class)
            )
        )
    )
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ApiUsageLog logUsage(
            @org.springframework.web.bind.annotation.RequestBody ApiUsageLog log) {
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
