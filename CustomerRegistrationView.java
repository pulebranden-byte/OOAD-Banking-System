
package view;

import javax.swing.*;
import java.awt.*;

public class CustomerRegistrationView extends JFrame {

    private JTextField customerIdField;
    private JTextField fullNameField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JCheckBox employedCheckBox;

    private JButton registerButton;
    private JButton backButton;

    public CustomerRegistrationView() {

        setTitle("Register Customer");
        setSize(500, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(244, 244, 244));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("CUSTOMER REGISTRATION", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 21));
        title.setForeground(new Color(52, 58, 64));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 12, 15));
        formPanel.setBackground(new Color(244, 244, 244));

        customerIdField = new JTextField();
        fullNameField = new JTextField();
        phoneNumberField = new JTextField();
        emailField = new JTextField();
        employedCheckBox = new JCheckBox("Employed");

        formPanel.add(new JLabel("Customer ID:"));
        formPanel.add(customerIdField);

        formPanel.add(new JLabel("Full Name:"));
        formPanel.add(fullNameField);

        formPanel.add(new JLabel("Phone Number:"));
        formPanel.add(phoneNumberField);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        formPanel.add(new JLabel("Employment Status:"));
        formPanel.add(employedCheckBox);

        registerButton = new JButton("Register Customer");
        backButton = new JButton("Back");

        styleButton(registerButton);
        styleButton(backButton);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(244, 244, 244));
        buttonPanel.add(backButton);
        buttonPanel.add(registerButton);

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

    public String getFullName() {
        return fullNameField.getText().trim();
    }

    public String getPhoneNumber() {
        return phoneNumberField.getText().trim();
    }

    public String getEmail() {
        return emailField.getText().trim();
    }

    public boolean isEmployed() {
        return employedCheckBox.isSelected();
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}
