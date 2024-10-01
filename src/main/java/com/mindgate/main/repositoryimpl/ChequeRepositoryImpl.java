package com.mindgate.main.repositoryimpl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Cheque;
import com.mindgate.main.repository.ChequeRepository;
import com.mindgate.main.rowmapper.ChequeRowMapper;


@Repository
public class ChequeRepositoryImpl implements ChequeRepository{

    JdbcTemplate jdbcTemplate;

    public ChequeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_NEXT_CHEQUE_NUMBER = "SELECT transaction_id_seq.nextval FROM dual";
    private static final String ADD_CHEQUE = "INSERT INTO cheque_table (cheque_number, from_account_number, to_account_number, cheque_amount,cheque_issue_date) VALUES(?,?,?,?,SYSDATE)";
    private static final String ACCEPT_CHEQUE = "UPDATE cheque_table SET cheque_status = 'ISSUED' , cheque_issue_date = SYSDATE WHERE cheque_number = ?";
    private static final String REJECT_CHEQUE = "DELETE FROM cheque_table WHERE cheque_number = ?";
    private static final String GET_ALL_ISSUED = "SELECT * FROM cheque_table WHERE cheque_status = 'ISSUED'";
    private static final String CLEAR_CHEQUE = "UPDATE cheque_table SET cheque_status = 'CLEARED' , cheque_clearance_date = SYSDATE WHERE cheque_number = ?";
    private static final String BOUNCE_CHEQUE = "UPDATE cheque_table SET cheque_status = 'BOUNCED' WHERE cheque_number = ?";
    private static final String GET_CHEQUE_BY_CHEQUE_NUMBER = "SELECT * FROM cheque_table WHERE cheque_number = ?";

    @Override
    public int getNextChequeNumber() {
        return jdbcTemplate.queryForObject(GET_NEXT_CHEQUE_NUMBER, Integer.class);
    }

    @Override
    public boolean addCheque(int chequeNumber, int fromAccountNumber, int toAccountNumber, double amount) {
        Object[] params = {chequeNumber, fromAccountNumber, toAccountNumber, amount};
        jdbcTemplate.update(ADD_CHEQUE, params);    
        return true;
       
    }

    @Override
    public boolean acceptCheque(int chequeNumber) {
        Object[] params = {chequeNumber};
        jdbcTemplate.update(ACCEPT_CHEQUE, params);
        return true;
    }

    @Override
    public boolean rejectCheque(int chequeNumber) {
        Object[] params = {chequeNumber};
        jdbcTemplate.update(REJECT_CHEQUE, params);
        return true;
    }

    @Override
    public boolean clearCheque(int chequeNumber) {
        Object[] params = {chequeNumber};
        jdbcTemplate.update(CLEAR_CHEQUE, params);
        return true;
    }

    @Override
    public boolean bounceCheque(int chequeNumber) {
        Object[] params = {chequeNumber};
        jdbcTemplate.update(BOUNCE_CHEQUE, params);
        return true;
    }

    @Override
    public Cheque getChequeByChequeNumber(int chequeNumber) {
        Object[] params = {chequeNumber};
        return jdbcTemplate.queryForObject(GET_CHEQUE_BY_CHEQUE_NUMBER, new ChequeRowMapper(), params);
    }

    @Override
    public List<Cheque> getAllCheques(String query, String account, String chequeStatus) {
        String updatedQuery = "" ; 

        updatedQuery = "SELECT * FROM cheque_table WHERE "+ account +" IN ("+query+") AND cheque_status IN " + chequeStatus + ""; 
//        System.out.println(u);
        return jdbcTemplate.query(updatedQuery, new ChequeRowMapper());
    }

    @Override
    public List<Cheque> getAllCheckToClear() {
        return jdbcTemplate.query(GET_ALL_ISSUED, new ChequeRowMapper());
    }
    
}
