package com.mindgate.main.domain;

import java.time.LocalDate;

public class DTO {
    private int transactionId;
    private int fromAccountNumber; 
    private int toAccountNumber;
    private double amount;
    private String transactionType;
    private LocalDate transactionDate;
    
    
	public DTO() {
		super();
	}
	public DTO(int transactionId, int fromAccountNumber, int toAccountNumber, double amount, String transactionType,
			LocalDate transactionDate) {
		super();
		this.transactionId = transactionId;
		this.fromAccountNumber = fromAccountNumber;
		this.toAccountNumber = toAccountNumber;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
	}
	@Override
	public String toString() {
		return "DTO [transactionId=" + transactionId + ", fromAccountNumber=" + fromAccountNumber + ", toAccountNumber="
				+ toAccountNumber + ", amount=" + amount + ", transactionType=" + transactionType + ", transactionDate="
				+ transactionDate + "]";
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getFromAccountNumber() {
		return fromAccountNumber;
	}
	public void setFromAccountNumber(int fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}
	public int getToAccountNumber() {
		return toAccountNumber;
	}
	public void setToAccountNumber(int toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
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
    
    
    
}
