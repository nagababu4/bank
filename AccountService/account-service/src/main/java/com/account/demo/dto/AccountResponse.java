package com.account.demo.dto;

import com.account.demo.entity.AccountStatus;
import com.account.demo.entity.AccountType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponse {

    private Long accountId;
    private String accountNumber;
    private Long customerId;
    private AccountType accountType;
    private Double balance;
    private AccountStatus status;
}