package com.banking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TransferFunds extends JPanel {

	    private JTextField fromAccountField;
	    private JTextField toAccountField;
	    private JTextField transferAmountField;
	    private BankService bankService;

	    public TransferFunds(BankService bankService) {
	        this.bankService = bankService;
	        initialize();
	    }

	    private void initialize() {
	        setLayout(null);

	        JLabel lblFromAccount = new JLabel("From Account:");
	        lblFromAccount.setBounds(30, 30, 120, 25);
	        add(lblFromAccount);

	        fromAccountField = new JTextField();
	        fromAccountField.setBounds(160, 30, 200, 25);
	        add(fromAccountField);
	        fromAccountField.setColumns(10);

	        JLabel lblToAccount = new JLabel("To Account:");
	        lblToAccount.setBounds(30, 70, 120, 25);
	        add(lblToAccount);

	        toAccountField = new JTextField();
	        toAccountField.setBounds(160, 70, 200, 25);
	        add(toAccountField);
	        toAccountField.setColumns(10);

	        JLabel lblAmount = new JLabel("Amount:");
	        lblAmount.setBounds(30, 110, 120, 25);
	        add(lblAmount);

	        transferAmountField = new JTextField();
	        transferAmountField.setBounds(160, 110, 200, 25);
	        add(transferAmountField);
	        transferAmountField.setColumns(10);

	        JButton transferButton = new JButton("Transfer");
	        transferButton.setBounds(160, 150, 100, 25);
	        add(transferButton);

	        transferButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    String fromAccount = fromAccountField.getText();
	                    String toAccount = toAccountField.getText();
	                    double amount = Double.parseDouble(transferAmountField.getText());
	                    if (bankService.transferFunds(fromAccount, toAccount, amount)) {
	                        JOptionPane.showMessageDialog(TransferFunds.this, "Transfer successful!");
	                    } else {
	                        JOptionPane.showMessageDialog(TransferFunds.this, "Transfer failed. Check account details and balance.");
	                    }
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });
	    }
	}