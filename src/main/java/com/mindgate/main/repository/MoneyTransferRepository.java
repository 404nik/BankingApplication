package com.mindgate.main.repository;

import com.mindgate.main.domain.MoneyTransfer;
import java.util.List;

public interface MoneyTransferRepository {
    void addTransaction(int transactionId, int fromAccount, int toAccount, double amount);
    int generateNextTransactionId();
    List<MoneyTransfer> getTransactionByAccountNumber(int accountNumber);
}
