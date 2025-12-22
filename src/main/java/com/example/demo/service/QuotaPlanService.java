package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.QuotaPlan;

public interface QuotaPlanService {

    QuotaPlan createQuotaPlan(QuotaPlan plan);

    QuotaPlan updateQuotaPlan(Long id, QuotaPlan plan);

    QuotaPlan getQuotaPlanById(Long id);

    List<QuotaPlan> getAllPlans();

    void deactivateQuotaPlan(Long id);
}
