package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.KeyExemption;
import com.example.demo.service.KeyExemptionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/key-exemptions")
public class KeyExemptionController {

    private final KeyExemptionService exemptionService;

    public KeyExemptionController(KeyExemptionService exemptionService) {
        this.exemptionService = exemptionService;
    }

    @PostMapping(consumes = "application/json")
    public KeyExemption createExemption(
            @RequestBody(
                required = true,
                content = @Content(schema = @Schema(implementation = KeyExemption.class))
            )
            @org.springframework.web.bind.annotation.RequestBody
            KeyExemption exemption) {

        return exemptionService.createExemption(exemption);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public KeyExemption updateExemption(
            @PathVariable Long id,
            @RequestBody(
                required = true,
                content = @Content(schema = @Schema(implementation = KeyExemption.class))
            )
            @org.springframework.web.bind.annotation.RequestBody
            KeyExemption exemption) {

        return exemptionService.updateExemption(id, exemption);
    }

    @GetMapping("/key/{keyId}")
    public KeyExemption getExemptionByKey(@PathVariable Long keyId) {
        return exemptionService.getExemptionByKey(keyId);
    }

    @GetMapping
    public List<KeyExemption> getAllExemptions() {
        return exemptionService.getAllExemptions();
    }
}
