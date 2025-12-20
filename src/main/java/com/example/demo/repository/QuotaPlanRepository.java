package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.QuotaPlan;

public interface QuotaPlanRepository extends JpaRepository<QuotaPlan, Long> {
}
