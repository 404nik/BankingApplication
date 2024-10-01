package com.mindgate.main.service;

import java.util.List;

import com.mindgate.main.domain.Customer;
import com.mindgate.main.domain.File;

public interface CustomerService {

    Customer loginCustomer(Customer customer);
	Customer addCustomer(Customer customer, File file);
	Boolean approveCustomer(int customerId);
	List<Customer> getAllCustomer();
	List<Customer> getAllUnapprovedCustomer();
	List<Customer> getAllBlockedCustomer();
	Boolean unblockCustomer(int customerId);
	Boolean deleteCustomer(int customerId);
}


