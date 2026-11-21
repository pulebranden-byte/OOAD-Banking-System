
package view;

import controller.*;
import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public MainView() {

        setTitle("Banking System");
        setSize(650, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(244, 244, 244));

        // Header
        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setBackground(new Color(52, 58, 64));
        header.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel title = new JLabel("BANKING SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel(
                "Welcome - Select an option below",
                SwingConstants.CENTER
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(Color.WHITE);

        header.add(title);
        header.add(subtitle);

        // Menu buttons
        JPanel menu = new JPanel(new GridLayout(3, 2, 15, 15));
        menu.setBackground(new Color(244, 244, 244));
        menu.setBorder(BorderFactory.createEmptyBorder(35, 45, 35, 45));

        JButton registerButton = createButton("Register Customer");
        JButton accountButton = createButton("Open Account");
        JButton depositButton = createButton("Deposit Money");
        JButton withdrawButton = createButton("Withdraw Money");
        JButton viewAccountButton = createButton("View Account");
        JButton transactionButton = createButton("Transaction History");

        menu.add(registerButton);
        menu.add(accountButton);
        menu.add(depositButton);
        menu.add(withdrawButton);
        menu.add(viewAccountButton);
        menu.add(transactionButton);

        // Footer
        JLabel footer = new JLabel(
                "Banking System | Computer Systems Engineering",
                SwingConstants.CENTER
        );

        footer.setFont(new Font("Arial", Font.PLAIN, 11));
        footer.setForeground(Color.GRAY);
        footer.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        // Button actions
        registerButton.addActionListener(e -> {
            CustomerRegistrationView view = new CustomerRegistrationView();
            new CustomerController(view);
        });

        accountButton.addActionListener(e -> {
            OpenAccountView view = new OpenAccountView();
            new AccountController(view);
        });

        depositButton.addActionListener(e -> {
            DepositView view = new DepositView();
            new DepositController(view);
        });

        withdrawButton.addActionListener(e -> {
            WithdrawView view = new WithdrawView();
            new WithdrawController(view);
        });

        viewAccountButton.addActionListener(e -> {
            AccountView view = new AccountView();
            new AccountViewController(view);
        });

        transactionButton.addActionListener(e -> {
            TransactionView view = new TransactionView();
            new TransactionController(view);
        });

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(menu, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setForeground(new Color(45, 45, 45));
        button.setBackground(new Color(255, 255, 255));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
        ));

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainView());
    }
}
