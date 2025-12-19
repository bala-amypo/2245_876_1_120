package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.service.RateLimitEnforcementService;

@RestController
@RequestMapping("/api/enforcements")
public class RateLimitEnforcementController {

    @Autowired
    private RateLimitEnforcementService ser;

    @PostMapping
    public RateLimitEnforcement createEnforcement(@RequestBody RateLimitEnforcement enforcement) {
        return ser.createEnforcement(enforcement);
    }

    @GetMapping("/{id}")
    public RateLimitEnforcement getById(@PathVariable Long id) {
        return ser.getEnforcementById(id);
    }

    @GetMapping("/key/{keyId}")
    public List<RateLimitEnforcement> getByKey(@PathVariable Long keyId) {
        return ser.getEnforcementsForKey(keyId);
    }
}
