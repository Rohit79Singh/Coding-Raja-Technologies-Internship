package com.banking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BalanceEnquire extends JPanel {

	private JTextField accountField;
    //private JLabel balanceLabel;
    private BankService bankService;

    public BalanceEnquire(BankService bankService) {
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
        
        //balanceLabel = new JLabel("Balance: ");
        //balanceLabel.setBounds(30, 70, 120, 25);
        //add(balanceLabel);
        
        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(30, 70, 150, 25);
        add(checkBalanceButton);
        
        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	String AccountNumber = accountField.getText();
                    double balance = bankService.checkBalance(AccountNumber);
                    //balanceLabel.setText("Balance: "+" " + balance);
                    JOptionPane.showMessageDialog(BalanceEnquire.this, balance);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
