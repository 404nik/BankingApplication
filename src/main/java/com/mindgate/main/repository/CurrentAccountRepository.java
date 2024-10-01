package com.mindgate.main.repository;

import java.util.List;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Current;


public interface  CurrentAccountRepository {
    Account addAccount(Account account, Current current);
    Account deleteAccount(int accountNumber);
    Account updateAccount(Account account);
    Account getAccountByAccountNumber(int accountNumber);
    int deposit(int accountNumber, double amount);
    int withdraw(int accountNumber, double amount);
    List<Account> getAllAccounts();
    Current getCurrentByAccountNumber(int accountNumber);
    
}
