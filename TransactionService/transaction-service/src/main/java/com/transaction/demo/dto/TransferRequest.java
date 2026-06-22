package com.transaction.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferRequest {

    @NotBlank(message = "From Account Number is required")
    private String fromAccountNumber;

    @NotBlank(message = "To Account Number is required")
    private String toAccountNumber;

    @NotNull(message = "Amount is required")
    private Double amount;
}