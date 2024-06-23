package com.banking;

import java.sql.SQLException;
import java.util.List;

public class BankService {

	DatabaseManager databaseManager = new DatabaseManager();
	
	public void addUser(User user) throws SQLException{
		databaseManager.addUser(user);
	}
	
	public User authenticateUser(String username, String password) throws SQLException{
		return databaseManager.authenticateUser(username, password);
		
	}
	
	public void addAccount(Account account) throws SQLException{
		databaseManager.addAccount(account);
	}
	
	public void addTransaction(Transaction transaction) throws SQLException{
		databaseManager.addTransaction(transaction);
	}
	
	public Account getAccount(String accountNumber) throws SQLException{
		return databaseManager.getAccount(accountNumber);
		
	}
	
	public boolean transferFunds(String fromAccount, String toAccount, double amount) throws SQLException{
		return databaseManager.transferFunds(fromAccount, toAccount, amount);
		
	}
	
	public void updateAccountBalance(String fromAccount, String toAccount, double balance1, double balance2) throws SQLException{
		databaseManager.updateAccountBalance(fromAccount, toAccount, balance1, balance2);
	}
	
	public double checkBalance(String accountNumber) throws SQLException{
		return databaseManager.checkBalance(accountNumber);
		
	}
	
	public List<Transaction> getTransactionHistory(String accountNumber) throws SQLException{
		return databaseManager.getTransactionHistory(accountNumber);
		
	}
	
	public void addLoan(Loan loan) throws SQLException {
		databaseManager.addLoan(loan);
	}
	
	public List<Loan> getLoansForAccount(String accountNumber) throws SQLException {
		return databaseManager.getLoansForAccount(accountNumber);
		
	}
	
	public void updateLoan(String accountNumber, double amount) throws SQLException {
		databaseManager.updateLoan(accountNumber, amount);
	}

}
