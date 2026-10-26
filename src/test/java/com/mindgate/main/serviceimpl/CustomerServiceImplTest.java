package com.mindgate.main.serviceimpl;

import static com.mindgate.main.domain.CustomerLoginResult.Status.ACCOUNT_BLOCKED;
import static com.mindgate.main.domain.CustomerLoginResult.Status.ACCOUNT_UNAPPROVED;
import static com.mindgate.main.domain.CustomerLoginResult.Status.INVALID_CREDENTIALS;
import static com.mindgate.main.domain.CustomerLoginResult.Status.SUCCESS;
import static com.mindgate.main.domain.CustomerLoginResult.Status.TEMPORARILY_LOCKED;
import static com.mindgate.main.domain.CustomerLoginResult.Status.UNKNOWN_CUSTOMER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mindgate.main.domain.Customer;
import com.mindgate.main.domain.CustomerLoginResult;
import com.mindgate.main.repository.CustomerRepository;
import com.mindgate.main.repository.FileRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    private static final int CUSTOMER_ID = 101;
    private static final String PASSWORD = "correct-password";
    private static final Instant FIXED_INSTANT = Instant.parse("2026-10-26T12:00:00Z");
    private static final Clock FIXED_CLOCK = Clock.fixed(FIXED_INSTANT, ZoneOffset.UTC);
    private static final LocalDateTime NOW = LocalDateTime.ofInstant(FIXED_INSTANT, ZoneOffset.UTC);

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private FileRepository fileRepository;

    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerRepository, fileRepository, FIXED_CLOCK);
    }

    @Test
    void failuresOneThroughFourRemainUnlocked() {
        for (int existingFailures = 0; existingFailures < 4; existingFailures++) {
            reset(customerRepository);
            Customer customer = activeCustomer(existingFailures, null);
            when(customerRepository.findByIdForLogin(CUSTOMER_ID)).thenReturn(customer);

            CustomerLoginResult result = customerService.loginCustomer(loginRequest("wrong-password"));

            assertEquals(INVALID_CREDENTIALS, result.getStatus());
            verify(customerRepository).findByIdForLogin(CUSTOMER_ID);
            verify(customerRepository).updateLoginState(CUSTOMER_ID, existingFailures + 1, null);
            verifyNoMoreInteractions(customerRepository);
        }
    }

    @Test
    void fifthFailureImmediatelyReturnsAndPersistsTemporaryLock() {
        Customer customer = activeCustomer(4, null);
        when(customerRepository.findByIdForLogin(CUSTOMER_ID)).thenReturn(customer);

        CustomerLoginResult result = customerService.loginCustomer(loginRequest("wrong-password"));

        LocalDateTime expectedDeadline = NOW.plusMinutes(15);
        assertEquals(TEMPORARILY_LOCKED, result.getStatus());
        assertEquals(expectedDeadline, result.getLockedUntil());
        InOrder inOrder = inOrder(customerRepository);
        inOrder.verify(customerRepository).findByIdForLogin(CUSTOMER_ID);
        inOrder.verify(customerRepository).updateLoginState(CUSTOMER_ID, 5, expectedDeadline);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void correctAndIncorrectPasswordsAreRejectedBeforeTemporaryLockExpires() {
        LocalDateTime lockedUntil = NOW.plusMinutes(1);

        Customer lockedForCorrectPassword = activeCustomer(5, lockedUntil);
        when(customerRepository.findByIdForLogin(CUSTOMER_ID)).thenReturn(lockedForCorrectPassword);
        CustomerLoginResult correctPasswordResult = customerService.loginCustomer(loginRequest(PASSWORD));
        assertEquals(TEMPORARILY_LOCKED, correctPasswordResult.getStatus());
        assertEquals(lockedUntil, correctPasswordResult.getLockedUntil());
        verify(customerRepository).findByIdForLogin(CUSTOMER_ID);
        verify(customerRepository, never()).updateLoginState(CUSTOMER_ID, 0, null);
        verifyNoMoreInteractions(customerRepository);

        reset(customerRepository);
        Customer lockedForWrongPassword = activeCustomer(5, lockedUntil);
        when(customerRepository.findByIdForLogin(CUSTOMER_ID)).thenReturn(lockedForWrongPassword);
        CustomerLoginResult wrongPasswordResult = customerService.loginCustomer(loginRequest("wrong-password"));
        assertEquals(TEMPORARILY_LOCKED, wrongPasswordResult.getStatus());
        assertEquals(lockedUntil, wrongPasswordResult.getLockedUntil());
        verify(customerRepository).findByIdForLogin(CUSTOMER_ID);
        verify(customerRepository, never()).updateLoginState(CUSTOMER_ID, 6, lockedUntil);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void exactTemporaryLockExpiryBoundaryAllowsSuccessfulAuthentication() {
        Customer customer = activeCustomer(5, NOW);
        when(customerRepository.findByIdForLogin(CUSTOMER_ID)).thenReturn(customer);

        CustomerLoginResult result = customerService.loginCustomer(loginRequest(PASSWORD));

        assertEquals(SUCCESS, result.getStatus());
        verify(customerRepository).findByIdForLogin(CUSTOMER_ID);
        verify(customerRepository).updateLoginState(CUSTOMER_ID, 0, null);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void wrongPasswordAfterTemporaryLockExpiryStartsFreshFailureSequence() {
        Customer customer = activeCustomer(5, NOW.minusSeconds(1));
        when(customerRepository.findByIdForLogin(CUSTOMER_ID)).thenReturn(customer);

        CustomerLoginResult result = customerService.loginCustomer(loginRequest("wrong-password"));

        assertEquals(INVALID_CREDENTIALS, result.getStatus());
        verify(customerRepository).findByIdForLogin(CUSTOMER_ID);
        verify(customerRepository).updateLoginState(CUSTOMER_ID, 1, null);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void successfulAuthenticationResetsFailureCountAndDeadline() {
        Customer customer = activeCustomer(3, NOW.plusMinutes(0));
        when(customerRepository.findByIdForLogin(CUSTOMER_ID)).thenReturn(customer);

        CustomerLoginResult result = customerService.loginCustomer(loginRequest(PASSWORD));

        assertEquals(SUCCESS, result.getStatus());
        assertEquals(0, result.getCustomer().getCustomerLoginCount());
        assertNull(result.getCustomer().getCustomerLoginLockedUntil());
        verify(customerRepository).findByIdForLogin(CUSTOMER_ID);
        verify(customerRepository).updateLoginState(CUSTOMER_ID, 0, null);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void administrativeBlockRemainsIndependentOfTemporaryExpiry() {
        Customer customer = customerWithStatus("BLOCKED", 5, NOW.minusMinutes(20));
        when(customerRepository.findByIdForLogin(CUSTOMER_ID)).thenReturn(customer);

        CustomerLoginResult result = customerService.loginCustomer(loginRequest(PASSWORD));

        assertEquals(ACCOUNT_BLOCKED, result.getStatus());
        verify(customerRepository).findByIdForLogin(CUSTOMER_ID);
        verify(customerRepository, never()).updateLoginState(CUSTOMER_ID, 0, null);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void unapprovedCustomerIsNotTreatedAsTemporaryLockout() {
        Customer customer = customerWithStatus("UNAPPROVED", 5, NOW.plusMinutes(15));
        when(customerRepository.findByIdForLogin(CUSTOMER_ID)).thenReturn(customer);

        CustomerLoginResult result = customerService.loginCustomer(loginRequest(PASSWORD));

        assertEquals(ACCOUNT_UNAPPROVED, result.getStatus());
        verify(customerRepository).findByIdForLogin(CUSTOMER_ID);
        verify(customerRepository, never()).updateLoginState(CUSTOMER_ID, 0, null);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void unknownCustomerReturnsGenericOutcomeWithoutPersistence() {
        when(customerRepository.findByIdForLogin(CUSTOMER_ID)).thenReturn(null);

        CustomerLoginResult result = customerService.loginCustomer(loginRequest(PASSWORD));

        assertEquals(UNKNOWN_CUSTOMER, result.getStatus());
        verify(customerRepository).findByIdForLogin(CUSTOMER_ID);
        verifyNoMoreInteractions(customerRepository);
    }

    private Customer activeCustomer(int loginCount, LocalDateTime lockedUntil) {
        return customerWithStatus("ACTIVE", loginCount, lockedUntil);
    }

    private Customer customerWithStatus(String status, int loginCount, LocalDateTime lockedUntil) {
        Customer customer = new Customer();
        customer.setCustomerId(CUSTOMER_ID);
        customer.setCustomerPassword(PASSWORD);
        customer.setCustomerAccountStatus(status);
        customer.setCustomerLoginCount(loginCount);
        customer.setCustomerLoginLockedUntil(lockedUntil);
        return customer;
    }

    private Customer loginRequest(String password) {
        Customer customer = new Customer();
        customer.setCustomerId(CUSTOMER_ID);
        customer.setCustomerPassword(password);
        return customer;
    }
}
