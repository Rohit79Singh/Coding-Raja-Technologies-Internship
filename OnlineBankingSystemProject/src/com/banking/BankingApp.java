package com.banking;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

public class BankingApp {
	
	private JTabbedPane tabbedPane;
    private JFrame frame;
    private BankService bankService;

    public BankingApp() {
        bankService = new BankService(); 
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Online Banking System");
        //frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().setLayout(null);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        
        LoginRegister loginRegisterPanel = new LoginRegister(bankService, this);
        frame.getContentPane().add(loginRegisterPanel, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }

        public void showMainBankingPanel() {
            frame.getContentPane().removeAll();
            frame.repaint();
            //frame.setBounds(100, 100, 500, 400);
            frame.setSize(600, 400);
        
            tabbedPane = new JTabbedPane();
            frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

            AddAccount addAccountPanel = new AddAccount(bankService);
            tabbedPane.addTab("Add Account", null, addAccountPanel, null);

            TransferFunds transferFundsPanel = new TransferFunds(bankService);
            tabbedPane.addTab("Transfer Funds", null, transferFundsPanel, null);

            DepositWithdraw depositWithdrawPanel = new DepositWithdraw(bankService);
            tabbedPane.addTab("Deposit/Withdraw", null, depositWithdrawPanel, null);
            
            BalanceEnquire balanceEnquire = new BalanceEnquire(bankService);
            tabbedPane.addTab("Balance", null, balanceEnquire, null);
            
            AccountStatement accountStatement = new AccountStatement(bankService);
            tabbedPane.addTab("Account Statement", null, accountStatement, null); 
            
            LoanAccount loanAccount = new LoanAccount(bankService);
            tabbedPane.addTab("Take Loan", null, loanAccount, null); 
            
            frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
            
        //frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BankingApp window = new BankingApp();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}