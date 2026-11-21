
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    // Record a deposit
    public boolean recordDeposit(String accountNumber, double amount) {

        String updateSQL =
                "UPDATE Accounts SET balance = balance + ? WHERE account_number = ?";

        String insertSQL =
                "INSERT INTO Transactions (account_number, transaction_type, amount) " +
                        "VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement update =
                         connection.prepareStatement(updateSQL);
                 PreparedStatement insert =
                         connection.prepareStatement(insertSQL)) {

                update.setDouble(1, amount);
                update.setString(2, accountNumber);

                int rowsUpdated = update.executeUpdate();

                if (rowsUpdated == 0) {
                    connection.rollback();
                    return false;
                }

                insert.setString(1, accountNumber);
                insert.setString(2, "Deposit");
                insert.setDouble(3, amount);
                insert.executeUpdate();

                connection.commit();
                return true;

            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.out.println("Error recording deposit: " + e.getMessage());
            return false;
        }
    }

    // Record a withdrawal
    public boolean recordWithdrawal(String accountNumber, double amount) {

        String updateSQL =
                "UPDATE Accounts SET balance = balance - ? " +
                        "WHERE account_number = ? AND balance >= ?";

        String insertSQL =
                "INSERT INTO Transactions (account_number, transaction_type, amount) " +
                        "VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement update =
                         connection.prepareStatement(updateSQL);
                 PreparedStatement insert =
                         connection.prepareStatement(insertSQL)) {

                update.setDouble(1, amount);
                update.setString(2, accountNumber);
                update.setDouble(3, amount);

                int rowsUpdated = update.executeUpdate();

                if (rowsUpdated == 0) {
                    connection.rollback();
                    return false;
                }

                insert.setString(1, accountNumber);
                insert.setString(2, "Withdrawal");
                insert.setDouble(3, amount);
                insert.executeUpdate();

                connection.commit();
                return true;

            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.out.println("Error recording withdrawal: " + e.getMessage());
            return false;
        }
    }

    // Retrieve transaction history
    public String getTransactionHistory(String accountNumber) {

        String sql = "SELECT transaction_type, amount, transaction_date " +
                "FROM Transactions WHERE account_number = ? " +
                "ORDER BY transaction_date DESC";

        StringBuilder history = new StringBuilder();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {

                    history.append("Type: ")
                            .append(result.getString("transaction_type"))
                            .append("\nAmount: BWP ")
                            .append(String.format("%.2f",
                                    result.getDouble("amount")))
                            .append("\nDate: ")
                            .append(result.getTimestamp("transaction_date"))
                            .append("\n--------------------------\n");
                }
            }

            if (history.length() == 0) {
                return "No transactions found for this account.";
            }

            return history.toString();

        } catch (SQLException e) {
            System.out.println("Error retrieving transactions: " + e.getMessage());
            return "Could not retrieve transaction history.";
        }
    }
}
