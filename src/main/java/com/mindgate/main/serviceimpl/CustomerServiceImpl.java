package com.mindgate.main.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Customer;
import com.mindgate.main.domain.File;
import com.mindgate.main.repository.CustomerRepository;
import com.mindgate.main.repository.FileRepository;
import com.mindgate.main.service.CustomerService;

import jakarta.validation.OverridesAttribute;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final FileRepository fileRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, FileRepository fileRepository) {
        this.customerRepository = customerRepository;
        this.fileRepository = fileRepository;
    }


    @Override
    public Customer loginCustomer(Customer customer) {
        Customer customerLogin = customerRepository.loginCustomer(customer);
        System.out.println("Inside Customer Login Service");
        return customerLogin;
    }

    @Override
    public Customer addCustomer(Customer customer, File file) {
        System.out.println("Inside Add Customer Service");
        fileRepository.uploadFile(file);
        return customerRepository.addCustomer(customer);
    }

    @Override
    public Boolean approveCustomer(int customerId) {
        System.out.println("Inside Approve Customer Service");
        return customerRepository.approveCustomer(customerId);
    }


    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.getAllCustomer();
    }


    @Override
    public List<Customer> getAllUnapprovedCustomer() {
        return customerRepository.getAllUnapprovedCustomer();
    }


    @Override
    public List<Customer> getAllBlockedCustomer() {
        return customerRepository.getAllBlockedCustomer();
    }

    @Override
    public Boolean unblockCustomer(int customerId) {
        return customerRepository.unblockCustomer(customerId);
    }

    @Override
    public Boolean deleteCustomer(int customerId) {
        return customerRepository.deleteCustomer(customerId);
    }
    
	
}
