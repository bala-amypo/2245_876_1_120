package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {

    private final QuotaPlanRepository repo;

    public QuotaPlanServiceImpl(QuotaPlanRepository repo) {
        this.repo = repo;
    }

    @Override
    public QuotaPlan createQuotaPlan(QuotaPlan plan) {

        if (plan.getDailyLimit() <= 0) {
            throw new BadRequestException("Daily limit must be greater than zero");
        }

        return repo.save(plan);
    }

    @Override
    public QuotaPlan updateQuotaPlan(Long id, QuotaPlan plan) {

        QuotaPlan existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        if (plan.getDailyLimit() <= 0) {
            throw new BadRequestException("Invalid daily limit");
        }

        existing.setPlanName(plan.getPlanName());
        existing.setDailyLimit(plan.getDailyLimit());
        existing.setDescription(plan.getDescription());
        existing.setActive(plan.getActive());

        return repo.save(existing);
    }

    @Override
    public QuotaPlan getQuotaPlanById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
    }

    @Override
    public List<QuotaPlan> getAllPlans() {
        return repo.findAll();
    }

    @Override
    public void deactivateQuotaPlan(Long id) {

        QuotaPlan plan = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        plan.setActive(false);
        repo.save(plan);
    }
}
