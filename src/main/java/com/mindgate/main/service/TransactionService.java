package com.mindgate.main.service;

import java.util.List;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Transaction;

public interface TransactionService {
    Boolean deposit(Account account, double amount);
    Boolean withdraw(Account account, double amount);
    List<Transaction> getTransactionByAccountNumber(Account account);
}
