package com.example.demo.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.service.RateLimitEnforcementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/enforcements")
public class RateLimitEnforcementController {

    private final RateLimitEnforcementService enforcementService;

    public RateLimitEnforcementController(
            RateLimitEnforcementService enforcementService) {
        this.enforcementService = enforcementService;
    }

    @Operation(
        summary = "Create rate limit enforcement",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = RateLimitEnforcement.class)
            )
        )
    )
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public RateLimitEnforcement createEnforcement(
            @org.springframework.web.bind.annotation.RequestBody
            RateLimitEnforcement enforcement) {
        return enforcementService.createEnforcement(enforcement);
    }

    @GetMapping("/{id}")
    public RateLimitEnforcement getEnforcementById(
            @PathVariable Long id) {
        return enforcementService.getEnforcementById(id);
    }

    @GetMapping("/key/{keyId}")
    public List<RateLimitEnforcement> getEnforcementsForKey(
            @PathVariable Long keyId) {
        return enforcementService.getEnforcementsForKey(keyId);
    }
}
