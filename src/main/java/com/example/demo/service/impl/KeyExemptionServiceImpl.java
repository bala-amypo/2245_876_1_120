package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.KeyExemption;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {
        return exemption;
    }

    @Override
    public KeyExemption updateExemption(Long id, KeyExemption exemption) {
        
        return exemption;
    }

    @Override
    public KeyExemption getExemptionByKey(Long keyId) {
        return new KeyExemption();
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return List.of();
    }
}
