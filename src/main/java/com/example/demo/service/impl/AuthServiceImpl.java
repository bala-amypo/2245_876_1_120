package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

   
    public AuthServiceImpl(
            UserAccountRepository userAccountRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil
    ) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    @Autowired
    public AuthServiceImpl(
            UserAccountRepository userAccountRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

   

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {

        if (userAccountRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        UserAccount user = new UserAccount(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole()
        );

   
        userAccountRepository.save(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", request.getRole());

        String token = jwtUtil.generateToken(claims, request.getEmail());

        return new AuthResponseDto(
                token,
                null,
                request.getEmail(),
                request.getRole()
        );
    }

   

    @Override
    public AuthResponseDto login(AuthRequestDto request) {

        UserAccount user = userAccountRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

  

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());

        String token = jwtUtil.generateToken(claims, user.getEmail());

        return new AuthResponseDto(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}
