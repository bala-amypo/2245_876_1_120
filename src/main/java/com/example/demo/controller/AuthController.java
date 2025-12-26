package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.*;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponseDto register(
            @RequestBody RegisterRequestDto dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    public AuthResponseDto login(
            @RequestBody AuthRequestDto dto) {
        return authService.login(dto);
    }
}
