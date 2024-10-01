package com.mindgate.main.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Cheque;
import com.mindgate.main.repository.*;
import com.mindgate.main.service.ChequeService;


@Service
public class ChequeServiceImpl implements ChequeService {

    private final ChequeRepository chequeRepository;
    private final AccountRepository accountRepository;

    public ChequeServiceImpl(ChequeRepository chequeRepository, AccountRepository accountRepository) {
        this.chequeRepository = chequeRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public int getNextChequeNumber() {
        return chequeRepository.getNextChequeNumber();
    }

    @Override
    public boolean addCheque(int chequeNumber, int fromAccountNumber, int toAccountNumber, double amount) {
            return chequeRepository.addCheque(chequeNumber, fromAccountNumber, toAccountNumber, amount);
    }

    @Override
    public boolean acceptCheque(int chequeNumber) {
        return chequeRepository.acceptCheque(chequeNumber);
    }

    @Override
    public boolean rejectCheque(int chequeNumber) {
        return chequeRepository.rejectCheque(chequeNumber);
    }

    @Override
    public boolean clearCheque(int chequeNumber) {
        return chequeRepository.clearCheque(chequeNumber);
    }

    @Override
    public boolean bounceCheque(int chequeNumber) {
        return chequeRepository.bounceCheque(chequeNumber);
    }

    @Override
    public Cheque getChequeByChequeNumber(int chequeNumber) {
        return chequeRepository.getChequeByChequeNumber(chequeNumber);
    }

    @Override
    public List<Cheque> getAllCheques(String query, String account, String chequeStatus) {
        return chequeRepository.getAllCheques(query, account, chequeStatus);
    }

    @Override
    public List<Cheque> getAllCheckToClear() {
        return chequeRepository.getAllCheckToClear();
    }
    
}
