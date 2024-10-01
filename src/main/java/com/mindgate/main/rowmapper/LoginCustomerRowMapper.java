package com.mindgate.main.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.Customer;

public class LoginCustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customerLogin = new Customer();
        customerLogin.setCustomerId(rs.getInt("customer_id"));
        customerLogin.setFileId(rs.getInt("file_id"));
        customerLogin.setCustomerFirstName(rs.getString("customer_first_name"));
        customerLogin.setCustomerLastName(rs.getString("customer_last_name"));
        customerLogin.setCustomerEmail(rs.getString("customer_email"));
        customerLogin.setCustomerMobile(rs.getString("customer_mobile"));
        customerLogin.setCustomerAddress(rs.getString("customer_address"));
        customerLogin.setCustomerDob(rs.getDate("customer_dob").toLocalDate());
        customerLogin.setCustomerAccountCreationDate(rs.getDate("customer_account_creation_date").toLocalDate());
        customerLogin.setCustomerPassword(rs.getString("customer_password"));
        customerLogin.setCustomerAccountStatus(rs.getString("customer_account_status"));
        customerLogin.setCustomerLoginCount(rs.getInt("customer_login_count"));
        return customerLogin;
    }
	
}
