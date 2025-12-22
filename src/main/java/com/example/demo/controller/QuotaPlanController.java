package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.service.QuotaPlanService;

@RestController
@RequestMapping("/api/quota-plans")
public class QuotaPlanController {

    private final QuotaPlanService quotaPlanService;

    public QuotaPlanController(QuotaPlanService quotaPlanService) {
        this.quotaPlanService = quotaPlanService;
    }

    @PostMapping
    public QuotaPlan createQuotaPlan(@RequestBody QuotaPlan plan) {
        return quotaPlanService.createQuotaPlan(plan);
    }

    @PutMapping("/{id}")
    public QuotaPlan updateQuotaPlan(@PathVariable Long id,
                                     @RequestBody QuotaPlan plan) {
        return quotaPlanService.updateQuotaPlan(id, plan);
    }

    @GetMapping("/{id}")
    public QuotaPlan getQuotaPlanById(@PathVariable Long id) {
        return quotaPlanService.getQuotaPlanById(id);
    }

    @GetMapping
    public List<QuotaPlan> getAllPlans() {
        return quotaPlanService.getAllPlans();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateQuotaPlan(@PathVariable Long id) {
        quotaPlanService.deactivateQuotaPlan(id);
    }
}
 