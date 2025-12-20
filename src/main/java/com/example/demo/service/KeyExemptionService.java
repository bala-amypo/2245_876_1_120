package com.example.demo.service;

import com.example.demo.entity.KeyExemption;
import java.util.List;

public interface KeyExemptionService {

    KeyExemption createExemption(KeyExemption exemption);

    KeyExemption getExemptionById(Long id);

    List<KeyExemption> getAllExemptions();

    void deleteExemption(Long id);
}
