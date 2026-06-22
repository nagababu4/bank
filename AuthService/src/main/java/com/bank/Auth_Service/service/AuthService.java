package com.bank.Auth_Service.service;

import com.bank.Auth_Service.dto.AuthResponse;
import com.bank.Auth_Service.dto.LoginRequest;
import com.bank.Auth_Service.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(
            RegisterRequest request);

    AuthResponse login(
            LoginRequest request);
}