package com.mindgate.main.serviceimpl;

import java.util.List;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.FixedDeposit;
import com.mindgate.main.repository.FixedDepositRepository;
import com.mindgate.main.service.FixedDepositService;

@Service
public class FixedDepositServiceImpl implements FixedDepositService {

    FixedDepositRepository fixedDepositRepository;

    public FixedDepositServiceImpl(FixedDepositRepository fixedDepositRepository) {
        this.fixedDepositRepository = fixedDepositRepository;
    }

    @Override
    public Account addAccount(Account account, FixedDeposit fixedDeposit) {
        return fixedDepositRepository.addAccount(account, fixedDeposit);
    }

    @Override
    public Account deleteAccount(int accountNumber) {
        return fixedDepositRepository.deleteAccount(accountNumber);
    }

    @Override
    public Account getAccountByAccountNumber(int accountNumber) {
        return fixedDepositRepository.getAccountByAccountNumber(accountNumber);
    }

    @Override
    public List<Account> getAllAccounts() {
        return fixedDepositRepository.getAllAccounts();
    }
    
}
