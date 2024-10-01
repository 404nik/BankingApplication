package com.mindgate.main.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Current;
import com.mindgate.main.repository.CurrentAccountRepository;
import com.mindgate.main.service.CurrentAccountService;

@Service
public class CurrentAccountServiceImpl implements CurrentAccountService {

    CurrentAccountRepository currentAccountRepository;

    public CurrentAccountServiceImpl(CurrentAccountRepository currentAccountRepository) {
        this.currentAccountRepository = currentAccountRepository;
    }

    @Override
    public Account addAccount(Account account, Current current) {
        return currentAccountRepository.addAccount(account, current);
    }

    @Override
    public Account deleteAccount(int accountNumber) {
        return currentAccountRepository.deleteAccount(accountNumber);
    }

    @Override
    public Account updateAccount(Account account) {
        return currentAccountRepository.updateAccount(account);
    }

    @Override
    public Account getAccountByAccountNumber(int accountNumber) {
        return currentAccountRepository.getAccountByAccountNumber(accountNumber);
    }

    @Override
    public int deposit(int accountNumber, double amount) {
        return currentAccountRepository.deposit(accountNumber, amount);
    }

    @Override
    public int withdraw(int accountNumber, double amount) {
        return currentAccountRepository.withdraw(accountNumber, amount);
    }

    @Override
    public List<Account> getAllAccounts() {
        return currentAccountRepository.getAllAccounts();
    }

    @Override
    public Current getCurrentByAccountNumber(int accountNumber) {
        return currentAccountRepository.getCurrentByAccountNumber(accountNumber);
    }
    
}
