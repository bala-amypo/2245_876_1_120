package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repository;

    public KeyExemptionServiceImpl(KeyExemptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {
        return repository.save(exemption);
    }

    @Override
    public KeyExemption getExemptionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KeyExemption not found"));
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return repository.findAll();
    }

    @Override
    public void deleteExemption(Long id) {
        KeyExemption exemption = getExemptionById(id);
        repository.delete(exemption);
    }
}
