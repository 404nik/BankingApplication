package com.mindgate.main.repositoryimpl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.MoneyTransfer;
import com.mindgate.main.repository.MoneyTransferRepository;
import com.mindgate.main.rowmapper.MoneyTransferRowMapper;


@Repository
public class MoneyTransferRepositoryImpl implements MoneyTransferRepository{

    private JdbcTemplate jdbcTemplate;

    public MoneyTransferRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String ADD_TRANSACTION = "INSERT INTO money_transfer_table (transaction_id, from_account_number, to_account_number, transaction_amount, transaction_date, transaction_status) VALUES(?,?,?,?,?,?)";
    private static final String GENERATE_NEXT_TRANSACTION_ID = "SELECT transaction_id_seq.nextval FROM dual";
    private static final String GET_TRANSACTION_BY_ACCOUNT_NUMBER = "SELECT * FROM money_transfer_table WHERE from_account_number = ? ";
    
    @Override
    public void addTransaction(int transactionId, int fromAccount, int toAccount, double amount) {
        Object[] params = { transactionId, fromAccount, toAccount, amount, java.time.LocalDate.now(), "SUCCESS" };
        jdbcTemplate.update(ADD_TRANSACTION, params);
    }

    @Override
    public int generateNextTransactionId() {
        return jdbcTemplate.queryForObject(GENERATE_NEXT_TRANSACTION_ID, Integer.class);
    }

    @Override
    public List<MoneyTransfer> getTransactionByAccountNumber(int accountNumber) {
        return jdbcTemplate.query(GET_TRANSACTION_BY_ACCOUNT_NUMBER, new MoneyTransferRowMapper(), new Object[] { accountNumber });
    }
    
}
