package com.mindgate.main.domain;

import java.time.LocalDate;

public class FixedDeposit extends Account{
    private int accountNumber;
    private  int maturityPeriod;
    private double interestRate;
    private double maturityAmount;
    private LocalDate starDate;
    private LocalDate endDate;

    public FixedDeposit() {
    }

    public FixedDeposit(int accountNumber, int maturityPeriod, double interestRate, double maturityAmount, LocalDate starDate, LocalDate endDate) {
        this.accountNumber = accountNumber;
        this.maturityPeriod = maturityPeriod;
        this.interestRate = interestRate;
        this.maturityAmount = maturityAmount;
        this.starDate = starDate;
        this.endDate = endDate;
    }



	public int getMaturityPeriod() {
		return maturityPeriod;
	}

	public void setMaturityPeriod(int maturityPeriod) {
		this.maturityPeriod = maturityPeriod;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getMaturityAmount() {
		return maturityAmount;
	}

	public void setMaturityAmount(double maturityAmount) {
		this.maturityAmount = maturityAmount;
	}

	public LocalDate getStarDate() {
		return starDate;
	}

	public void setStarDate(LocalDate starDate) {
		this.starDate = starDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "FixedDeposit [accountNumber=" + accountNumber + ", maturityPeriod=" + maturityPeriod + ", interestRate="
				+ interestRate + ", maturityAmount=" + maturityAmount + ", starDate=" + starDate + ", endDate="
				+ endDate + "]";
	}

    
    

}
