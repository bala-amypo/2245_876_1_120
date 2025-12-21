package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.KeyExemption;
import com.example.demo.service.KeyExemptionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/key-exemptions")
public class KeyExemptionController {

    private final KeyExemptionService exemptionService;

    public KeyExemptionController(KeyExemptionService exemptionService) {
        this.exemptionService = exemptionService;
    }

    @Operation(
        summary = "Create a key exemption",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = KeyExemption.class)
            )
        )
    )
    @PostMapping
    public KeyExemption createExemption(
            @org.springframework.web.bind.annotation.RequestBody
            KeyExemption exemption) {
        return exemptionService.createExemption(exemption);
    }

    @Operation(
        summary = "Update a key exemption",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = KeyExemption.class)
            )
        )
    )
    @PutMapping("/{id}")
    public KeyExemption updateExemption(
            @PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody
            KeyExemption exemption) {
        return exemptionService.updateExemption(id, exemption);
    }

    @Operation(summary = "Get exemption for API key")
    @GetMapping("/key/{keyId}")
    public Optional<KeyExemption> getExemptionByKey(@PathVariable Long keyId) {
        return exemptionService.getExemptionByKey(keyId);
    }

    @Operation(summary = "Get all exemptions")
    @GetMapping
    public List<KeyExemption> getAllExemptions() {
        return exemptionService.getAllExemptions();
    }
}
