package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Set;

@Table(name="user_accounts");
@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @ManyToMany
    private Set<QuotaPlan> quotaPlans;

    
}
