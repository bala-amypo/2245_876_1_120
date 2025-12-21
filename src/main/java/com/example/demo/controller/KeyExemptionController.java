package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.KeyExemption;
import com.example.demo.service.KeyExemptionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/key-exemptions")
public class KeyExemptionController {

    private final KeyExemptionService exemptionService;

    public KeyExemptionController(KeyExemptionService exemptionService) {
        this.exemptionService = exemptionService;
    }

    @PostMapping
    @Operation(summary = "Create a Key Exemption")
    @RequestBody(
        description = "KeyExemption request",
        required = true,
        content = @Content(
            schema = @Schema(implementation = KeyExemption.class)
        )
    )
    public KeyExemption createExemption(
            @org.springframework.web.bind.annotation.RequestBody
            KeyExemption exemption) {
        return exemptionService.createExemption(exemption);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Key Exemption")
    @RequestBody(
        description = "Updated KeyExemption",
        required = true,
        content = @Content(
            schema = @Schema(implementation = KeyExemption.class)
        )
    )
    public KeyExemption updateExemption(
            @PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody
            KeyExemption exemption) {
        return exemptionService.updateExemption(id, exemption);
    }

    @GetMapping("/key/{keyId}")
    public Optional<KeyExemption> getExemptionByKey(@PathVariable Long keyId) {
        return exemptionService.getExemptionByKey(keyId);
    }

    @GetMapping
    public List<KeyExemption> getAllExemptions() {
        return exemptionService.getAllExemptions();
    }
}
