package com.mindgate.main.repositoryimpl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Account;
import com.mindgate.main.domain.Current;
import com.mindgate.main.repository.CurrentAccountRepository;
import com.mindgate.main.rowmapper.AccountRowMapper;
import com.mindgate.main.rowmapper.CurrentAccountRowMapper;

@Repository
public class CurrentAccountRepositoryImpl implements CurrentAccountRepository {

    private JdbcTemplate jdbcTemplate;

    public CurrentAccountRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GENERATE_NEXT_ACCOUNT_NUMBER = "SELECT account_number_seq.nextval FROM dual";
    private static final String ADD_ACCOUNT = "INSERT INTO account_table (account_number, customer_id, account_balance, account_type, account_creation_date) VALUES(?,?,?,?,?)";
    private static final String ADD_CURRENT_ACCOUNT = "INSERT INTO current_account_table (account_number) VALUES(?)";
    private static final String BLOCK_ACCOUNT = "UPDATE account_table SET account_status = 'BLOCKED' WHERE account_number = ?";
    private static final String UPDATE_ACCOUNT = "UPDATE account_table SET account_balance = ? WHERE account_number = ?";
    private static final String GET_ACCOUNT_BY_ACCOUNT_NUMBER = "SELECT * FROM account_table WHERE account_number = ?";
    private static final String DEPOSIT = "UPDATE account_table SET account_balance = ? WHERE account_number = ?";
    private static final String WITHDRAW = "UPDATE account_table SET account_balance = ? WHERE account_number = ?";
    private static final String GET_ALL_ACCOUNTS = "SELECT * FROM account_table WHERE account_type = 'CURRENT'";
    private static final String GET_CURRENT_ACCOUNT = "SELECT * FROM current_account_table WHERE account_number = ?";
    private static final String UPDATE_CURRENT_ACCOUNT = "UPDATE current_account_table SET account_overdraft_balance = ? WHERE account_number = ?";

	@Override
	public Account addAccount(Account account, Current current) {
		System.out.println("In addAccount method of CurrentAccountRepositoryImpl");
        int accountNumber = jdbcTemplate.queryForObject(GENERATE_NEXT_ACCOUNT_NUMBER, Integer.class);
        account.setAccountNumber(accountNumber);
        System.out.println("Account number generated: " + accountNumber + " for customer: " + account.getCustomer().getCustomerId());
        Object[] params = { account.getAccountNumber(), account.getCustomer().getCustomerId(), account.getAccountBalance(),
                account.getAccountType(), account.getAccountCreationDate() };

        System.out.println("Adding account to account_table");
        jdbcTemplate.update(ADD_ACCOUNT, params);
        System.out.println("Account added to account_table");
        Object[] params1 = { account.getAccountNumber()};
        System.out.println("Adding account to current_account_table");
        jdbcTemplate.update(ADD_CURRENT_ACCOUNT, params1);
        System.out.println("Account added to current_account_table");
        return account;
	}

	@Override
	public Account deleteAccount(int accountNumber) {
        Object[] params = { accountNumber };
        jdbcTemplate.update(BLOCK_ACCOUNT, params);
		return null;
	}

	@Override
	public Account updateAccount(Account account) {
		Object[] params = { account.getAccountBalance(), account.getAccountNumber() };
        int rowcount =jdbcTemplate.update(UPDATE_ACCOUNT, params);
        if(rowcount > 0) {
            return account;
        }
        return null;
	}

	@Override
	public Account getAccountByAccountNumber(int accountNumber) {
		Object[] params = { accountNumber };
        return jdbcTemplate.queryForObject(GET_ACCOUNT_BY_ACCOUNT_NUMBER, new AccountRowMapper(), params);
	}

	@Override
	public int deposit(int accountNumber, double amount) {
		// Current balance  = 0 , overdraft balance >0
        // Current balance = 0, overdraft balance = 0
        // Current balance > 0, overdraft balance >0

        Account account = getAccountByAccountNumber(accountNumber);
        Object[] params = { accountNumber };
        Current current = jdbcTemplate.queryForObject(GET_CURRENT_ACCOUNT, new CurrentAccountRowMapper(), params);
        double currentAccountOverDraftbalance = current.getOverDraftBalance();
        double currentAccountOverdraftLimit = current.getAccountOverdraftLimit();
        double currentBalance = account.getAccountBalance();
        double newBalance = 0;
        double newOverDraftBalance = 0;
        double newTemp = 0 ;
        if(currentBalance == 0 && currentAccountOverDraftbalance > 0 && currentAccountOverDraftbalance < currentAccountOverdraftLimit) {
            newTemp = currentAccountOverDraftbalance + amount;
            newOverDraftBalance = currentAccountOverdraftLimit - newTemp;
            if(newOverDraftBalance < 0) {
                newBalance = Math.abs(newOverDraftBalance);
                newOverDraftBalance = currentAccountOverdraftLimit;
            }
            else{
                newBalance = 0;
                newOverDraftBalance = newTemp;
            }
        }
        else if ( currentBalance == 0 && currentAccountOverDraftbalance == 0) {
            newTemp = amount;
            if (newTemp > currentAccountOverdraftLimit) {
                newBalance = newTemp - currentAccountOverdraftLimit;
                newOverDraftBalance = currentAccountOverdraftLimit;
            }
            else {
                newBalance = 0;
                newOverDraftBalance = newTemp;
            }
        }
        else if (currentBalance > 0 ) { 
            newBalance = currentBalance + amount;
            newOverDraftBalance = currentAccountOverDraftbalance;
        }
        Object[] params1 = { newBalance, accountNumber };
        Object[] params2 = { newOverDraftBalance, accountNumber };
        jdbcTemplate.update(DEPOSIT, params1);
        jdbcTemplate.update(UPDATE_CURRENT_ACCOUNT, params2);
        return 1;

	}

	@Override
	public int withdraw(int accountNumber, double amount) {
		Account account = getAccountByAccountNumber(accountNumber);
        Object[] params = { accountNumber };
        Current current = jdbcTemplate.queryForObject(GET_CURRENT_ACCOUNT, new CurrentAccountRowMapper(), params);
        double currentAccountOverDraftbalance = current.getOverDraftBalance();
        double currentAccountOverdraftLimit = current.getAccountOverdraftLimit();
        System.out.println("Current Account Overdraft Limit: " + currentAccountOverdraftLimit);
        System.out.println("Current Account Overdraft Balance: " + currentAccountOverDraftbalance);
        double currentBalance = account.getAccountBalance();
        double newBalance = 0;
        int message = 0;
        double newOverDraftBalance = 0;
        double newTemp = currentBalance + currentAccountOverDraftbalance;
        System.out.println("Total balance: " + newTemp);
        double leftAmount = newTemp - amount;
        if ( leftAmount >= 0 && leftAmount <= currentAccountOverdraftLimit){
            newBalance = 0;
            newOverDraftBalance = leftAmount;
            message = 1;
        } 
        else if (leftAmount >= currentAccountOverdraftLimit) {
            newBalance = leftAmount - currentAccountOverdraftLimit;
            newOverDraftBalance = currentAccountOverdraftLimit;
            message = 1;
	    }
        else if (leftAmount < 0) {
            newBalance = currentBalance; 
            newOverDraftBalance = currentAccountOverDraftbalance;
            message = -1;
        }
        Object[] params1 = { newBalance, accountNumber };
        Object[] params2 = { newOverDraftBalance, accountNumber };
        jdbcTemplate.update(WITHDRAW, params1);
        jdbcTemplate.update(UPDATE_CURRENT_ACCOUNT, params2);
        return message;
    }

	@Override
	public List<Account> getAllAccounts() {
		return jdbcTemplate.query(GET_ALL_ACCOUNTS, new AccountRowMapper());
	}
    
    @Override
    public Current getCurrentByAccountNumber(int accountNumber) {
        Object[] params = { accountNumber };
        return jdbcTemplate.queryForObject(GET_CURRENT_ACCOUNT, new CurrentAccountRowMapper(), params);
    }
    
    
}
