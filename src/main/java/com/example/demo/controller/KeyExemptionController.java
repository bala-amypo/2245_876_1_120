package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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

    @PostMapping(consumes = "application/json")
    public KeyExemption createExemption(
            @RequestBody KeyExemption exemption) {
        return exemptionService.createExemption(exemption);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public KeyExemption updateExemption(
            @PathVariable Long id,
            @RequestBody KeyExemption exemption) {
        return exemptionService.updateExemption(id, exemption);
    }

    // ✅ Proper Optional handling
    @GetMapping("/key/{keyId}")
    public ResponseEntity<KeyExemption> getExemptionByKey(
            @PathVariable Long keyId) {

        return exemptionService.getExemptionByKey(keyId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<KeyExemption> getAllExemptions() {
        return exemptionService.getAllExemptions();
    }
}
