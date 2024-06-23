package com.banking;

import java.util.Date;

public class Loan {

	    private String loanId;
        private String accountNumber;
        private double amount;
        private double interestRate;
        private Date startDate;
        private Date endDate;
        private double monthlyPayment;
        private double remainingBalance;
	    
	    public Loan(String accountNumber, double amount, double interestRate, Date startDate, Date endDate,
			double monthlyPayment, double remainingBalance) {
		
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.interestRate = interestRate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.monthlyPayment = monthlyPayment;
		this.remainingBalance = remainingBalance;
	}

	    public Loan() {
			
		}

		public boolean deduction(double amountl) {
	        if (amount > 0 && amountl <= amount) {
	        	amount -= amountl;
	            return true;
	        }
	        return false;
	    }
	    
		public String getLoanId() {
			return loanId;
		}

		public String getAccountNumber() {
			return accountNumber;
		}

		public double getAmount() {
			return amount;
		}

		public double getInterestRate() {
			return interestRate;
		}

		public Date getStartDate() {
			return startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public double getMonthlyPayment() {
			return monthlyPayment;
		}

		public double getRemainingBalance() {
			return remainingBalance;
		}
	    
}
