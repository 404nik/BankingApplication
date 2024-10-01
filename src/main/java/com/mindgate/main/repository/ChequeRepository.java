package com.mindgate.main.repository;

import java.time.LocalDate;
import java.util.List;

import com.mindgate.main.domain.Cheque;

import jakarta.validation.constraints.AssertFalse;

public interface ChequeRepository {

    int getNextChequeNumber();
    boolean addCheque(int chequeNumber, int fromAccountNumber, int toAccountNumber, double amount);
    boolean acceptCheque(int chequeNumber);
    boolean rejectCheque(int chequeNumber);
    boolean clearCheque(int chequeNumber);
    boolean bounceCheque(int chequeNumber);
    Cheque getChequeByChequeNumber(int chequeNumber);
    List<Cheque> getAllCheques(String query, String account, String chequeStatus);
    List<Cheque> getAllCheckToClear();

}
