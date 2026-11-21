
package model;

public class InvestmentAccount extends Account
        implements InterestBearing {

    private static final double MONTHLY_INTEREST_RATE = 0.05;
    private static final double MINIMUM_INITIAL_DEPOSIT = 500.00;

    public InvestmentAccount(
            String accountNumber,
            double initialDeposit,
            String branch,
            Customer owner) {

        super(
                accountNumber,
                validateInitialDeposit(initialDeposit),
                branch,
                owner
        );
    }

    private static double validateInitialDeposit(double amount) {
        if (amount < MINIMUM_INITIAL_DEPOSIT) {
            throw new IllegalArgumentException(
                    "Investment Account requires a minimum initial deposit of BWP 500."
            );
        }

        return amount;
    }

    @Override
    public void applyMonthlyInterest() {
        double interest = getBalance() * MONTHLY_INTEREST_RATE;
        addInterest(interest);
    }
}

