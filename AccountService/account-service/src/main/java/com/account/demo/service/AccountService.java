package com.account.demo.service;

import com.account.demo.dto.AccountRequest;
import com.account.demo.dto.AccountResponse;

import java.util.List;

public interface AccountService {

    AccountResponse createAccount(AccountRequest request);

    AccountResponse getAccountByNumber(String accountNumber);

    List<AccountResponse> getAllAccounts();

    AccountResponse updateAccount(String accountNumber,
                                  AccountRequest request);
}