package com.mindgate.main.repositoryimpl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.FixedDeposit;
import com.mindgate.main.repository.FixedDepositRepository;
import com.mindgate.main.rowmapper.AccountRowMapper;

@Repository
public class FixedDepositRepositoryImpl implements FixedDepositRepository {

    private JdbcTemplate jdbcTemplate;

    public FixedDepositRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GENERATE_NEW_ACCOUNT_NUMBER = "SELECT account_number_seq.nextval FROM dual";
    private static final String ADD_ACCOUNT = "insert into ACCOUNT_TABLE (account_number, customer_id, account_balance, account_type,  account_creation_date) values(?,?,?,?,?)";
    private static final String ADD_FD_ACCOUNT = "INSERT INTO fixed_deposit_account_table(account_number, account_maturity_period, account_maturity_amount, account_fd_start_date, account_fd_end_date) VALUES(?,?,?,?,?)";
    private static final String BLOCK_ACCOUNT = "update account_table set account_status = 'BLOCKED' where account_number = ?";
    private static final String GET_ACCOUNT_BY_ACCOUNT_NUMBER = "select * from account_table where account_number = ?";
    private static final String GET_ALL_ACCOUNTS = "select * from account_table where account_type = 'FIXED_DEPOSIT'";

    @Override
    public Account addAccount(Account account, FixedDeposit fixedDeposit) {
        System.out.println("In addAccount method of FixedDepositRepositoryImpl");
        int accountNumber = jdbcTemplate.queryForObject(GENERATE_NEW_ACCOUNT_NUMBER, Integer.class);
        account.setAccountNumber(accountNumber);
        System.out.println("Account number generated: " + accountNumber + " for customer: " + account.getCustomer().getCustomerId());
        Object[] params = { account.getAccountNumber(), account.getCustomer().getCustomerId(), account.getAccountBalance(),
                account.getAccountType(), account.getAccountCreationDate() };
        System.out.println("Adding account to account_table");
        jdbcTemplate.update(ADD_ACCOUNT, params);
        System.out.println("Account added to account_table");
        Object[] params1 = { account.getAccountNumber(), fixedDeposit.getMaturityPeriod(),
                fixedDeposit.getMaturityAmount(), fixedDeposit.getStarDate(), fixedDeposit.getEndDate()};
        jdbcTemplate.update(ADD_FD_ACCOUNT, params1); 
        return account;     
    }

    @Override
    public Account deleteAccount(int accountNumber) {
        Object[] params = { accountNumber };
        jdbcTemplate.update(BLOCK_ACCOUNT, params);
        return null;
    }

    @Override
    public Account getAccountByAccountNumber(int accountNumber) {
        Object[] params = { accountNumber };
        return jdbcTemplate.queryForObject(GET_ACCOUNT_BY_ACCOUNT_NUMBER, new AccountRowMapper(), params);
    }

    @Override
    public List<Account> getAllAccounts() {
        return jdbcTemplate.query(GET_ALL_ACCOUNTS, new AccountRowMapper());
    }
    
}
