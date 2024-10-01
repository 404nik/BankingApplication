package com.mindgate.main.repository;

import java.util.List;

// import org.springframework.boot.autoconfigure.web.WebProperties;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.FixedDeposit;

public interface  FixedDepositRepository {

    Account addAccount(Account account, FixedDeposit fixedDeposit);
    Account deleteAccount(int accountNumber);
    Account getAccountByAccountNumber(int accountNumber);
    List<Account> getAllAccounts();
    
}
