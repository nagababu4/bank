package com.account.demo.service;

import com.account.demo.dto.AccountRequest;
import com.account.demo.dto.AccountResponse;
import com.account.demo.entity.Account;
import com.account.demo.entity.AccountStatus;
import com.account.demo.entity.AccountType;
import com.account.demo.exception.AccountNotFoundException;
import com.account.demo.exception.InvalidAccountException;
import com.account.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountResponse createAccount(AccountRequest request) {

        validateMinimumBalance(request);

        Account account = Account.builder()
                .accountNumber(generateAccountNumber())
                .customerId(request.getCustomerId())
                .accountType(request.getAccountType())
                .balance(request.getBalance())
                .status(AccountStatus.ACTIVE)
                .build();

        return mapToResponse(accountRepository.save(account));
    }

    @Override
    public AccountResponse getAccountByNumber(String accountNumber) {

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new AccountNotFoundException(
                                "Account not found"));

        return mapToResponse(account);
    }

    @Override
    public List<AccountResponse> getAllAccounts() {

        return accountRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AccountResponse updateAccount(
            String accountNumber,
            AccountRequest request) {

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new AccountNotFoundException(
                                "Account not found"));

        validateMinimumBalance(request);

        account.setCustomerId(request.getCustomerId());
        account.setAccountType(request.getAccountType());
        account.setBalance(request.getBalance());

        return mapToResponse(
                accountRepository.save(account));
    }

    private void validateMinimumBalance(
            AccountRequest request) {

        if (request.getAccountType() == AccountType.SAVINGS
                && request.getBalance() < 1000) {

            throw new InvalidAccountException(
                    "Savings account minimum balance is 1000");
        }

        if (request.getAccountType() == AccountType.CURRENT
                && request.getBalance() < 5000) {

            throw new InvalidAccountException(
                    "Current account minimum balance is 5000");
        }
    }

    private String generateAccountNumber() {

        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 12);
    }

    private AccountResponse mapToResponse(
            Account account) {

        return AccountResponse.builder()
                .accountId(account.getAccountId())
                .accountNumber(account.getAccountNumber())
                .customerId(account.getCustomerId())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .status(account.getStatus())
                .build();
    }
}