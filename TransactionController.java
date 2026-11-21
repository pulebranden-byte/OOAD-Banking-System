
package controller;

import database.TransactionDAO;
import view.TransactionView;

import javax.swing.*;

public class TransactionController {

    private TransactionView view;
    private TransactionDAO transactionDAO;

    public TransactionController(TransactionView view) {
        this.view = view;
        this.transactionDAO = new TransactionDAO();

        this.view.getSearchButton()
                .addActionListener(e -> searchTransactions());
    }

    private void searchTransactions() {

        String accountNumber = view.getAccountNumber();

        if (accountNumber.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Please enter an account number.");
            return;
        }

        String history =
                transactionDAO.getTransactionHistory(accountNumber);

        view.displayTransactions(history);
    }
}
