package com.mindgate.main.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.mindgate.main.domain.Customer;


// import jakarta.validation.OverridesAttribute.List;

public interface CustomerRepository {

	Customer findByIdForLogin(int customerId);
	Boolean updateLoginState(int customerId, int loginCount, LocalDateTime lockedUntil);
	Customer addCustomer(Customer customer);
	Boolean approveCustomer(int customerId);
	List<Customer> getAllCustomer();
	List<Customer> getAllUnapprovedCustomer();
	List<Customer> getAllBlockedCustomer();
	// Boolean blockCustomer(int customerId);
	Boolean unblockCustomer(int customerId);
	Boolean deleteCustomer(int customerId);

}
