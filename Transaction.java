
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private final String transactionId;
    private final String transactionType;
    private final double amount;
    private final LocalDateTime dateTime;

    public Transaction(
            String transactionId,
            String transactionType,
            double amount) {

        if (transactionId == null || transactionId.isBlank()) {
            throw new IllegalArgumentException(
                    "Transaction ID is required."
            );
        }

        if (transactionType == null || transactionType.isBlank()) {
            throw new IllegalArgumentException(
                    "Transaction type is required."
            );
        }

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Transaction amount must be greater than zero."
            );
        }

        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return "Transaction ID: " + transactionId
                + ", Type: " + transactionType
                + ", Amount: BWP " + String.format("%.2f", amount)
                + ", Date: " + dateTime.format(formatter);
    }
}
