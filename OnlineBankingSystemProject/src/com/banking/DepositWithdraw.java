package com.banking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DepositWithdraw extends JPanel {

	    private JTextField depositWithdrawAccountField;
	    private JTextField depositWithdrawAmountField;
	    private BankService bankService;

	    public DepositWithdraw(BankService bankService) {
	        this.bankService = bankService;
	        initialize();
	    }

	    private void initialize() {
	        setLayout(null);

	        JLabel lblAccount = new JLabel("Account Number:");
	        lblAccount.setBounds(30, 30, 120, 25);
	        add(lblAccount);

	        depositWithdrawAccountField = new JTextField();
	        depositWithdrawAccountField.setBounds(160, 30, 200, 25);
	        add(depositWithdrawAccountField);
	        depositWithdrawAccountField.setColumns(10);

	        JLabel lblDepositWithdrawAmount = new JLabel("Amount:");
	        lblDepositWithdrawAmount.setBounds(30, 70, 120, 25);
	        add(lblDepositWithdrawAmount);

	        depositWithdrawAmountField = new JTextField();
	        depositWithdrawAmountField.setBounds(160, 70, 200, 25);
	        add(depositWithdrawAmountField);
	        depositWithdrawAmountField.setColumns(10);

	        JButton depositButton = new JButton("Deposit");
	        depositButton.setBounds(160, 110, 100, 25);
	        add(depositButton);

	        JButton withdrawButton = new JButton("Withdraw");
	        withdrawButton.setBounds(270, 110, 90, 25);
	        add(withdrawButton);

	        depositButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    String accountNumber = depositWithdrawAccountField.getText();
	                    double amount = Double.parseDouble(depositWithdrawAmountField.getText());
	                    Account account = bankService.getAccount(accountNumber);
	                    if (account != null) {
	                        account.deposit(amount);
	                        bankService.updateAccountBalance(accountNumber, null, account.getBalance(), 0);
	                        Transaction transaction = new Transaction(UUID.randomUUID().toString(), accountNumber, amount, new Date(), "Deposit");
	                        bankService.addTransaction(transaction);
	                        JOptionPane.showMessageDialog(DepositWithdraw.this, "Deposit successful!");
	                    } else {
	                        JOptionPane.showMessageDialog(DepositWithdraw.this, "Account not found.");
	                    }
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });

	        withdrawButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    String accountNumber = depositWithdrawAccountField.getText();
	                    double amount = Double.parseDouble(depositWithdrawAmountField.getText());
	                    Account account = bankService.getAccount(accountNumber);
	                    if (account != null) {
	                        if (account.withdraw(amount)) {
	                        	bankService.updateAccountBalance(accountNumber, null, account.getBalance(), 0);
	                            Transaction transaction = new Transaction(UUID.randomUUID().toString(), accountNumber, -amount, new Date(), "Withdraw");
	                            bankService.addTransaction(transaction);
	                            JOptionPane.showMessageDialog(DepositWithdraw.this, "Withdrawal successful!");
	                        } else {
	                            JOptionPane.showMessageDialog(DepositWithdraw.this, "Insufficient funds.");
	                        }
	                    } else {
	                        JOptionPane.showMessageDialog(DepositWithdraw.this, "Account not found.");
	                    }
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });
	    }
	}