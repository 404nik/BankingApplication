package com.mindgate.main.service;

import java.util.List;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Customer;

public interface AccountService {
        
    List<Account> getAllNewAccounts();
    List<Account> getAllNewAccountsByCusstomerId(int customerId);
    List<Account> getAllAccounts(Customer customer);
    boolean approveAccount(int accountNumber);
    boolean rejectAccount(int accountNumber);
    Account getAccountByAccountNumber(int accountNumber);
    
}

