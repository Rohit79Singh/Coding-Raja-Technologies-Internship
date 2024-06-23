package com.banking;

public class Account {

	    private String accountNumber;
	    private double balance;
	    private String accountType;

	    public Account(String accountNumber, double initialBalance, String accountType) {
	        this.accountNumber = accountNumber;
	        this.balance = initialBalance;
	        this.accountType = accountType;
	    }

	    public String getAccountNumber() { return accountNumber; }
	    public double getBalance() { return balance; }
	    public String getAccountType() { return accountType; }
	    
	    public void deposit(double amount) {
	        if (amount > 0) {
	            balance += amount;
	        }
	    }

	    public boolean withdraw(double amount) {
	        if (amount > 0 && amount <= balance) {
	            balance -= amount;
	            return true;
	        }
	        return false;
	    }
	}