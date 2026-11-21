
package database;

import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {

    public boolean saveCustomer(Customer customer) {

        String sql = "INSERT INTO Customers " +
                "(customer_id, full_name, phone_number, email, employed) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getCustomerId());
            statement.setString(2, customer.getFullName());
            statement.setString(3, customer.getPhoneNumber());
            statement.setString(4, customer.getEmail());
            statement.setBoolean(5, customer.isEmployed());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error saving customer: " + e.getMessage());
            return false;
        }
    }
}
