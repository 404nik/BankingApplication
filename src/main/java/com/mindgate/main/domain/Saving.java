package com.mindgate.main.domain;

public class Saving  extends Account{
	private int accountNumber;
	private double interestRate;
	private double minimumBalance;

	public Saving() {
	}

	public Saving(int accountNumber, double interestRate, double minimumBalance) {
		this.accountNumber = accountNumber;
		this.interestRate = interestRate;
		this.minimumBalance = minimumBalance;
	}

	public double getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getMinimumBalance() {
		return this.minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}	

	@Override
	public String toString() {
		return "Saving [accountNumber=" + accountNumber + ", interestRate=" + interestRate + ", minimumBalance="
				+ minimumBalance + "]";
	}

	
}
