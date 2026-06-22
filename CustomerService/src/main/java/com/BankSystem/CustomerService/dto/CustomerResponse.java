package com.BankSystem.CustomerService.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

    private Long customerId;
    private String name;
    private String email;
    private String mobile;
    private String address;
    private String panNumber;
}