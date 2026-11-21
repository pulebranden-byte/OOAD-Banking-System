
package controller;

import model.*;
import database.AccountDAO;
import view.OpenAccountView;

import javax.swing.*;

public class AccountController {

    private OpenAccountView view;
    private AccountDAO accountDAO;

    public AccountController(OpenAccountView view) {
        this.view = view;
        this.accountDAO = new AccountDAO();

        this.view.getOpenAccountButton()
                .addActionListener(e -> openAccount());
    }

    private void openAccount() {
        try {
            String customerId = view.getCustomerId();
            String accountNumber = view.getAccountNumber();
            String accountType = view.getAccountType();
            String branch = "Main Branch";

            if (customerId.isEmpty() || accountNumber.isEmpty()
                    || view.getInitialDeposit().isEmpty()) {
                JOptionPane.showMessageDialog(view,
                        "Please complete all required fields.");
                return;
            }

            double initialDeposit =
                    Double.parseDouble(view.getInitialDeposit());

            Customer owner = null;

            for (Customer customer : BankData.getCustomers()) {
                if (customer.getCustomerId().equals(customerId)) {
                    owner = customer;
                    break;
                }
            }

            if (owner == null) {
                JOptionPane.showMessageDialog(view,
                        "Customer not found. Please register first.");
                return;
            }

            for (Customer customer : BankData.getCustomers()) {
                for (Account existingAccount : customer.getAccounts()) {
                    if (existingAccount.getAccountNumber().equals(accountNumber)) {
                        JOptionPane.showMessageDialog(view,
                                "This account number already exists.");
                        return;
                    }
                }
            }

            String employerCompany = null;
            String employerAddress = null;

            Account account;

            switch (accountType) {
                case "Savings Account":
                    account = new SavingsAccount(
                            accountNumber, initialDeposit, branch, owner);
                    break;

                case "Investment Account":
                    account = new InvestmentAccount(
                            accountNumber, initialDeposit, branch, owner);
                    break;

                case "Cheque Account":
                    employerCompany = view.getEmployerCompany();
                    employerAddress = view.getEmployerAddress();

                    account = new ChequeAccount(
                            accountNumber, initialDeposit, branch, owner,
                            employerCompany, employerAddress);
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Invalid account type.");
            }

            if (accountDAO.saveAccount(account, customerId, branch,
                    employerCompany, employerAddress)) {

                owner.addAccount(account);

                JOptionPane.showMessageDialog(view,
                        accountType + " opened successfully!\n"
                                + "Account Number: " + accountNumber
                                + "\nInitial Balance: BWP "
                                + String.format("%.2f", initialDeposit));

            } else {
                JOptionPane.showMessageDialog(view,
                        "Account could not be saved to the database.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view,
                    "Please enter a valid deposit amount.");

        } catch (IllegalArgumentException
                 | UnsupportedOperationException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }
}
