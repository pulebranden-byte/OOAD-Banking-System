
package model;

import java.util.ArrayList;
import java.util.List;

public class BankData {

    private static final List<Customer> customers = new ArrayList<>();

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }
}

