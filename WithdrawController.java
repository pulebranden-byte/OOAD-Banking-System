
package controller;

import model.Account;
import model.Customer;
import model.BankData;
import database.TransactionDAO;
import view.WithdrawView;

import javax.swing.*;

public class WithdrawController {

    private WithdrawView view;
    private TransactionDAO transactionDAO;

    public WithdrawController(WithdrawView view) {
        this.view = view;
        this.transactionDAO = new TransactionDAO();

        this.view.getWithdrawButton()
                .addActionListener(e -> withdrawMoney());
    }

    private void withdrawMoney() {
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
                        "Withdrawal amount must be greater than zero.");
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

            // Check the business rules before changing the database
            if (amount > foundAccount.getBalance()) {
                JOptionPane.showMessageDialog(view,
                        "Insufficient funds.");
                return;
            }

            if (transactionDAO.recordWithdrawal(accountNumber, amount)) {

                foundAccount.withdraw(amount);

                JOptionPane.showMessageDialog(view,
                        "Withdrawal successful!\nNew Balance: BWP "
                                + String.format("%.2f", foundAccount.getBalance()));

            } else {
                JOptionPane.showMessageDialog(view,
                        "Withdrawal could not be saved. Check the database balance.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view,
                    "Please enter a valid numerical amount.");

        } catch (IllegalArgumentException
                 | UnsupportedOperationException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }
}
