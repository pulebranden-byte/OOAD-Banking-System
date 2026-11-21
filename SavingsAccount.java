
package model;

public class SavingsAccount extends Account
        implements InterestBearing {

    private static final double MONTHLY_INTEREST_RATE = 0.0005;

    public SavingsAccount(
            String accountNumber,
            double initialBalance,
            String branch,
            Customer owner) {

        super(accountNumber, initialBalance, branch, owner);
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException(
                "Withdrawals are not allowed on a Savings Account."
        );
    }

    @Override
    public void applyMonthlyInterest() {
        double interest = getBalance() * MONTHLY_INTEREST_RATE;
        addInterest(interest);
    }
}
