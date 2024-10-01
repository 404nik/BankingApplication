package com.mindgate.main.repository;

import java.util.List;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Transaction;

public interface TransactionRepository {

    List<Transaction> getTransactionByAccountNumber(Account account);
    int generateNextTransactionId();
    void addTransaction(int transactionId, int accountNumber, String transactionType, double amount);
}
