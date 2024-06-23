package com.banking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginRegister extends JPanel {

	    private JTextField usernameField;
	    private JPasswordField passwordField;
	    private BankService bankService;
	    private BankingApp bankingApp;

	    public LoginRegister(BankService bankService, BankingApp bankingApp) {
	        this.bankService = bankService;
	        this.bankingApp = bankingApp;
	        initialize();
	    }

	    private void initialize() {
	        setLayout(null);

	        JLabel lblUsername = new JLabel("Username:");
	        lblUsername.setBounds(50, 50, 100, 25);
	        add(lblUsername);

	        usernameField = new JTextField();
	        usernameField.setBounds(160, 50, 150, 25);
	        add(usernameField);
	        usernameField.setColumns(10);

	        JLabel lblPassword = new JLabel("Password:");
	        lblPassword.setBounds(50, 90, 100, 25);
	        add(lblPassword);

	        passwordField = new JPasswordField();
	        passwordField.setBounds(160, 90, 150, 25);
	        add(passwordField);

	        JButton loginButton = new JButton("Login");
	        loginButton.setBounds(50, 130, 100, 25);
	        add(loginButton);

	        JButton registerButton = new JButton("Register");
	        registerButton.setBounds(210, 130, 100, 25);
	        add(registerButton);

	        loginButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    String username = usernameField.getText();
	                    String password = new String(passwordField.getPassword());
	                    User loggedInUser = bankService.authenticateUser(username, password);
	                    if (loggedInUser != null) {
	                        bankingApp.showMainBankingPanel();
	                    } else {
	                        JOptionPane.showMessageDialog(LoginRegister.this, "Invalid credentials.");
	                    }
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });

	        registerButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    String username = usernameField.getText();
	                    String password = new String(passwordField.getPassword());
	                    User user = new User(username, password);
	                    bankService.addUser(user);
	                    JOptionPane.showMessageDialog(LoginRegister.this, "User registered successfully!");
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });
	    }
	}