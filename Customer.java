
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {

    private String customerId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private boolean employed;

    private List<Account> accounts;

    // Constructor with four parameters
    public Customer(
            String customerId,
            String fullName,
            String phoneNumber,
            String email) {

        this(customerId, fullName, phoneNumber, email, false);
    }

    // Constructor with five parameters
    public Customer(
            String customerId,
            String fullName,
            String phoneNumber,
            String email,
            boolean employed) {

        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException(
                    "Customer ID is required."
            );
        }

        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException(
                    "Customer name is required."
            );
        }

        this.customerId = customerId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employed = employed;

        this.accounts = new ArrayList<>();
    }

    // Getters
    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmployed() {
        return employed;
    }

    // Return accounts without allowing direct modification
    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    // Add an account and prevent duplicate account numbers

    public void addAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException(
                    "Account cannot be null."
            );
        }

        if (account.getOwner() != this) {
            throw new IllegalArgumentException(
                    "This account does not belong to this customer."
            );
        }

        for (Account existingAccount : accounts) {

            if (existingAccount.getAccountNumber()
                    .equals(account.getAccountNumber())) {
                throw new IllegalArgumentException(
                        "An account with this account number already exists."
                );
            }
        }

        accounts.add(account);
    }

    // Remove an account from the customer's list
    public void removeAccount(Account account) {
        accounts.remove(account);
    }
}
