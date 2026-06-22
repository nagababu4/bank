package com.BankSystem.Loan.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private Long customerId;

    private Double loanAmount;

    private Double annualIncome;

    private Integer tenureMonths;

    private Double emi;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;
}