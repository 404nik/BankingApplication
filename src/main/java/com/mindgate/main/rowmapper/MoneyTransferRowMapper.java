package com.mindgate.main.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.MoneyTransfer;
import com.mindgate.main.domain.Transaction;

public class MoneyTransferRowMapper implements  RowMapper<MoneyTransfer> {

    @Override
    public MoneyTransfer mapRow(ResultSet rs, int rowNum) throws SQLException {
        MoneyTransfer moneyTransfer = new MoneyTransfer();
        Account fromAccount = new Account();
        Account toAccount = new Account();
        Transaction transaction = new Transaction();
        moneyTransfer.setTransaction(transaction);
        moneyTransfer.setFromAccount(fromAccount);
        moneyTransfer.setToAccount(toAccount);
        moneyTransfer.getFromAccount().setAccountNumber(rs.getInt("from_account_number"));
        moneyTransfer.getToAccount().setAccountNumber(rs.getInt("to_account_number"));
        moneyTransfer.setAmount(rs.getDouble("transaction_amount"));
        moneyTransfer.setTransactionStatus(rs.getString("transaction_status"));
        moneyTransfer.getTransaction().setTransactionId(rs.getInt("transaction_id"));
        moneyTransfer.setTransactionDate(rs.getDate("transaction_date").toLocalDate());
        return moneyTransfer;
    }
    
    
}
