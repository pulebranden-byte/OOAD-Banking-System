
package view;

import javax.swing.*;
import java.awt.*;

public class AccountView extends JFrame {

    private JTextField accountNumberField;

    private JLabel customerNameLabel;
    private JLabel accountNumberLabel;
    private JLabel accountTypeLabel;
    private JLabel balanceLabel;

    private JButton searchButton;
    private JButton backButton;

    public AccountView() {

        setTitle("View Account");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(244, 244, 244));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("ACCOUNT DETAILS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 21));
        title.setForeground(new Color(52, 58, 64));

        // Search section
        JPanel searchPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        searchPanel.setBackground(new Color(244, 244, 244));

        accountNumberField = new JTextField();
        searchButton = new JButton("Search");

        searchPanel.add(new JLabel("Account Number:"));
        searchPanel.add(accountNumberField);
        searchPanel.add(new JLabel(""));
        searchPanel.add(searchButton);

        // Account details section
        JPanel detailsPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));

        customerNameLabel = new JLabel("-");
        accountNumberLabel = new JLabel("-");
        accountTypeLabel = new JLabel("-");
        balanceLabel = new JLabel("-");

        detailsPanel.add(new JLabel("Customer Name:"));
        detailsPanel.add(customerNameLabel);

        detailsPanel.add(new JLabel("Account Number:"));
        detailsPanel.add(accountNumberLabel);

        detailsPanel.add(new JLabel("Account Type:"));
        detailsPanel.add(accountTypeLabel);

        detailsPanel.add(new JLabel("Current Balance:"));
        detailsPanel.add(balanceLabel);

        backButton = new JButton("Back");

        styleButton(searchButton);
        styleButton(backButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(244, 244, 244));
        buttonPanel.add(backButton);

        backButton.addActionListener(e -> dispose());

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(searchPanel, BorderLayout.CENTER);
        mainPanel.add(detailsPanel, BorderLayout.SOUTH);

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

    public void displayAccountDetails(
            String customerName,
            String accountNumber,
            String accountType,
            double balance) {

        customerNameLabel.setText(customerName);
        accountNumberLabel.setText(accountNumber);
        accountTypeLabel.setText(accountType);
        balanceLabel.setText("BWP " + String.format("%.2f", balance));
    }
}
