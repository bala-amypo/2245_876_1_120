package com.example.demo.controller;

import com.example.demo.dto.KeyExemptionDto;
import com.example.demo.service.KeyExemptionService;
import jakarta.validation.Valid;
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
    public KeyExemptionDto create(@RequestBody @Valid KeyExemptionDto dto) {
        return service.createExemption(dto);
    }

    @PutMapping("/{id}")
    public KeyExemptionDto update(@PathVariable Long id,
                                  @RequestBody @Valid KeyExemptionDto dto) {
        return service.updateExemption(id, dto);
    }

    @GetMapping("/key/{keyId}")
    public KeyExemptionDto getByApiKey(@PathVariable Long keyId) {
        return service.getExemptionByKey(keyId);
    }

    @GetMapping
    public List<KeyExemptionDto> getAll() {
        return service.getAllExemptions();
    }
}
