package com.example.demo.entity;

import jakarta.persistence.*;

@Table(name="quota_plans");
@Entity
public class QuotaPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String planName;

    @Column(nullable = false)
    private Integer dailyLimit;

    private String description;

    @Column(nullable = false)
    private Boolean active = true;

    
}
