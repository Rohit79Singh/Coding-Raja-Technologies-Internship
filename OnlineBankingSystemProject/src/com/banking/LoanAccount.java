package com.banking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoanAccount extends JPanel {
	
	private JTextField accountNumber;
    private JTextField amount;
    private JTextField interestRate;
    private JTextField monthlyPayment;
    private BankService bankService;
	
    public LoanAccount(BankService bankService) {
        this.bankService = bankService;
        initialize();
    }

	private void initialize() {
		setLayout(null);
		
		JLabel lblAccountNumber = new JLabel("Account Number:");
        lblAccountNumber.setBounds(30, 30, 120, 25);
        add(lblAccountNumber);

        accountNumber = new JTextField();
        accountNumber.setBounds(160, 30, 200, 25);
        add(accountNumber);
        accountNumber.setColumns(10);

        JLabel loanAmount = new JLabel("Amount:");
        loanAmount.setBounds(30, 80, 120, 25);
        add(loanAmount);

        amount = new JTextField();
        amount.setBounds(160, 80, 200, 25);
        add(amount);
        amount.setColumns(10);

        JLabel interestRates = new JLabel("Interest Rate:");
        interestRates.setBounds(30, 130, 120, 25);
        add(interestRates);

        interestRate = new JTextField();
        interestRate.setBounds(160, 130, 200, 25);
        add(interestRate);
        interestRate.setColumns(10);
        
        JLabel monthlyPayments = new JLabel("Monthly Payment:");
        monthlyPayments.setBounds(30, 180, 120, 25);
        add(monthlyPayments);

        monthlyPayment = new JTextField();
        monthlyPayment.setBounds(160, 180, 200, 25);
        add(monthlyPayment);
        monthlyPayment.setColumns(10);
        
        JButton addLoanButton = new JButton("Take Loan");
        addLoanButton.setBounds(160, 230, 150, 25);
        add(addLoanButton);
		
        addLoanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	String newAccountNumber = accountNumber.getText();
                    double loanAmount = Double.parseDouble(amount.getText());
                    double rateOfInterest = Double.parseDouble(interestRate.getText());
                    double emi = Double.parseDouble(monthlyPayment.getText());
                    Loan newAccount = new Loan(newAccountNumber, loanAmount, rateOfInterest, null, null, emi, 0);
                    bankService.addLoan(newAccount);;
                    JOptionPane.showMessageDialog(LoanAccount.this, "Loan Credited Successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
	}
    
}
