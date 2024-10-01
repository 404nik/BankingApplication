package com.mindgate.main.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Customer;

public class AccountRowMapper implements RowMapper<Account> {


    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        Customer customer = new Customer();
        account.setCustomer(customer);
        account.setAccountNumber(rs.getInt("account_number"));
        account.getCustomer().setCustomerId(rs.getInt("customer_id"));
        account.setAccountBalance(rs.getDouble("account_balance"));
        account.setAccountType(rs.getString("account_type"));
        account.setAccountStatus(rs.getString("account_status"));
        account.setAccountCreationDate(rs.getDate("account_creation_date").toLocalDate());
        return account;
    }
    
}
