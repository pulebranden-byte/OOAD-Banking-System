
package controller;

import database.AccountDAO;
import view.AccountView;

import javax.swing.*;

public class AccountViewController {

    private AccountView view;
    private AccountDAO accountDAO;

    public AccountViewController(AccountView view) {
        this.view = view;
        this.accountDAO = new AccountDAO();

        this.view.getSearchButton()
                .addActionListener(e -> searchAccount());
    }

    private void searchAccount() {

        String accountNumber = view.getAccountNumber();

        if (accountNumber.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Please enter an account number.");
            return;
        }

        String[] accountDetails =
                accountDAO.findAccount(accountNumber);

        if (accountDetails == null) {
            JOptionPane.showMessageDialog(view,
                    "Account not found or database connection failed.");
            return;
        }

        view.displayAccountDetails(
                accountDetails[0],
                accountDetails[1],
                accountDetails[2],
                Double.parseDouble(accountDetails[3])
        );
    }
}
