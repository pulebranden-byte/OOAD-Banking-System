
package view;

import javax.swing.*;
import java.awt.*;

public class TransactionView extends JFrame {

    private JTextField accountNumberField;
    private JTextArea transactionArea;

    private JButton searchButton;
    private JButton backButton;

    public TransactionView() {

        setTitle("Transaction History");
        setSize(550, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(244, 244, 244));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("TRANSACTION HISTORY", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 21));
        title.setForeground(new Color(52, 58, 64));

        // Search section
        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        searchPanel.setBackground(new Color(244, 244, 244));

        accountNumberField = new JTextField();
        searchButton = new JButton("Search");

        searchPanel.add(new JLabel("Account Number:"), BorderLayout.WEST);
        searchPanel.add(accountNumberField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // Transaction display area
        transactionArea = new JTextArea();
        transactionArea.setEditable(false);
        transactionArea.setFont(new Font("Arial", Font.PLAIN, 13));
        transactionArea.setLineWrap(true);
        transactionArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(transactionArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(
                new Color(210, 210, 210)
        ));

        searchButton.setFont(new Font("Arial", Font.PLAIN, 13));
        backButton = new JButton("Back");

        styleButton(searchButton);
        styleButton(backButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(244, 244, 244));
        buttonPanel.add(backButton);

        backButton.addActionListener(e -> dispose());

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(searchPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 13));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(45, 45, 45));
        button.setFocusPainted(false);
    }

    public String getAccountNumber() {
        return accountNumberField.getText().trim();
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void displayTransactions(String transactions) {
        transactionArea.setText(transactions);
    }
}
