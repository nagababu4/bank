package com.transaction.demo.dto;

import com.transaction.demo.entity.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponse {

    private Long transactionId;

    private String fromAccountNumber;

    private String toAccountNumber;

    private Double amount;

    private TransactionType transactionType;

    private LocalDateTime transactionDate;

    private String status;
}