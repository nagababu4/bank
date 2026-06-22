package com.BankSystem.Loan.service;

import com.BankSystem.Loan.dto.LoanRequest;
import com.BankSystem.Loan.entity.Loan;

public interface LoanService {

    Loan applyLoan(LoanRequest request);

    Loan getLoan(Long id);

    Loan approveLoan(Long id);

    Loan rejectLoan(Long id);
}