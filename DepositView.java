
package view;

import javax.swing.*;
import java.awt.*;

public class DepositView extends JFrame {

    private JTextField accountNumberField;
    private JTextField amountField;

    private JButton depositButton;
    private JButton backButton;

    public DepositView() {

        setTitle("Deposit Money");
        setSize(450, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(244, 244, 244));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JLabel title = new JLabel("DEPOSIT MONEY", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 21));
        title.setForeground(new Color(52, 58, 64));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 12, 18));
        formPanel.setBackground(new Color(244, 244, 244));

        accountNumberField = new JTextField();
        amountField = new JTextField();

        formPanel.add(new JLabel("Account Number:"));
        formPanel.add(accountNumberField);

        formPanel.add(new JLabel("Amount (BWP):"));
        formPanel.add(amountField);

        depositButton = new JButton("Deposit");
        backButton = new JButton("Back");

        styleButton(depositButton);
        styleButton(backButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(244, 244, 244));
        buttonPanel.add(backButton);
        buttonPanel.add(depositButton);

        backButton.addActionListener(e -> dispose());

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
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

    public String getAmount() {
        return amountField.getText().trim();
    }

    public JButton getDepositButton() {
        return depositButton;
    }
}
