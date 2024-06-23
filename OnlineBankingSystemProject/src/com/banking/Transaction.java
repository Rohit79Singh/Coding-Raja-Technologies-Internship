package com.banking;

import java.util.Date;

public class Transaction {

	    private String transactionId;
	    private String accountNumber;
	    private double amount;
	    private Date transactionDate;
	    private String transactionType;

	    public Transaction(String transactionId, String accountNumber, double amount, Date transactionDate, String transactionType) {
	        this.transactionId = transactionId;
	        this.accountNumber = accountNumber;
	        this.amount = amount;
	        this.transactionDate = transactionDate;
	        this.transactionType = transactionType;
	    }

	    public String getTransactionId() { return transactionId; }
	    public String getAccountNumber() { return accountNumber; }
	    public double getAmount() { return amount; }
	    public Date getTransactionDate() { return transactionDate; }
	    public String getTransactionType() { return transactionType; }
	}