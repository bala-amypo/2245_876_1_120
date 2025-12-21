package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.KeyExemption;
import com.example.demo.service.KeyExemptionService;

@RestController
@RequestMapping("/api/key-exemptions")
public class KeyExemptionController {

    private final KeyExemptionService exemptionService;

    public KeyExemptionController(KeyExemptionService exemptionService) {
        this.exemptionService = exemptionService;
    }

    // =========================
    // CREATE EXEMPTION
    // =========================
    @PostMapping(consumes = "application/json")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Create a Key Exemption",
        required = true
    )
    public KeyExemption createExemption(
            @org.springframework.web.bind.annotation.RequestBody
            KeyExemption exemption) {

        return exemptionService.createExemption(exemption);
    }

    // =========================
    // UPDATE EXEMPTION
    // =========================
    @PutMapping(value = "/{id}", consumes = "application/json")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Update an existing Key Exemption",
        required = true
    )
    public KeyExemption updateExemption(
            @PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody
            KeyExemption exemption) {

        return exemptionService.updateExemption(id, exemption);
    }

    // =========================
    // GET EXEMPTION BY API KEY
    // =========================
    @GetMapping("/key/{keyId}")
    public Optional<KeyExemption> getExemptionByKey(
            @PathVariable Long keyId) {

        return exemptionService.getExemptionByKey(keyId);
    }

    // =========================
    // GET ALL EXEMPTIONS
    // =========================
    @GetMapping
    public List<KeyExemption> getAllExemptions() {
        return exemptionService.getAllExemptions();
    }
}
