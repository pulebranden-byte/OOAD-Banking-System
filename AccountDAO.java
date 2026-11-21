
package database;

import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    // Save a new account
    public boolean saveAccount(Account account, String customerId,
                               String branch, String employerCompany,
                               String employerAddress) {

        String sql = "INSERT INTO Accounts " +
                "(account_number, customer_id, account_type, balance, branch, " +
                "employer_company, employer_address) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, account.getAccountNumber());
            statement.setString(2, customerId);
            statement.setString(3, account.getClass().getSimpleName());
            statement.setDouble(4, account.getBalance());
            statement.setString(5, branch);
            statement.setString(6, employerCompany);
            statement.setString(7, employerAddress);

            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error saving account: " + e.getMessage());
            return false;
        }
    }

    // Search for an account and retrieve its details
    public String[] findAccount(String accountNumber) {

        String sql = "SELECT Customers.full_name, Accounts.account_number, " +
                "Accounts.account_type, Accounts.balance " +
                "FROM Accounts " +
                "INNER JOIN Customers ON Accounts.customer_id = Customers.customer_id " +
                "WHERE Accounts.account_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {

                    return new String[]{
                            result.getString("full_name"),
                            result.getString("account_number"),
                            result.getString("account_type"),
                            String.valueOf(result.getDouble("balance"))
                    };
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching account: " + e.getMessage());
        }

        return null;
    }
}
