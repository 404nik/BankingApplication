package com.mindgate.main.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Transaction;
import com.mindgate.main.repository.CurrentAccountRepository;
import com.mindgate.main.repository.SavingAccountRepository;
import com.mindgate.main.repository.TransactionRepository;
import com.mindgate.main.service.TransactionService;


@Service
public class TransactionServiceImpl implements TransactionService {

    private SavingAccountRepository savingAccountRepository;
    private TransactionRepository transactionRepository;
    private CurrentAccountRepository currentAccountRepository; 

    public TransactionServiceImpl(SavingAccountRepository savingAccountRepository, TransactionRepository transactionRepository, CurrentAccountRepository currentAccountRepository) {
        this.savingAccountRepository = savingAccountRepository;
        this.transactionRepository = transactionRepository;
        this.currentAccountRepository = currentAccountRepository;
    }

    @Override
    public Boolean deposit(Account account, double amount) {
        if (account.getAccountType().equals("SAVINGS")) {
             savingAccountRepository.deposit(account.getAccountNumber(), amount);
        } else if (account.getAccountType().equals("CURRENT")) {
            currentAccountRepository.deposit(account.getAccountNumber(), amount);
        }
        int transactionId = transactionRepository.generateNextTransactionId();
        transactionRepository.addTransaction(transactionId, account.getAccountNumber(), "DEPOSIT", amount);
        return true;
    }

    @Override
    public Boolean withdraw(Account account, double amount) {
        if (account.getAccountType().equals("SAVINGS")) {
            int result = savingAccountRepository.withdraw(account.getAccountNumber(), amount);
            System.out.println("Result: " + result);
            if (result == 0) {
                return false;
            }
            else if (result == 1) {
                int transactionId = transactionRepository.generateNextTransactionId();
                transactionRepository.addTransaction(transactionId, account.getAccountNumber(), "WITHDRAW", amount);
                return true;
            }
        } else if (account.getAccountType().equals("CURRENT")) {
            int result = currentAccountRepository.withdraw(account.getAccountNumber(), amount);
            if (result == -1) {
                return false;
            }
            else if (result == 1) {
                int transactionId = transactionRepository.generateNextTransactionId();
                transactionRepository.addTransaction(transactionId, account.getAccountNumber(), "WITHDRAW", amount);
                return true;
            }
        }

        return true;
    }

    @Override
    public List<Transaction> getTransactionByAccountNumber(Account account) {
        // TODO Auto-generated method stub
        return transactionRepository.getTransactionByAccountNumber(account);
    }
    
}
