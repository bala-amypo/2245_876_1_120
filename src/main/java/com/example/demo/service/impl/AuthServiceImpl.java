package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.RegisterRequestDto;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String register(RegisterRequestDto request) {
        return "User registered";
    }

    @Override
    public String login(AuthRequestDto request) {
        return "JWT_TOKEN";
    }
}
