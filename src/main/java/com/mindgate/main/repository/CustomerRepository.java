package com.mindgate.main.repository;

import java.util.List;

import com.mindgate.main.domain.Customer;


// import jakarta.validation.OverridesAttribute.List;

public interface CustomerRepository {

	Customer loginCustomer(Customer customer);
	Customer addCustomer(Customer customer);
	Boolean approveCustomer(int customerId);
	List<Customer> getAllCustomer();
	List<Customer> getAllUnapprovedCustomer();
	List<Customer> getAllBlockedCustomer();
	// Boolean blockCustomer(int customerId);
	Boolean unblockCustomer(int customerId);
	Boolean deleteCustomer(int customerId);

}
