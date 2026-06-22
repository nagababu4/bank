package com.BankSystem.Loan.dto;


import lombok.Data;

@Data
public class LoanRequest {

    private Long customerId;

    private Double loanAmount;

    private Double annualIncome;

    private Integer tenureMonths;
}
