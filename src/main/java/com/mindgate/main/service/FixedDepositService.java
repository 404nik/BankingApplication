package com.mindgate.main.service;

import java.util.List;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.FixedDeposit;

public interface FixedDepositService {

    Account addAccount(Account account, FixedDeposit fixedDeposit);
    Account deleteAccount(int accountNumber);
    Account getAccountByAccountNumber(int accountNumber);
    List<Account> getAllAccounts();

}
