package com.banking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AccountStatement extends JPanel {

	private JTextField accountField;
    //private JLabel accountlabel;
    private BankService bankService;
	
	public AccountStatement(BankService bankService) {
		this.bankService = bankService;
        initialize();
	}

	private void initialize() {
		setLayout(null);

        JLabel accountNumber = new JLabel("Account Number:");
        accountNumber.setBounds(30, 30, 120, 25);
        add(accountNumber);

        accountField = new JTextField();
        accountField.setBounds(160, 30, 200, 25);
        add(accountField);
        accountField.setColumns(10);
        
        JButton accountStatement = new JButton("Get Statement");
        accountStatement.setBounds(30, 70, 150, 25);
        add(accountStatement);
        
        accountStatement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	String AccountNumber = accountField.getText();
                    List<Transaction> history = bankService.getTransactionHistory(AccountNumber);
                   /* for(int i=0;i<history.size();i++) {
            			System.out.println(history.get(i));
            		}*/
                    JOptionPane.showMessageDialog(AccountStatement.this, history);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        
	}
	
}