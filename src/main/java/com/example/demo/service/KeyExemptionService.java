package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.KeyExemption;

public interface KeyExemptionService {

    KeyExemption createExemption(KeyExemption exemption);

    KeyExemption updateExemption(Long id, KeyExemption exemption);

    // ✅ Optional as per spec
    Optional<KeyExemption> getExemptionByKey(Long apiKeyId);

    List<KeyExemption> getAllExemptions();
}
