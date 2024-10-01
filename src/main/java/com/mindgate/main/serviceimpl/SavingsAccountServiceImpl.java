package com.mindgate.main.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Saving;
import com.mindgate.main.repository.SavingAccountRepository;
import com.mindgate.main.service.SavingAccountService;

@Service
public class SavingsAccountServiceImpl implements SavingAccountService {

    private SavingAccountRepository savingAccountRepository;

    public SavingsAccountServiceImpl(SavingAccountRepository savingAccountRepository) {
        this.savingAccountRepository = savingAccountRepository;
    }

    @Override
    public Account addAccount(Account account, Saving saving) {
        System.out.println("In addAccount method of SavingsAccountServiceImpl");
        return savingAccountRepository.addAccount(account, saving);
    }

    @Override
    public Account blockAccount(int accountNumber) {
        return savingAccountRepository.blockAccount(accountNumber);
    }

    @Override
    public Account updateAccount(Account account) {
        return savingAccountRepository.updateAccount(account);
    }

    @Override
    public Account getAccountByAccountNumber(int accountNumber) {
        return savingAccountRepository.getAccountByAccountNumber(accountNumber);
    }

    @Override
    public int deposit(int accountNumber, double amount) {
        return savingAccountRepository.deposit(accountNumber, amount);
    }

    @Override
    public int withdraw(int accountNumber, double amount) {
        return savingAccountRepository.withdraw(accountNumber, amount);
    }

    @Override
    public List<Account> getAllAccounts() {
        return savingAccountRepository.getAllAccounts();
    }

    @Override
    public double getMinimumBalance() {
        return savingAccountRepository.getMinimumBalance();
    }

    @Override
    public double getInterestRate() {
        return savingAccountRepository.getInterestRate();
    }

    
}
