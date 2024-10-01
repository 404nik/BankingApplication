package com.mindgate.main.repositoryimpl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Constants;
import com.mindgate.main.domain.Saving;
import com.mindgate.main.repository.SavingAccountRepository;
import com.mindgate.main.rowmapper.AccountRowMapper;


@Repository
public class SavingAccountRepositoryImpl implements SavingAccountRepository {

    Constants constants = new Constants();
    private JdbcTemplate jdbcTemplate;

    public SavingAccountRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GENERATE_NEXT_ACCOUNT_NUMBER = "SELECT account_number_seq.nextval FROM dual";
    private static final String ADD_ACCOUNT = "INSERT INTO account_table (account_number, customer_id, account_balance, account_type, account_creation_date) VALUES(?,?,?,?,?)";
    private static final String ADD_SAVINGS_ACCOUNT = "INSERT INTO savings_account_table (account_number) VALUES(?)";
    private static final String BLOCK_ACCOUNT = "UPDATE account_table SET account_status = 'BLOCKED' WHERE account_number = ?";
    private static final String UPDATE_ACCOUNT = "UPDATE account_table SET account_balance = ? WHERE account_number = ?";
    private static final String GET_ACCOUNT_BY_ACCOUNT_NUMBER = "SELECT * FROM account_table WHERE account_number = ?";
    private static final String DEPOSIT = "UPDATE account_table SET account_balance = ? WHERE account_number = ?";
    private static final String WITHDRAW = "UPDATE account_table SET account_balance = ? WHERE account_number = ?";
    private static final String GET_ALL_ACCOUNTS = "SELECT * FROM account_table WHERE account_type = 'SAVINGS'";
    private static final String GET_MINIMUM_BALANCE = "SELECT account_min_balance FROM savings_account_table WHERE account_number = ?";
    private static final String GET_INTEREST_RATE = "SELECT account_interest_rate FROM savings_account_table WHERE account_number = ?";


    @Override
    public Account addAccount(Account account, Saving saving) {
        System.out.println("In addAccount method of SavingAccountRepositoryImpl");
        int accountNumber = jdbcTemplate.queryForObject(GENERATE_NEXT_ACCOUNT_NUMBER, Integer.class);
        account.setAccountNumber(accountNumber);
        System.out.println("Account number generated: " + accountNumber + " for customer: " + account.getCustomer().getCustomerId());
        Object[] params = { account.getAccountNumber(), account.getCustomer().getCustomerId(), account.getAccountBalance(),
                account.getAccountType(), account.getAccountCreationDate() };

        System.out.println("Adding account to account_table");
        jdbcTemplate.update(ADD_ACCOUNT, params);
        System.out.println("Account added to account_table");
        Object[] params1 = { account.getAccountNumber()};
        System.out.println("Adding account to savings_account_table");
        jdbcTemplate.update(ADD_SAVINGS_ACCOUNT, params1);
        System.out.println("Account added to savings_account_table");
        return account;
    }

    @Override
    public Account blockAccount(int accountNumber) {
        Object[] params = { accountNumber };
        jdbcTemplate.update(BLOCK_ACCOUNT, params);
        return getAccountByAccountNumber(accountNumber);
    }

    @Override
    public Account updateAccount(Account account) {
        Object[] params = { account.getAccountBalance(), account.getAccountNumber() };
        jdbcTemplate.update(UPDATE_ACCOUNT, params);
        return getAccountByAccountNumber(account.getAccountNumber());

    }

    @Override
    public Account getAccountByAccountNumber(int accountNumber) {
        Object[] params = { accountNumber };
        Account account = jdbcTemplate.queryForObject(GET_ACCOUNT_BY_ACCOUNT_NUMBER, new AccountRowMapper(),params); 
        return account; 
    }

    @Override
    public int deposit(int accountNumber, double amount) {
        Account account = getAccountByAccountNumber(accountNumber);
        double newBalance = account.getAccountBalance() + amount;
        Object[] params = { newBalance, accountNumber };
        jdbcTemplate.update(DEPOSIT, params);
        return 1;
    }

    @Override
    public int withdraw(int accountNumber, double amount) {
        Account account = getAccountByAccountNumber(accountNumber);
        double newBalance = account.getAccountBalance() - amount;
        if(newBalance < Constants.savingAccountMinimumBalance) {
            return 0; //Balance is less than minimum balance
        }
        Object[] params = { newBalance, accountNumber };
        jdbcTemplate.update(WITHDRAW, params);
        return 1;
    }

    @Override
    public List<Account> getAllAccounts() {
        return jdbcTemplate.query(GET_ALL_ACCOUNTS, new AccountRowMapper());
    }
    
    @Override
    public double getMinimumBalance() {
        return jdbcTemplate.queryForObject(GET_MINIMUM_BALANCE, Double.class);
    }

    @Override
    public double getInterestRate() {
        return jdbcTemplate.queryForObject(GET_INTEREST_RATE, Double.class);
    }

    
}