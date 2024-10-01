package com.mindgate.main.repositoryimpl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Transaction;
import com.mindgate.main.repository.TransactionRepository;
import com.mindgate.main.rowmapper.TransactionRowMapper;


@Repository
public class TransactionRepositoryImpl  implements TransactionRepository{

    JdbcTemplate jdbcTemplate;

    public TransactionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GENERATE_NEXT_TRANSACTION_ID = "SELECT transaction_id_seq.nextval FROM dual";
    private static final String ADD_TRANSACTION = "INSERT INTO transaction_table (transaction_id, account_number, transaction_type, transaction_amount, transaction_date, transaction_status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_TRANSACTION_BY_ACCOUNT_NUMBER = "SELECT * FROM transaction_table WHERE account_number = ?";
    // private static final String UPDATE_SAVINGS_ACCOUNT = "UPDATE account SET account_balance = ? WHERE account_number = ?";
    

	@Override
	public List<Transaction> getTransactionByAccountNumber(Account account) {
		Object[] params = { account.getAccountNumber() };
        List<Transaction> transactions = jdbcTemplate.query(GET_TRANSACTION_BY_ACCOUNT_NUMBER, new TransactionRowMapper(), params );
        return transactions;
	}

    @Override
    public int generateNextTransactionId() {
        return jdbcTemplate.queryForObject(GENERATE_NEXT_TRANSACTION_ID, Integer.class);
    }
    
    @Override
    public void addTransaction(int transactionId, int accountNumber, String transactionType, double amount) {
        Object[] params = { transactionId, accountNumber, transactionType, amount, new java.sql.Date(System.currentTimeMillis()), "SUCCESS" };
        jdbcTemplate.update(ADD_TRANSACTION, params);
    }
    
}
