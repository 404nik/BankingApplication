package com.mindgate.main.service;

import java.util.List;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Saving;

public interface SavingAccountService {
    Account addAccount(Account account, Saving saving);
    Account blockAccount(int accountNumber);
    Account updateAccount(Account account);
    Account getAccountByAccountNumber(int accountNumber);
    int deposit(int accountNumber, double amount);
    int withdraw(int accountNumber, double amount);
    List<Account> getAllAccounts();
    double getMinimumBalance();
    double getInterestRate();

}
