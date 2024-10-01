package com.mindgate.main.domain;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

public class Transaction {
    private int transactionId;
    private Account account;
    private double amount;
    private String transactionType;
    private LocalDate  transactionDate;
    private String transactionStatus;

    public Transaction() {
    }

    public Transaction(int transactionId, Account account, double amount, String transactionType, LocalDate transactionDate, String transactionStatus) {
        this.transactionId = transactionId;
        this.account = account;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.transactionStatus = transactionStatus;
    }

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Account  getAccount() {
		return account ;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountNumber=" + account + ", amount=" + amount
				+ ", transactionType=" + transactionType + ", transactionDate=" + transactionDate
				+ ", transactionStatus=" + transactionStatus + "]";
	}

    
}
