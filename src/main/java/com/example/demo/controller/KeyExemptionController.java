package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import com.example.demo.entity.KeyExemption;
import com.example.demo.service.KeyExemptionService;

@RestController
@RequestMapping("/api/key-exemptions")
public class KeyExemptionController {

    private final KeyExemptionService exemptionService;

    public KeyExemptionController(KeyExemptionService exemptionService) {
        this.exemptionService = exemptionService;
    }

    @Operation(summary = "Create a key exemption")
    @PostMapping
    public KeyExemption createExemption(
            @RequestBody(description = "Key exemption details", required = true)
            @org.springframework.web.bind.annotation.RequestBody
            KeyExemption exemption) {
        return exemptionService.createExemption(exemption);
    }

    @Operation(summary = "Update a key exemption")
    @PutMapping("/{id}")
    public KeyExemption updateExemption(
            @PathVariable Long id,
            @RequestBody(description = "Updated exemption details", required = true)
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
