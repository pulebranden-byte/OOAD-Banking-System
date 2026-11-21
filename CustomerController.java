
package controller;

import model.Customer;
import model.BankData;
import database.CustomerDAO;
import view.CustomerRegistrationView;

import javax.swing.*;

public class CustomerController {

    private CustomerRegistrationView view;
    private CustomerDAO customerDAO;

    public CustomerController(CustomerRegistrationView view) {
        this.view = view;
        this.customerDAO = new CustomerDAO();

        this.view.getRegisterButton().addActionListener(e -> registerCustomer());
    }

    private void registerCustomer() {

        String customerId = view.getCustomerId();
        String fullName = view.getFullName();
        String phoneNumber = view.getPhoneNumber();
        String email = view.getEmail();
        boolean employed = view.isEmployed();

        if (customerId.isEmpty() || fullName.isEmpty()
                || phoneNumber.isEmpty() || email.isEmpty()) {

            JOptionPane.showMessageDialog(view,
                    "Please fill in all customer details.");
            return;
        }

        Customer customer = new Customer(
                customerId,
                fullName,
                phoneNumber,
                email,
                employed
        );

        if (customerDAO.saveCustomer(customer)) {

            BankData.addCustomer(customer);

            JOptionPane.showMessageDialog(view,
                    "Customer registered successfully!");

        } else {

            JOptionPane.showMessageDialog(view,
                    "Customer could not be saved. Please check the database.");
        }
    }
}
