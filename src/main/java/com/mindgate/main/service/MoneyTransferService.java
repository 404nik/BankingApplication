package com.mindgate.main.service;

import java.util.List;

import com.mindgate.main.domain.MoneyTransfer;

public interface MoneyTransferService {
    Boolean transferMoney(int fromAccount, int toAccount, double amount);
    void addTransaction(int transactionId, int fromAccount, int toAccount, double amount);
    int generateNextTransactionId();
    List<MoneyTransfer> getTransactionByAccountNumber(int accountNumber);
    Boolean transferMoneyByCheque(int fromAccount, int toAccount, double amount);

}
