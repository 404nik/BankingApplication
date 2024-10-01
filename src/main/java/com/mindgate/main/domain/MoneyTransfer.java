package com.mindgate.main.domain;

import java.time.LocalDate;

public class MoneyTransfer {
    private Transaction transaction;
    private Account fromAccount;
	private Account toAccount;
    private double amount;
    private LocalDate transactionDate;
    private String transactionStatus;
    
    public MoneyTransfer() {
    }
    

	public MoneyTransfer(Transaction transaction, Account fromAccount, Account toAccount, double amount,
			LocalDate transactionDate, String transactionStatus) {
		super();
		this.transaction = transaction;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.transactionStatus = transactionStatus;
	}


	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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
		return "MoneyTransfer [transaction=" + transaction + ", fromAccount=" + fromAccount + ", toAccount=" + toAccount
				+ ", amount=" + amount + ", transactionDate=" + transactionDate + ", transactionStatus="
				+ transactionStatus + "]";
	}

    

    
}
