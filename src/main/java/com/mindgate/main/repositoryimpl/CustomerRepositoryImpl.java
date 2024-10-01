package com.mindgate.main.repositoryimpl;

import java.util.List;

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

	private static final String GET_CUSTOMER = "SELECT * FROM customer_table WHERE customer_id = ?";
	private static final String SET_LOGIN_COUNT = "UPDATE customer_table SET customer_login_count = ?, customer_account_status = ? WHERE customer_id = ?";
    private static final String LOGIN_QUERY = "SELECT * FROM CUSTOMER_TABLE WHERE customer_id = ? AND customer_password = ?";
    private static final String GENERATE_NEXT_CUST_ID = "select customer_seq.nextval FROM DUAL";
    private static final String ADD_CUSTOMER = "INSERT INTO customer_table (customer_id, file_id, customer_first_name,customer_last_name, customer_email, customer_mobile, customer_address, customer_dob, customer_account_creation_date, customer_password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String APPROVE_CUSTOMER = "UPDATE customer_table SET customer_account_status = 'ACTIVE' WHERE customer_id = ?";
	private static final String UNBLOCK_CUSTOMER = "UPDATE customer_table SET customer_account_status = 'ACTIVE', customer_login_count = 0 WHERE customer_id = ?";
	private static final String DELETE_CUSTOMER = "DELETE FROM customer_table WHERE customer_id = ?";
	private static final String GET_ALL_CUSTOMER = "SELECT * FROM customer_table";
	private static final String GET_ALL_UNAPPROVED_CUSTOMER = "SELECT * FROM customer_table WHERE customer_account_status = 'UNAPPROVED'";
	private static final String GET_ALL_BLOCKED_CUSTOMER = "SELECT * FROM customer_table WHERE customer_account_status = 'BLOCKED'";
	// private static final String APPROVE_CUSTOMER = "UPDATE customer_table SET customer_account_status = 'ACTIVE' WHERE customer_id = ?";

//    private static final String FILE_UPLOAD="insert into file_table ()"

    @Override
    public Customer loginCustomer(Customer customer) {
		try {
			Object[] params = { customer.getCustomerId(), customer.getCustomerPassword() };
			System.out.println(customer.getCustomerId());
			System.out.println(customer.getCustomerPassword());
			System.out.println("Inside Login Repository");
			System.out.println("Before Query");
			System.out.println(customer);
			Customer customerFromDb = jdbcTemplate.queryForObject(LOGIN_QUERY, new LoginCustomerRowMapper(), params);
			System.out.println("After Query");
			if (customerFromDb.getCustomerAccountStatus().equals("BLOCKED") || customerFromDb.getCustomerAccountStatus().equals("UNAPPROVED")) {
				return customerFromDb;
			} 
			else if (customerFromDb.getCustomerLoginCount() < 3
					&& (customerFromDb.getCustomerAccountStatus().equals("ACTIVE") || customerFromDb.getCustomerAccountStatus().equals("UNAPPROVED")) ) {
				customerFromDb.setCustomerLoginCount(0);
				Object[] params1 = { customerFromDb.getCustomerLoginCount(), customerFromDb.getCustomerAccountStatus(),
						customerFromDb.getCustomerId() };
				System.out.println("Before Query Count");
				jdbcTemplate.update(SET_LOGIN_COUNT, params1);
				System.out.println("After Query COunt");
				System.out.println("Login Success");
				return customerFromDb;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			try {
				Object[] params2 = {customer.getCustomerId()};
				Customer loginIfExist = jdbcTemplate.queryForObject(GET_CUSTOMER, new LoginCustomerRowMapper(), params2); 
				System.out.println(loginIfExist);
				try {
					if (loginIfExist.getCustomerLoginCount() !=3 && !loginIfExist.getCustomerAccountStatus().equals("UNAPPROVED")) {
					loginIfExist.setCustomerLoginCount(loginIfExist.getCustomerLoginCount()+1);
					}
					if (loginIfExist.getCustomerLoginCount() == 3) {
						loginIfExist.setCustomerAccountStatus("BLOCKED");
					}
					Object[] params = {loginIfExist.getCustomerLoginCount(), loginIfExist.getCustomerAccountStatus(), loginIfExist.getCustomerId()};
					jdbcTemplate.update(SET_LOGIN_COUNT, params);
					return loginIfExist;				
				} catch (Exception e2) {
					System.out.println("Error in updating login count");
					return null;
				}
			} catch (Exception e3) {
				return null;
			}
			
		}
		return null;
    }

    @Override
    public Customer addCustomer(Customer customer) {
		System.out.println("Inside Add Customer Repository");
        int generatedCustomerId = jdbcTemplate.queryForObject(GENERATE_NEXT_CUST_ID, Integer.class);
		customer.setCustomerId(generatedCustomerId);
		System.out.println("Customer ID: " + customer.getCustomerId());
		// private static final String ADD_CUSTOMER = "INSERT INTO customer_table (customer_id, file_id, customer_name, customer_email, customer_mobile, customer_address, customer_dob, customer_account_created_date, customer_password, customer_account_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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