package com.bank.Auth_Service.service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.Auth_Service.dto.AuthResponse;
import com.bank.Auth_Service.dto.LoginRequest;
import com.bank.Auth_Service.dto.RegisterRequest;
import com.bank.Auth_Service.entity.User;
import com.bank.Auth_Service.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl
        implements AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(
            RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()))
                .role(request.getRole())
                .build();

        repository.save(user);

        // FIX: Pass role to generateToken
        String token =
                jwtService.generateToken(
                        user.getUsername(),
                        user.getRole().name());

        return new AuthResponse(
                token,
                user.getUsername(),
                user.getRole().name());
    }

    @Override
    public AuthResponse login(
            LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        User user =
                repository.findByUsername(
                                request.getUsername())
                        .orElseThrow();

        // FIX: Pass role to generateToken
        String token =
                jwtService.generateToken(
                        user.getUsername(),
                        user.getRole().name());

        return new AuthResponse(
                token,
                user.getUsername(),
                user.getRole().name());
    }
}
