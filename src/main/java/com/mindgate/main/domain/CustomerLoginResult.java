package com.mindgate.main.domain;

import java.time.LocalDateTime;

public class CustomerLoginResult {

    public enum Status {
        SUCCESS,
        INVALID_CREDENTIALS,
        TEMPORARILY_LOCKED,
        UNKNOWN_CUSTOMER,
        ACCOUNT_BLOCKED,
        ACCOUNT_UNAPPROVED,
        ACCOUNT_UNAVAILABLE
    }

    private final Status status;
    private final Customer customer;
    private final LocalDateTime lockedUntil;

    private CustomerLoginResult(Status status, Customer customer, LocalDateTime lockedUntil) {
        this.status = status;
        this.customer = customer;
        this.lockedUntil = lockedUntil;
    }

    public static CustomerLoginResult success(Customer customer) {
        return new CustomerLoginResult(Status.SUCCESS, customer, null);
    }

    public static CustomerLoginResult invalidCredentials(Customer customer) {
        return new CustomerLoginResult(Status.INVALID_CREDENTIALS, customer, null);
    }

    public static CustomerLoginResult temporarilyLocked(Customer customer, LocalDateTime lockedUntil) {
        return new CustomerLoginResult(Status.TEMPORARILY_LOCKED, customer, lockedUntil);
    }

    public static CustomerLoginResult unknownCustomer() {
        return new CustomerLoginResult(Status.UNKNOWN_CUSTOMER, null, null);
    }

    public static CustomerLoginResult accountBlocked(Customer customer) {
        return new CustomerLoginResult(Status.ACCOUNT_BLOCKED, customer, null);
    }

    public static CustomerLoginResult accountUnapproved(Customer customer) {
        return new CustomerLoginResult(Status.ACCOUNT_UNAPPROVED, customer, null);
    }

    public static CustomerLoginResult accountUnavailable(Customer customer) {
        return new CustomerLoginResult(Status.ACCOUNT_UNAVAILABLE, customer, null);
    }

    public Status getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getLockedUntil() {
        return lockedUntil;
    }
}
