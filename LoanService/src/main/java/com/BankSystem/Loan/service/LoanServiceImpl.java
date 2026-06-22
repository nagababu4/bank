package com.BankSystem.Loan.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.BankSystem.Loan.dto.LoanRequest;
import com.BankSystem.Loan.entity.Loan;
import com.BankSystem.Loan.entity.LoanStatus;
import com.BankSystem.Loan.exception.LoanNotFoundException;
import com.BankSystem.Loan.repository.LoanRepository;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl
        implements LoanService {

    private final LoanRepository repository;

    @Override
    public Loan applyLoan(LoanRequest request) {

        if(request.getLoanAmount() >
                request.getAnnualIncome() * 10) {

            throw new RuntimeException(
                    "Loan Amount exceeds eligibility");
        }

        double emi =
                request.getLoanAmount()
                        / request.getTenureMonths();

        Loan loan = Loan.builder()
                .customerId(request.getCustomerId())
                .loanAmount(request.getLoanAmount())
                .annualIncome(request.getAnnualIncome())
                .tenureMonths(request.getTenureMonths())
                .emi(emi)
                .status(LoanStatus.PENDING)
                .build();

        return repository.save(loan);
    }

    @Override
    public Loan getLoan(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new LoanNotFoundException(
                                "Loan not found"));
    }

    @Override
    public Loan approveLoan(Long id) {

        Loan loan = getLoan(id);

        loan.setStatus(
                LoanStatus.APPROVED);

        return repository.save(loan);
    }

    @Override
    public Loan rejectLoan(Long id) {

        Loan loan = getLoan(id);

        loan.setStatus(
                LoanStatus.REJECTED);

        return repository.save(loan);
    }
}