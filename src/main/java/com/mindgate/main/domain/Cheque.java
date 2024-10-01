package com.mindgate.main.domain;

import java.time.LocalDate;

public class Cheque {
    // private int chequeId;
    private int chequeNumber;
    private int fromAccountNumber;
    private int toAccountNumber;
    private double amount;
    private String chequeStatus;
    private LocalDate  chequeIssueDate;
    private LocalDate chequeClearanceDate;
    
   public Cheque() {
   }

   public Cheque(int chequeNumber, int fromAccountNumber, int toAccountNumber, double amount, String chequeStatus, LocalDate chequeIssueDate, LocalDate chequeClearanceDate) {
       // this.chequeId = chequeId;
       this.chequeNumber = chequeNumber;
       this.fromAccountNumber = fromAccountNumber;
       this.toAccountNumber = toAccountNumber;
       this.amount = amount;
       this.chequeStatus = chequeStatus;
       this.chequeIssueDate = chequeIssueDate;
       this.chequeClearanceDate = chequeClearanceDate;
   }

	public int getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(int chequeNumber) {
		this.chequeNumber = chequeNumber;
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

	public String getChequeStatus() {
		return chequeStatus;
	}

	public void setChequeStatus(String chequeStatus) {
		this.chequeStatus = chequeStatus;
	}

	public LocalDate getChequeIssueDate() {
		return chequeIssueDate;
	}

	public void setChequeIssueDate(LocalDate chequeIssueDate) {
		this.chequeIssueDate = chequeIssueDate;
	}

	public LocalDate getChequeClearanceDate() {
		return chequeClearanceDate;
	}

	public void setChequeClearanceDate(LocalDate chequeClearanceDate) {
		this.chequeClearanceDate = chequeClearanceDate;
	}

	@Override
	public String toString() {
		return "Cheque [chequeNumber=" + chequeNumber + ", fromAccountNumber=" + fromAccountNumber
				+ ", toAccountNumber=" + toAccountNumber + ", amount=" + amount + ", chequeStatus=" + chequeStatus
				+ ", chequeIssueDate=" + chequeIssueDate + ", chequeClearanceDate=" + chequeClearanceDate + "]";
	}

}
