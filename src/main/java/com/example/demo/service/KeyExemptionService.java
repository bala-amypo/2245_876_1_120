package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.KeyExemption;

public interface KeyExemptionService {

    KeyExemption createExemption(KeyExemption exemption);

    KeyExemption updateExemption(Long id, KeyExemption exemption);

    // ✅ MUST return SINGLE entity (tests expect this)
    KeyExemption getExemptionByKey(Long apiKeyId);

    List<KeyExemption> getAllExemptions();
}
