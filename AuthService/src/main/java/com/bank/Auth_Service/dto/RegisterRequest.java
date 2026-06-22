package com.bank.Auth_Service.dto;
import com.bank.Auth_Service.entity.Role;

import lombok.Data;

@Data
public class RegisterRequest {

    private String username;

    private String email;

    private String password;

    private Role role;
}