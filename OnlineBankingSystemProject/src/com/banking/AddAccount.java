package com.banking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddAccount extends JPanel {
    private JTextField accountNumberField;
    private JTextField initialBalanceField;
    private JTextField accountTypeField;
    private BankService bankService;

    public AddAccount(BankService bankService) {
        this.bankService = bankService;
        initialize();
    }

    private void initialize() {
        setLayout(null);

        JLabel lblAccountNumber = new JLabel("Account Number:");
        lblAccountNumber.setBounds(30, 30, 120, 25);
        add(lblAccountNumber);

        accountNumberField = new JTextField();
        accountNumberField.setBounds(160, 30, 200, 25);
        add(accountNumberField);
        accountNumberField.setColumns(10);

        JLabel lblInitialBalance = new JLabel("Initial Balance:");
        lblInitialBalance.setBounds(30, 80, 120, 25);
        add(lblInitialBalance);

        initialBalanceField = new JTextField();
        initialBalanceField.setBounds(160, 80, 200, 25);
        add(initialBalanceField);
        initialBalanceField.setColumns(10);

        JLabel lblAccountType = new JLabel("Account Type:");
        lblAccountType.setBounds(30, 130, 120, 25);
        add(lblAccountType);

        accountTypeField = new JTextField();
        accountTypeField.setBounds(160, 130, 200, 25);
        add(accountTypeField);
        accountTypeField.setColumns(10);
        
        JButton addAccountButton = new JButton("Add Account");
        addAccountButton.setBounds(160, 180, 150, 25);
        add(addAccountButton);

        addAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	String newAccountNumber = accountNumberField.getText();
                    double initialBalance = Double.parseDouble(initialBalanceField.getText());
                    String accountType = accountTypeField.getText();
                    Account newAccount = new Account(newAccountNumber, initialBalance, accountType);
                    bankService.addAccount(newAccount);
                    JOptionPane.showMessageDialog(AddAccount.this, "Account added successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}