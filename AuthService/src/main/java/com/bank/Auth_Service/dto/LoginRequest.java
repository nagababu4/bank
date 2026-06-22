package com.bank.Auth_Service.dto;


import lombok.Data;

@Data
public class LoginRequest {

    private String username;

    private String password;
}