package com.mindgate.main.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.MoneyTransfer;
import com.mindgate.main.repository.AccountRepository;
import com.mindgate.main.repository.CurrentAccountRepository;
import com.mindgate.main.repository.MoneyTransferRepository;
import com.mindgate.main.repository.SavingAccountRepository;
import com.mindgate.main.repository.TransactionRepository;
import com.mindgate.main.service.MoneyTransferService;


@Service
public class MoneyTransferServiceImpl implements MoneyTransferService{

    private MoneyTransferRepository moneyTransferRepository;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private SavingAccountRepository savingAccountRepository;
    private CurrentAccountRepository currentAccountRepository;
    

    public MoneyTransferServiceImpl(MoneyTransferRepository moneyTransferRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, SavingAccountRepository savingAccountRepository, CurrentAccountRepository currentAccountRepository) {
        this.moneyTransferRepository = moneyTransferRepository;
        this.accountRepository = accountRepository;
        this.savingAccountRepository = savingAccountRepository;
        this.currentAccountRepository = currentAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Boolean transferMoney(int fromAccount, int toAccount, double amount) {
        Account from = accountRepository.getAccountByAccountNumber(fromAccount);
        Account to = accountRepository.getAccountByAccountNumber(toAccount);
        if ( from.getAccountType().equals("SAVINGS") && to.getAccountType().equals("SAVINGS") ) {
            int result = savingAccountRepository.withdraw(fromAccount, amount);
            if (result == 0) {
                return false;
            }
            savingAccountRepository.deposit(toAccount, amount);
        }
        else if ( from.getAccountType().equals("CURRENT") && to.getAccountType().equals("CURRENT") ) {
           int result =  currentAccountRepository.withdraw(fromAccount, amount);
            if (result == -1) {
                return false;
            }
            currentAccountRepository.deposit(toAccount, amount);
        }
        else if ( from.getAccountType().equals("SAVINGS") && to.getAccountType().equals("CURRENT") ) {
            int result = savingAccountRepository.withdraw(fromAccount, amount);
            if (result == 0) {
                return false;
            }
            currentAccountRepository.deposit(toAccount, amount);
        }
        else if ( from.getAccountType().equals("CURRENT") && to.getAccountType().equals("SAVINGS") ) {
            int result = currentAccountRepository.withdraw(fromAccount, amount);
            if (result == -1) {
                return false;
            }
            savingAccountRepository.deposit(toAccount, amount);
        }
        System.out.println("In transferMoney method of MoneyTransferServiceImpl");
        addTransaction(0, fromAccount, toAccount, amount);
        return true;
    }
    @Override
    public Boolean transferMoneyByCheque(int fromAccount, int toAccount, double amount) {
        Account from = accountRepository.getAccountByAccountNumber(fromAccount);
        Account to = accountRepository.getAccountByAccountNumber(toAccount);
        if ( from.getAccountType().equals("SAVINGS") && to.getAccountType().equals("SAVINGS") ) {
            int result = savingAccountRepository.withdraw(fromAccount, amount);
            if (result == 0) {
                return false;
            }
            savingAccountRepository.deposit(toAccount, amount);
        }
        else if ( from.getAccountType().equals("CURRENT") && to.getAccountType().equals("CURRENT") ) {
           int result =  currentAccountRepository.withdraw(fromAccount, amount);
            if (result == -1) {
                return false;
            }
            currentAccountRepository.deposit(toAccount, amount);
        }
        else if ( from.getAccountType().equals("SAVINGS") && to.getAccountType().equals("CURRENT") ) {
            int result = savingAccountRepository.withdraw(fromAccount, amount);
            if (result == 0) {
                return false;
            }
            currentAccountRepository.deposit(toAccount, amount);
        }
        else if ( from.getAccountType().equals("CURRENT") && to.getAccountType().equals("SAVINGS") ) {
            int result = currentAccountRepository.withdraw(fromAccount, amount);
            if (result == -1) {
                return false;
            }
            savingAccountRepository.deposit(toAccount, amount);
        }
        System.out.println("In transferMoney method of MoneyTransferServiceImpl");
        return true;
    }

    @Override
    public void addTransaction(int transactionId, int fromAccount, int toAccount, double amount) {
        System.out.println("In addTransaction method of MoneyTransferServiceImpl");
        System.out.println("Transaction ID: " + transactionId);
        transactionId = moneyTransferRepository.generateNextTransactionId();
        System.out.println("Transaction ID New: " + transactionId);
        moneyTransferRepository.addTransaction(transactionId, fromAccount, toAccount, amount);
    }

    @Override
    public int generateNextTransactionId() {
       return moneyTransferRepository.generateNextTransactionId();
    }

    @Override   
    public List<MoneyTransfer> getTransactionByAccountNumber(int accountNumber) {
        return moneyTransferRepository.getTransactionByAccountNumber(accountNumber);
    }
    
}
