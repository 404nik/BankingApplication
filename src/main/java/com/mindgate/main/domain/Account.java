package com.mindgate.main.domain;

import java.time.LocalDate;
public class Account {
	private int accountNumber;
    private Customer customer;
    private double accountBalance;
    private String accountType;
    private String accountStatus;
    private LocalDate accountCreationDate;

    public Account() {
    }

    public Account(int accountNumber, Customer customer, double accountBalance, String accountType, String accountStatus, LocalDate accountCreationDate) {
        this.accountNumber = accountNumber;
        this.customer= customer;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.accountCreationDate = accountCreationDate;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }
    
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountStatus() {
        return this.accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public LocalDate getAccountCreationDate() {
        return this.accountCreationDate;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    @Override
    public String toString() {
        return "{" +
            " accountNumber='" + getAccountNumber() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", accountBalance='" + getAccountBalance() + "'" +
            ", accountType='" + getAccountType() + "'" +
            ", accountStatus='" + getAccountStatus() + "'" +
            ", accountCreationDate='" + getAccountCreationDate() + "'" +
            "}";
    }

    
}
