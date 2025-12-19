package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.service.QuotaPlanService;

@RestController
@RequestMapping("/api/quota-plans")
public class QuotaPlanController {

    @Autowired
    private QuotaPlanService ser;

    @PostMapping
    public QuotaPlan createPlan(@RequestBody QuotaPlan plan) {
        return ser.createQuotaPlan(plan);
    }

    @PutMapping("/{id}")
    public QuotaPlan updatePlan(@PathVariable Long id, @RequestBody QuotaPlan plan) {
        return ser.updateQuotaPlan(id, plan);
    }

    @GetMapping("/{id}")
    public QuotaPlan getPlanById(@PathVariable Long id) {
        return ser.getQuotaPlanById(id);
    }

    @GetMapping
    public List<QuotaPlan> getAllPlans() {
        return ser.getAllPlans();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivatePlan(@PathVariable Long id) {
        ser.deactivateQuotaPlan(id);
        return "Quota Plan Deactivated";
    }
}
