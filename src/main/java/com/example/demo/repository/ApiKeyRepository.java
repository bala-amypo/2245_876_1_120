package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.ApiKey;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    ApiKey findByKeyValue(String keyValue);
}
