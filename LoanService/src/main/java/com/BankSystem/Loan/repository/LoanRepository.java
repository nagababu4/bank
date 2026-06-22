package com.BankSystem.Loan.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.BankSystem.Loan.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}