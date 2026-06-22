package com.transaction.demo.dto;

import lombok.Data;

@Data
public class AccountResponse {

    private Long accountId;

    private String accountNumber;

    private Long customerId;

    private String accountType;

    private Double balance;

    private String status;
}