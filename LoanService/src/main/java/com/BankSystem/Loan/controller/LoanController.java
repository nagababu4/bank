package com.BankSystem.Loan.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.BankSystem.Loan.dto.LoanRequest;
import com.BankSystem.Loan.entity.Loan;
import com.BankSystem.Loan.service.LoanService;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService service;

    @PostMapping
    public Loan applyLoan(
            @RequestBody LoanRequest request) {

        return service.applyLoan(request);
    }

    @GetMapping("/{id}")
    public Loan getLoan(
            @PathVariable Long id) {

        return service.getLoan(id);
    }

    @PutMapping("/{id}/approve")
    public Loan approveLoan(
            @PathVariable Long id) {

        return service.approveLoan(id);
    }

    @PutMapping("/{id}/reject")
    public Loan rejectLoan(
            @PathVariable Long id) {

        return service.rejectLoan(id);
    }
}