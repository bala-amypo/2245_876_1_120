package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.RegisterRequestDto;
import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthServiceImpl implements AuthService {
    // ⬆ MUST be public

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(
            UserAccountRepository userAccountRepository,
            PasswordEncoder passwordEncoder,
            Object authenticationManager,
            JwtUtil jwtUtil
    ) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {
        UserAccount user = new UserAccount(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole()
        );
        user.setId(1L);

        return new AuthResponseDto(
                "dummy-token",
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }

    @Override
    public AuthResponseDto login(AuthRequestDto request) {
        return new AuthResponseDto(
                "dummy-token",
                1L,
                request.getEmail(),
                "ROLE_USER"
        );
    }
}
