package com.example.demo.controller;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rate-limit-enforcements")
public class RateLimitEnforcementController {

    private final RateLimitEnforcementService service;

    public RateLimitEnforcementController(
            RateLimitEnforcementService service) {
        this.service = service;
    }

    @PostMapping
    public RateLimitEnforcement enforce(
            @RequestBody RateLimitEnforcement enforcement) {
        return service.enforce(enforcement);
    }

    @GetMapping("/key/{keyId}")
    public List<RateLimitEnforcement> getForKey(
            @PathVariable Long keyId) {
        return service.getForApiKey(keyId);
    }
}
