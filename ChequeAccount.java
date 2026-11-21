
package model;

public class ChequeAccount extends Account {

    private final String employerCompanyName;
    private final String employerAddress;

    public ChequeAccount(
            String accountNumber,
            double initialBalance,
            String branch,
            Customer owner,
            String employerCompanyName,
            String employerAddress) {

        super(accountNumber, initialBalance, branch, owner);

        if (!owner.isEmployed()) {
            throw new IllegalArgumentException(
                    "Only employed customers can open a Cheque Account."
            );
        }

        if (employerCompanyName == null
                || employerCompanyName.isBlank()) {
            throw new IllegalArgumentException(
                    "Employer company name is required."
            );
        }

        if (employerAddress == null
                || employerAddress.isBlank()) {
            throw new IllegalArgumentException(
                    "Employer address is required."
            );
        }

        this.employerCompanyName = employerCompanyName;
        this.employerAddress = employerAddress;
    }

    public String getEmployerCompanyName() {
        return employerCompanyName;
    }

    public String getEmployerAddress() {
        return employerAddress;
    }
}

