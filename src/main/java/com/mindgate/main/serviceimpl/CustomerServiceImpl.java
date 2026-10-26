package com.mindgate.main.serviceimpl;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindgate.main.domain.Customer;
import com.mindgate.main.domain.CustomerLoginResult;
import com.mindgate.main.domain.File;
import com.mindgate.main.repository.CustomerRepository;
import com.mindgate.main.repository.FileRepository;
import com.mindgate.main.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    static final int MAX_FAILED_LOGIN_ATTEMPTS = 5;
    static final Duration TEMPORARY_LOCKOUT_DURATION = Duration.ofMinutes(15);

    private static final String ACTIVE_STATUS = "ACTIVE";
    private static final String BLOCKED_STATUS = "BLOCKED";
    private static final String UNAPPROVED_STATUS = "UNAPPROVED";

    private final CustomerRepository customerRepository;
    private final FileRepository fileRepository;
    private final Clock clock;

    public CustomerServiceImpl(CustomerRepository customerRepository, FileRepository fileRepository, Clock clock) {
        this.customerRepository = customerRepository;
        this.fileRepository = fileRepository;
        this.clock = clock;
    }

    @Override
    @Transactional
    public CustomerLoginResult loginCustomer(Customer loginRequest) {
        Customer customer = customerRepository.findByIdForLogin(loginRequest.getCustomerId());
        if (customer == null) {
            return CustomerLoginResult.unknownCustomer();
        }

        String accountStatus = customer.getCustomerAccountStatus();
        if (BLOCKED_STATUS.equals(accountStatus)) {
            return CustomerLoginResult.accountBlocked(customer);
        }
        if (UNAPPROVED_STATUS.equals(accountStatus)) {
            return CustomerLoginResult.accountUnapproved(customer);
        }
        if (!ACTIVE_STATUS.equals(accountStatus)) {
            return CustomerLoginResult.accountUnavailable(customer);
        }

        LocalDateTime now = LocalDateTime.now(clock);
        LocalDateTime lockedUntil = customer.getCustomerLoginLockedUntil();
        if (lockedUntil != null && lockedUntil.isAfter(now)) {
            return CustomerLoginResult.temporarilyLocked(customer, lockedUntil);
        }

        int startingFailureCount = lockedUntil == null ? customer.getCustomerLoginCount() : 0;
        if (passwordMatches(loginRequest, customer)) {
            customerRepository.updateLoginState(customer.getCustomerId(), 0, null);
            customer.setCustomerLoginCount(0);
            customer.setCustomerLoginLockedUntil(null);
            return CustomerLoginResult.success(customer);
        }

        int failedAttempts = startingFailureCount + 1;
        if (failedAttempts >= MAX_FAILED_LOGIN_ATTEMPTS) {
            LocalDateTime newLockedUntil = now.plus(TEMPORARY_LOCKOUT_DURATION);
            customerRepository.updateLoginState(customer.getCustomerId(), failedAttempts, newLockedUntil);
            customer.setCustomerLoginCount(failedAttempts);
            customer.setCustomerLoginLockedUntil(newLockedUntil);
            return CustomerLoginResult.temporarilyLocked(customer, newLockedUntil);
        }

        customerRepository.updateLoginState(customer.getCustomerId(), failedAttempts, null);
        customer.setCustomerLoginCount(failedAttempts);
        customer.setCustomerLoginLockedUntil(null);
        return CustomerLoginResult.invalidCredentials(customer);
    }

    private boolean passwordMatches(Customer loginRequest, Customer customer) {
        return customer.getCustomerPassword() != null
                && customer.getCustomerPassword().equals(loginRequest.getCustomerPassword());
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
