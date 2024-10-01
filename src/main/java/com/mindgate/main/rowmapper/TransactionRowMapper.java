package com.mindgate.main.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Transaction;

public class TransactionRowMapper implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        Account account = new Account();
        transaction.setAccount(account);
        transaction.setTransactionId(rs.getInt("transaction_id"));
        transaction.getAccount().setAccountNumber(rs.getInt("account_number"));
        transaction.setAmount(rs.getDouble("transaction_amount"));
        transaction.setTransactionType(rs.getString("transaction_type"));
        transaction.setTransactionDate(rs.getDate("transaction_date").toLocalDate());
        transaction.setTransactionStatus(rs.getString("transaction_status"));
        return transaction;
    }
    
} 
