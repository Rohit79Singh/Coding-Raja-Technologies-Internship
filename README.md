# Online Banking System

This project is an online banking system implemented in Java using Swing for the user interface and MySQL for the database. It provides functionality for account management, transaction history, funds transfer, balance enquiry, security measures, and loan management.

## Features

1. **Account Management**: Allows users to create and manage different types of accounts (savings, checking, etc.).
2. **Transaction History**: Stores and displays a history of user transactions.
3. **Funds Transfer**: Enables users to transfer funds between accounts.
4. **Balance Enquiry**: Allows users to view their account balances.
5. **Security Measures**: Implements user authentication, PIN codes, and secure transactions.
6. **Loan Management**: Provides options for users to apply for loans and manage loan payments.

## Tech Stack

- **Java**
- **Swing**
- **MySQL**
- **Object-Oriented Programming concepts**

## Setup and Installation

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Eclipse IDE
- MySQL server

### Database Setup

1. Install MySQL server and MySQL Workbench.
2. Create the database schema using the following SQL script:   

### Project Setup

2. **Open the Project in Eclipse**

    - Launch Eclipse IDE.
    - Select `File` > `Open Projects from File System...`.
    - Browse to the cloned repository directory and click `Finish`.

3. **Add JDBC Library**

    - Right-click on the project in the Project Explorer.
    - Select `Build Path` > `Add External Archives...`.
    - Add the MySQL JDBC driver jar file (e.g., `mysql-connector-java-8.0.23.jar`).

4. **Update Database Configuration**

    - Open the `BankApp.java` file.
    - Update the database connection string, username, and password with your MySQL configuration:

5. **Run the Application**

    - Right-click on the `BankApp` class.
    - Select `Run As` > `Java Application`.

## Usage

1. **Login**

    - Use the default username and password provided in the initial database setup or create new users using the database directly.

2. **Manage Accounts**

    - Create and manage different types of accounts from the account management panel.

3. **Transfer Funds**

    - Transfer funds between different accounts.

4. **View Transaction History**

    - View the history of transactions for each account.

5. **Balance Enquiry**

    - Check the current balance of accounts.

6. **Loan Management**

    - Apply for loans and manage loan payments from the loan management panel.

## License

This project is licensed under the MIT License.

## Acknowledgements

- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
- [Java Swing](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Eclipse IDE](https://www.eclipse.org/ide/)

 ```sql
    CREATE DATABASE BankingSystem;
    USE BankingSystem;

    CREATE TABLE Users (
        user_id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(50) NOT NULL,
        password VARCHAR(50) NOT NULL,
        pin_code INT NOT NULL
    );

    CREATE TABLE Accounts (
        account_id INT AUTO_INCREMENT PRIMARY KEY,
        user_id INT,
        account_type VARCHAR(50),
        balance DOUBLE,
        FOREIGN KEY (user_id) REFERENCES Users(user_id)
    );

    CREATE TABLE Transactions (
        transaction_id INT AUTO_INCREMENT PRIMARY KEY,
        account_id INT,
        amount DOUBLE,
        transaction_type VARCHAR(50),
        transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (account_id) REFERENCES Accounts(account_id)
    );

    CREATE TABLE Loans (
        loan_id INT AUTO_INCREMENT PRIMARY KEY,
        user_id INT,
        loan_amount DOUBLE,
        loan_status VARCHAR(50),
        FOREIGN KEY (user_id) REFERENCES Users(user_id)
    );
    ```
