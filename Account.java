
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class Account {

    private String accountNumber;
    private double balance;
    private String branch;
    private final Customer owner;

    private final List<Transaction> transactions;

    public Account(
            String accountNumber,
            double initialBalance,
            String branch,
            Customer owner) {

        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException(
                    "Account number is required."
            );
        }

        if (initialBalance < 0) {
            throw new IllegalArgumentException(
                    "Initial balance cannot be negative."
            );
        }

        if (branch == null || branch.isBlank()) {
            throw new IllegalArgumentException(
                    "Branch name is required."
            );
        }

        if (owner == null) {
            throw new IllegalArgumentException(
                    "An account must belong to a customer."
            );
        }

        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.branch = branch;
        this.owner = owner;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getBranch() {
        return branch;
    }

    public Customer getOwner() {
        return owner;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Deposit must be greater than zero."
            );
        }

        balance += amount;
        recordTransaction("DEPOSIT", amount);
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Withdrawal must be greater than zero."
            );
        }

        if (amount > balance) {
            throw new IllegalArgumentException(
                    "Insufficient funds."
            );
        }

        balance -= amount;
        recordTransaction("WITHDRAWAL", amount);
    }

    protected void addInterest(double amount) {

        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Interest cannot be negative."
            );
        }

        if (amount > 0) {
            balance += amount;
            recordTransaction("INTEREST", amount);
        }
    }

    private void recordTransaction(
            String transactionType,
            double amount) {

        String transactionId = UUID.randomUUID().toString();

        Transaction transaction = new Transaction(
                transactionId,
                transactionType,
                amount
        );

        transactions.add(transaction);
    }
}
