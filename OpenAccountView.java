
package view;

import javax.swing.*;
import java.awt.*;

public class OpenAccountView extends JFrame {

    private JTextField customerIdField;
    private JTextField accountNumberField;
    private JComboBox<String> accountTypeBox;
    private JTextField initialDepositField;
    private JTextField employerCompanyField;
    private JTextField employerAddressField;

    private JButton openAccountButton;
    private JButton backButton;

    public OpenAccountView() {

        setTitle("Open Account");
        setSize(520, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(244, 244, 244));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("OPEN BANK ACCOUNT", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 21));
        title.setForeground(new Color(52, 58, 64));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 12, 15));
        formPanel.setBackground(new Color(244, 244, 244));

        customerIdField = new JTextField();
        accountNumberField = new JTextField();

        accountTypeBox = new JComboBox<>(new String[]{
                "Savings Account",
                "Investment Account",
                "Cheque Account"
        });

        initialDepositField = new JTextField();
        employerCompanyField = new JTextField();
        employerAddressField = new JTextField();

        formPanel.add(new JLabel("Customer ID:"));
        formPanel.add(customerIdField);

        formPanel.add(new JLabel("Account Number:"));
        formPanel.add(accountNumberField);

        formPanel.add(new JLabel("Account Type:"));
        formPanel.add(accountTypeBox);

        formPanel.add(new JLabel("Initial Deposit:"));
        formPanel.add(initialDepositField);

        formPanel.add(new JLabel("Employer Company:"));
        formPanel.add(employerCompanyField);

        formPanel.add(new JLabel("Employer Address:"));
        formPanel.add(employerAddressField);

        openAccountButton = new JButton("Open Account");
        backButton = new JButton("Back");

        styleButton(openAccountButton);
        styleButton(backButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(244, 244, 244));
        buttonPanel.add(backButton);
        buttonPanel.add(openAccountButton);

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

    public String getCustomerId() {
        return customerIdField.getText().trim();
    }

    public String getAccountNumber() {
        return accountNumberField.getText().trim();
    }

    public String getAccountType() {
        return (String) accountTypeBox.getSelectedItem();
    }

    public String getInitialDeposit() {
        return initialDepositField.getText().trim();
    }

    public String getEmployerCompany() {
        return employerCompanyField.getText().trim();
    }

    public String getEmployerAddress() {
        return employerAddressField.getText().trim();
    }

    public JButton getOpenAccountButton() {
        return openAccountButton;
    }
}
