package com.transaction.demo.repository;

import com.transaction.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findByFromAccountNumber(
            String fromAccountNumber);

    List<Transaction> findByToAccountNumber(
            String toAccountNumber);
}