
package controller;

import model.Account;
import model.Customer;
import model.BankData;
import database.TransactionDAO;
import view.DepositView;

import javax.swing.*;

public class DepositController {

    private DepositView view;
    private TransactionDAO transactionDAO;

    public DepositController(DepositView view) {
        this.view = view;
        this.transactionDAO = new TransactionDAO();

        this.view.getDepositButton()
                .addActionListener(e -> depositMoney());
    }

    private void depositMoney() {
        try {
            String accountNumber = view.getAccountNumber();
            String amountText = view.getAmount();

            if (accountNumber.isEmpty() || amountText.isEmpty()) {
                JOptionPane.showMessageDialog(view,
                        "Please enter the account number and amount.");
                return;
            }

            double amount = Double.parseDouble(amountText);

            if (amount <= 0) {
                JOptionPane.showMessageDialog(view,
                        "Deposit amount must be greater than zero.");
                return;
            }

            Account foundAccount = null;

            for (Customer customer : BankData.getCustomers()) {
                for (Account account : customer.getAccounts()) {
                    if (account.getAccountNumber().equals(accountNumber)) {
                        foundAccount = account;
                        break;
                    }
                }

                if (foundAccount != null) {
                    break;
                }
            }

            if (foundAccount == null) {
                JOptionPane.showMessageDialog(view, "Account not found.");
                return;
            }

            if (transactionDAO.recordDeposit(accountNumber, amount)) {

                foundAccount.deposit(amount);

                JOptionPane.showMessageDialog(view,
                        "Deposit successful!\nNew Balance: BWP "
                                + String.format("%.2f", foundAccount.getBalance()));

            } else {
                JOptionPane.showMessageDialog(view,
                        "Deposit could not be saved. Check the database.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view,
                    "Please enter a valid numerical amount.");

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }
}
