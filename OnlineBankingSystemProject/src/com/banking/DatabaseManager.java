package com.banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DatabaseManager {

	    private Connection connection;
	    List<Transaction> transactions;
	    
	    String dbUrl = "jdbc:mysql://localhost/bankdata";
		String user = "userName";
		String password = "Password";
	    
	    public DatabaseManager() {
	        try {
				connection = DriverManager.getConnection(dbUrl, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    public void addUser(User user) throws SQLException {
	        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, user.getUsername());
	            stmt.setString(2, user.getPassword());
	            stmt.executeUpdate();
	        }
	    }
	    
	    public User authenticateUser(String username, String password) throws SQLException {
	        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, username);
	            stmt.setString(2, password);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                return new User(rs.getString("username"), rs.getString("password"));
	            }
	        }
	        return null;
	    }
	    
	    public void addAccount(Account account) throws SQLException {
	        String query = "INSERT INTO accounts (accountNumber, balance, accountType) VALUES (?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, account.getAccountNumber());
	            stmt.setDouble(2, account.getBalance());
	            stmt.setString(3, account.getAccountType());
	            stmt.executeUpdate();
	        }
	    }

	    public void addTransaction(Transaction transaction) throws SQLException {
	    	String query = "INSERT INTO transactions (transactionId, accountNumber, amount, transactionDate, transactionType) VALUES (?, ?, ?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, transaction.getTransactionId());
	            stmt.setString(2, transaction.getAccountNumber());
	            stmt.setDouble(3, transaction.getAmount());
	            stmt.setTimestamp(4, new Timestamp(transaction.getTransactionDate().getTime()));
	            stmt.setString(5, transaction.getTransactionType());
	            stmt.executeUpdate();
	        }
	    }
	    
	    public Account getAccount(String accountNumber) throws SQLException {
	        String query = "SELECT * FROM accounts WHERE accountNumber = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, accountNumber);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                return new Account(rs.getString("accountNumber"), rs.getDouble("balance"), rs.getString("accountType"));
	            }
	        }
	        return null;
	    }

	    
	    public boolean transferFunds(String fromAccount, String toAccount, double amount) throws SQLException {
	    	String query = "SELECT * FROM accounts WHERE accountNumber = ?";
	    	
	    	double balan1 = 0;
	        double balan2 = 0;
	        
	    	try (PreparedStatement stmt = connection.prepareStatement(query)) {
	    		
	            stmt.setString(1, fromAccount);
	            ResultSet rs1 = stmt.executeQuery();
	            stmt.setString(1, toAccount);
	            ResultSet rs2 = stmt.executeQuery();
	            Account from = new Account(rs1.getString("accountNumber"), rs1.getDouble("balance"), rs1.getString("accountType"));
                Account to = new Account(rs2.getString("accountNumber"), rs2.getDouble("balance"), rs2.getString("accountType"));
	            
	            if (rs1.next() && rs2.next() && from != null && to != null) {
	            	
	            	from.withdraw(amount);
	                balan1=from.getBalance();
	                
	                to.deposit(amount);
	                balan2 = to.getBalance();
	                
	                updateAccountBalance(fromAccount, toAccount, balan1, balan2);
	                
	                transactions.add(new Transaction(UUID.randomUUID().toString(), fromAccount, -amount, new Date(), "Transfer"));
	                transactions.add(new Transaction(UUID.randomUUID().toString(), toAccount, amount, new Date(), "Recivied"));
	                
	                int i = 0;
	                while(i < transactions.size()) {
	                addTransaction(transactions.get(i));
	                i++;
	                }
	                
	                return true;
	                
	            }
	        }
	        return false;
	    }
	    
	    public void updateAccountBalance(String fromAccount, String toAccount, double balance1, double balance2) throws SQLException {
	        String query = "UPDATE accounts SET balance = ? WHERE accountNumber = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setDouble(1, balance1);
	            stmt.setString(2, fromAccount);
	            stmt.executeUpdate();
	            stmt.setDouble(1, balance2);
	            stmt.setString(2, toAccount);
	            stmt.executeUpdate();
	        }
	    }
	    
	    public double checkBalance(String accountNumber) throws SQLException {
	    	String query = "SELECT * FROM accounts WHERE accountNumber = ?";
	    	
	    	try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, accountNumber);
	            ResultSet rs = stmt.executeQuery();
	        
	            if (rs.next()) {
					 return rs.getDouble("balance");
	            }
	        }
	        return -1;
	    }	       
	    
	    public List<Transaction> getTransactionHistory(String accountNumber) throws SQLException {
            String query = "SELECT * FROM transactions WHERE accountNumber = ?";
            List<Transaction> history = new ArrayList<>();
            
	    	try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, accountNumber);
	            ResultSet rs = stmt.executeQuery();
	            
	            int i=1;
	            while(i<=rs.getFetchSize()) {
	                rs.absolute(i);
	            	Transaction data = new Transaction(rs.getString("transactionId"), rs.getString("accountNumber"),rs.getDouble("amount"), rs.getDate("transactionDate") , rs.getString("transactionType"));
	            	history.add(data);
	            	i++;
	            }
	        }
	        return history;
	    	}
	
	    public void addLoan(Loan loan) throws SQLException {
	        String query = "INSERT INTO loans (accountNumber, amount, interestRate, startDate, endDate, monthlyPayment, remainingBalance) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	             stmt.setString(1, loan.getLoanId());
	             stmt.setString(2, loan.getAccountNumber());
	             stmt.setDouble(3, loan.getAmount());
	             stmt.setDouble(4, loan.getInterestRate());
	             stmt.setDate(5, new java.sql.Date(loan.getStartDate().getTime()));
	             stmt.setDate(6, new java.sql.Date(loan.getEndDate().getTime()));
	             stmt.setDouble(7, loan.getMonthlyPayment());
	             stmt.setDouble(8, loan.getRemainingBalance());
	             stmt.executeUpdate();
	        } 
	    }
	    
	    public List<Loan> getLoansForAccount(String accountNumber) throws SQLException {
	        List<Loan> loans = new ArrayList<>();
	        String query = "SELECT * FROM loans WHERE accountNumber = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	        	stmt.setString(1, accountNumber);
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    Loan loan = new Loan(
	                            rs.getString("accountNumber"),
	                            rs.getDouble("amount"),
	                            rs.getDouble("interestRate"),
	                            rs.getDate("startDate"),
	                            rs.getDate("endDate"),
	                            rs.getDouble("monthlyPayment"),
	                            rs.getDouble("remainingBalance")
	                    );
	                    loans.add(loan);
	                }
	            }
	        }
	        return loans;
	    }

	    public void updateLoan(String accountNumber, double amount) throws SQLException {
	        String query = "UPDATE loans SET amount = ? WHERE accountNumber = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(query)) {
	        	stmt.setDouble(1, amount);
	        	stmt.setString(2, accountNumber);
	        	stmt.executeUpdate();
	        }
	    }
	    
	}