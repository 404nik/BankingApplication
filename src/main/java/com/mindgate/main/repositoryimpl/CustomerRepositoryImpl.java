package com.mindgate.main.repositoryimpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Customer;
import com.mindgate.main.repository.CustomerRepository;
import com.mindgate.main.rowmapper.LoginCustomerRowMapper;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private JdbcTemplate jdbcTemplate;

    public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String GET_CUSTOMER_FOR_LOGIN = "SELECT * FROM customer_table WHERE customer_id = ? FOR UPDATE";
    private static final String UPDATE_LOGIN_STATE = "UPDATE customer_table SET customer_login_count = ?, customer_login_locked_until = ? WHERE customer_id = ?";
    private static final String GENERATE_NEXT_CUST_ID = "select customer_seq.nextval FROM DUAL";
    private static final String ADD_CUSTOMER = "INSERT INTO customer_table (customer_id, file_id, customer_first_name,customer_last_name, customer_email, customer_mobile, customer_address, customer_dob, customer_account_creation_date, customer_password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String APPROVE_CUSTOMER = "UPDATE customer_table SET customer_account_status = 'ACTIVE' WHERE customer_id = ?";
    private static final String UNBLOCK_CUSTOMER = "UPDATE customer_table SET customer_account_status = 'ACTIVE', customer_login_count = 0, customer_login_locked_until = NULL WHERE customer_id = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customer_table WHERE customer_id = ?";
    private static final String GET_ALL_CUSTOMER = "SELECT * FROM customer_table";
    private static final String GET_ALL_UNAPPROVED_CUSTOMER = "SELECT * FROM customer_table WHERE customer_account_status = 'UNAPPROVED'";
    private static final String GET_ALL_BLOCKED_CUSTOMER = "SELECT * FROM customer_table WHERE customer_account_status = 'BLOCKED'";

    @Override
    public Customer findByIdForLogin(int customerId) {
        try {
            return jdbcTemplate.queryForObject(GET_CUSTOMER_FOR_LOGIN, new LoginCustomerRowMapper(), customerId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Boolean updateLoginState(int customerId, int loginCount, LocalDateTime lockedUntil) {
        Timestamp lockedUntilTimestamp = lockedUntil == null ? null : Timestamp.valueOf(lockedUntil);
        int rowsAffected = jdbcTemplate.update(UPDATE_LOGIN_STATE, loginCount, lockedUntilTimestamp, customerId);
        return rowsAffected > 0;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        System.out.println("Inside Add Customer Repository");
        int generatedCustomerId = jdbcTemplate.queryForObject(GENERATE_NEXT_CUST_ID, Integer.class);
        customer.setCustomerId(generatedCustomerId);
        System.out.println("Customer ID: " + customer.getCustomerId());
        Object[] params = { customer.getCustomerId(), customer.getFileId(), customer.getCustomerFirstName(),customer.getCustomerLastName(), customer.getCustomerEmail(), customer.getCustomerMobile(), customer.getCustomerAddress(), customer.getCustomerDob(), customer.getCustomerAccountCreationDate(), customer.getCustomerPassword()};
        int rowsAffected = jdbcTemplate.update(ADD_CUSTOMER, params);
        if (rowsAffected > 0) {
            System.out.println("Customer Added Successfully");
            return customer;

        }
        return null;
    }

    @Override
    public Boolean approveCustomer(int customerId) {
        Object[] params = {customerId};
        int rowsAffected = jdbcTemplate.update(APPROVE_CUSTOMER, params);
        if (rowsAffected > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return jdbcTemplate.query(GET_ALL_CUSTOMER, new LoginCustomerRowMapper());
    }

    @Override
    public List<Customer> getAllUnapprovedCustomer() {
        return jdbcTemplate.query(GET_ALL_UNAPPROVED_CUSTOMER, new LoginCustomerRowMapper());
    }

    @Override
    public List<Customer> getAllBlockedCustomer() {
        return jdbcTemplate.query(GET_ALL_BLOCKED_CUSTOMER, new LoginCustomerRowMapper());
    }

    @Override
    public Boolean unblockCustomer(int customerId) {
        Object[] params = {customerId};
        int rowsAffected = jdbcTemplate.update(UNBLOCK_CUSTOMER, params);
        if (rowsAffected > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteCustomer(int customerId) {
        Object[] params = {customerId};
        int rowsAffected = jdbcTemplate.update(DELETE_CUSTOMER, params);
        if (rowsAffected > 0) {
            return true;
        }
        return false;
    }
}
