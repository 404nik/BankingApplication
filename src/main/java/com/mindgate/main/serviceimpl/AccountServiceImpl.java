package com.mindgate.main.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Customer;
import com.mindgate.main.repository.AccountRepository;
import com.mindgate.main.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    
    private  final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }

    @Override
    public List<Account> getAllNewAccounts() {
        return accountRepository.getAllNewAccounts();
    }

    @Override
    public List<Account> getAllNewAccountsByCusstomerId(int customerId) {
       return accountRepository.getAllNewAccountsByCusstomerId(customerId);
    }

    @Override
    public boolean approveAccount(int accountNumber) {
        return accountRepository.approveAccount(accountNumber);
    }

    @Override
    public boolean rejectAccount(int accountNumber) {
        return accountRepository.rejectAccount(accountNumber);
    }

    @Override
    public Account getAccountByAccountNumber(int accountNumber) {
        return accountRepository.getAccountByAccountNumber(accountNumber);
    }
    
    @Override
    public List<Account> getAllAccounts( Customer customer) {
        System.out.println("AccountServiceImpl.getAllAccounts()");
        return accountRepository.getAllAccounts(customer);
    }
    
}
