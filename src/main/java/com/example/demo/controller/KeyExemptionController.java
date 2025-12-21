package com.example.demo.controller;

import com.example.demo.entity.KeyExemption;
import com.example.demo.service.KeyExemptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/key-exemptions")
public class KeyExemptionController {

    private final KeyExemptionService service;

    public KeyExemptionController(KeyExemptionService service) {
        this.service = service;
    }

    @PostMapping
    public KeyExemption create(@RequestBody KeyExemption exemption) {
        return service.createExemption(exemption);
    }

    @GetMapping("/{id}")
    public KeyExemption getById(@PathVariable Long id) {
        return service.getExemptionById(id);
    }

    @GetMapping
    public List<KeyExemption> getAll() {
        return service.getAllExemptions();
    }

    @PutMapping("/{id}")
    public KeyExemption update(@PathVariable Long id, @RequestBody KeyExemption exemption) {
        return service.updateExemption(id, exemption);
    }
}
