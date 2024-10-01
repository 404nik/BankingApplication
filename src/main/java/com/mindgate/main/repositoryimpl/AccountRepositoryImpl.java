package com.mindgate.main.repositoryimpl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Customer;
import com.mindgate.main.repository.AccountRepository;
import com.mindgate.main.rowmapper.AccountRowMapper;
import com.mindgate.main.rowmapper.AccountRowMapperr;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    JdbcTemplate jdbcTemplate;

    public AccountRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_ACCOUNT_BY_ACCOUNT_NUMBER = "SELECT * FROM account_table WHERE account_number = ?";
    private static final String GET_ALL_NEW_ACCOUNTS = "SELECT * FROM account_table a JOIN customer_table c ON a.customer_id = c.customer_id WHERE a.account_status = 'NEW' AND c.customer_account_status = 'ACTIVE'";
    private static final String GET_ALL_ACCOUNTS_OF_CUSTOMER = "SELECT * FROM account_table WHERE customer_id = ? AND (account_type = 'SAVINGS' OR account_type = 'CURRENT')" ;
    private static final String GET_ALL_NEW_ACCOUNTS_BY_CUSTOMER_ID = "SELECT * FROM account_table WHERE customer_id = ? AND account_status = 'NEW'";
    private static final String APPROVE_ACCOUNT = "UPDATE account_table SET account_status = 'ACTIVE' WHERE account_number = ?";
    private static final String BLOCK_ACCOUNT = "UPDATE account_table SET account_status = 'BLOCKED' WHERE account_number = ?";
    private static final String DELETE_CURRENT_ACCOUNT = "DELETE FROM current_account_table WHERE account_number = ?";
    private static final String DELETE_SAVING_ACCOUNT = "DELETE FROM savings_account_table WHERE account_number = ?";
    private static final String DELETE_FD_ACCOUNT = "DELETE FROM fixed_deposit_account_table WHERE account_number = ?";
    private static final String DELETE_ACCOUNT = "DELETE FROM account_table WHERE account_number = ?";


    @Override
    public List<Account> getAllNewAccounts() {
        return jdbcTemplate.query(GET_ALL_NEW_ACCOUNTS, new AccountRowMapper());
    }

    @Override
    public List<Account> getAllNewAccountsByCusstomerId(int customerId) {
        Object[] params = { customerId };
        return jdbcTemplate.query(GET_ALL_NEW_ACCOUNTS_BY_CUSTOMER_ID, new AccountRowMapper(), params);
    }

    @Override
    public Account getAccountByAccountNumber(int accountNumber) {
        Object[] params = { accountNumber };
        return jdbcTemplate.queryForObject(GET_ACCOUNT_BY_ACCOUNT_NUMBER, new AccountRowMapperr(), params);
    }

    @Override
    public boolean  approveAccount(int accountNumber) {
        Object[] params = { accountNumber };
        jdbcTemplate.update(APPROVE_ACCOUNT, params);
        return true;
    }

    @Override
    public boolean rejectAccount(int accountNumber) {
        Object[] params = { accountNumber };
        Account account = getAccountByAccountNumber(accountNumber); 
        System.out.println(account.getAccountType());
        if (account.getAccountType().equals("CURRENT")) {
            jdbcTemplate.update(DELETE_CURRENT_ACCOUNT, params);
        } else if (account.getAccountType().equals("SAVINGS")) {
            jdbcTemplate.update(DELETE_SAVING_ACCOUNT, params);
        } else if (account.getAccountType().equals("FIXEDDEPOSIT")) {
            jdbcTemplate.update(DELETE_FD_ACCOUNT, params);
        }
        jdbcTemplate.update(DELETE_ACCOUNT, params);

        return true;
    }

    @Override
    public List<Account> getAllAccounts(Customer customer) {
        System.out.println("AccountRepositoryImpl.getAllAccounts()");
        Object[] params = { customer.getCustomerId() };
        return jdbcTemplate.query(GET_ALL_ACCOUNTS_OF_CUSTOMER, new AccountRowMapper(), params);
    }

    
}
