package com.transaction.demo.service;

import com.transaction.demo.dto.*;
import com.transaction.demo.entity.Transaction;
import com.transaction.demo.entity.TransactionType;
import com.transaction.demo.exception.*;
import com.transaction.demo.feign.AccountFeignClient;
import com.transaction.demo.feign.BeneficiaryFeignClient;
import com.transaction.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl
        implements TransactionService {

    private final TransactionRepository repository;
    private final AccountFeignClient accountFeignClient;
    private final BeneficiaryFeignClient beneficiaryFeignClient;

    @Override
    public TransactionResponse deposit(
            DepositRequest request) {

        AccountResponse account =
                accountFeignClient.getAccount(
                        request.getAccountNumber());

        account.setBalance(
                account.getBalance() + request.getAmount());

        accountFeignClient.updateAccount(
                account.getAccountNumber(),
                account);

        Transaction transaction =
                Transaction.builder()
                        .fromAccountNumber(
                                request.getAccountNumber())
                        .amount(request.getAmount())
                        .transactionType(
                                TransactionType.DEPOSIT)
                        .transactionDate(
                                LocalDateTime.now())
                        .status("SUCCESS")
                        .build();

        return map(repository.save(transaction));
    }

    @Override
    public TransactionResponse withdraw(
            WithdrawRequest request) {

        AccountResponse account =
                accountFeignClient.getAccount(
                        request.getAccountNumber());

        if (account.getBalance() < request.getAmount()) {

            throw new InsufficientBalanceException(
                    "Insufficient Balance");
        }

        account.setBalance(
                account.getBalance() - request.getAmount());

        accountFeignClient.updateAccount(
                account.getAccountNumber(),
                account);

        Transaction transaction =
                Transaction.builder()
                        .fromAccountNumber(
                                request.getAccountNumber())
                        .amount(request.getAmount())
                        .transactionType(
                                TransactionType.WITHDRAW)
                        .transactionDate(
                                LocalDateTime.now())
                        .status("SUCCESS")
                        .build();

        return map(repository.save(transaction));
    }

    @Override
    public TransactionResponse transfer(
            TransferRequest request) {

        Boolean beneficiaryExists =
                beneficiaryFeignClient
                        .validateBeneficiary(
                                request.getToAccountNumber());

        if (!beneficiaryExists) {

            throw new BeneficiaryNotFoundException(
                    "Beneficiary Not Found");
        }

        AccountResponse account =
                accountFeignClient.getAccount(
                        request.getFromAccountNumber());

        if (account.getBalance() < request.getAmount()) {

            throw new InsufficientBalanceException(
                    "Insufficient Balance");
        }

        account.setBalance(
                account.getBalance() - request.getAmount());

        accountFeignClient.updateAccount(
                account.getAccountNumber(),
                account);

        Transaction transaction =
                Transaction.builder()
                        .fromAccountNumber(
                                request.getFromAccountNumber())
                        .toAccountNumber(
                                request.getToAccountNumber())
                        .amount(request.getAmount())
                        .transactionType(
                                TransactionType.TRANSFER)
                        .transactionDate(
                                LocalDateTime.now())
                        .status("SUCCESS")
                        .build();

        return map(repository.save(transaction));
    }

    @Override
    public List<TransactionResponse> getTransactions(
            String accountNumber) {

        return repository
                .findByFromAccountNumber(accountNumber)
                .stream()
                .map(this::map)
                .toList();
    }

    private TransactionResponse map(
            Transaction transaction) {

        return TransactionResponse.builder()
                .transactionId(
                        transaction.getTransactionId())
                .fromAccountNumber(
                        transaction.getFromAccountNumber())
                .toAccountNumber(
                        transaction.getToAccountNumber())
                .amount(
                        transaction.getAmount())
                .transactionType(
                        transaction.getTransactionType())
                .transactionDate(
                        transaction.getTransactionDate())
                .status(
                        transaction.getStatus())
                .build();
    }
}