package com.BankSystem.CustomerService.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerRequest {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String mobile;

    @NotBlank
    private String address;

    @NotBlank
    private String panNumber;
}