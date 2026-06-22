package com.transaction.demo.service;

import com.transaction.demo.dto.*;

import java.util.List;

public interface TransactionService {

	TransactionResponse deposit(DepositRequest request);

	TransactionResponse withdraw(WithdrawRequest request);

	TransactionResponse transfer(TransferRequest request);

	List<TransactionResponse> getTransactions(String accountNumber);
}