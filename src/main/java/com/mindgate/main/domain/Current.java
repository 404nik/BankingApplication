package com.mindgate.main.domain;

public class Current extends Account {
    private int accountNumber;
    private double accountOverdraftLimit;
    private double overdraftInterestRate;
    private double overDraftBalance;

	public double getAccountOverdraftLimit() {
		return accountOverdraftLimit;
	}
	public void setAccountOverdraftLimit(double accountOverdraftLimit) {
		this.accountOverdraftLimit = accountOverdraftLimit;
	}
	public double getOverdraftInterestRate() {
		return overdraftInterestRate;
	}
	public void setOverdraftInterestRate(double overdraftInterestRate) {
		this.overdraftInterestRate = overdraftInterestRate;
	}
	public double getOverDraftBalance() {
		return overDraftBalance;
	}
	public void setOverDraftBalance(double overDraftBalance) {
		this.overDraftBalance = overDraftBalance;
	}
    
	@Override
	public String toString() {
		return "Current [accountNumber=" + accountNumber + ", accountOverdraftLimit=" + accountOverdraftLimit
				+ ", overdraftInterestRate=" + overdraftInterestRate + ", overDraftBalance=" + overDraftBalance + "]";
	}

    

}
