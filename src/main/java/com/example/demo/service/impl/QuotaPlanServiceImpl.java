package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.QuotaPlan;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {

    @Override
    public QuotaPlan createQuotaPlan(QuotaPlan plan) {
        return plan;
    }

    @Override
    public QuotaPlan updateQuotaPlan(Long id, QuotaPlan plan) {
       
        return plan;
    }

    @Override
    public QuotaPlan getQuotaPlanById(Long id) {
        return new QuotaPlan();
    }

    @Override
    public List<QuotaPlan> getAllPlans() {
        return List.of();
    }

    @Override
    public void deactivateQuotaPlan(Long id) {
      
    }
}
