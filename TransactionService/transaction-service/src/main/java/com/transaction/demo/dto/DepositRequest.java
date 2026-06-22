package com.transaction.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepositRequest {

    @NotBlank(message = "Account Number is required")
    private String accountNumber;

    @NotNull(message = "Amount is required")
    private Double amount;
}