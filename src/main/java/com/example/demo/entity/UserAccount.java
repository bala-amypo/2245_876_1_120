package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_accounts")
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
    @JoinTable(
        name = "user_quota_plans",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "plan_id")
    )
    private Set<QuotaPlan> quotaPlans;

    public UserAccount() {}
}
