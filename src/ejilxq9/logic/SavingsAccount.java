package ejilxq9.logic;
/**
 * SavingAccount-klassen.
 *
 * @author Jens Lindström
 * @email ejilxq-9@studera.ltu.se
 * @course D0018D Objekorienterad programmering
 */

public class SavingsAccount {

    private double amount;
    private double interestRate;
    private String accountType = "Saving";
    private int accountNumber;

    public SavingsAccount(double amount, double interestRate, String accountType, int accountNumber) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String toString() {
        return accountNumber + " " + amount + " " + this.accountType +  " " + this.interestRate;
    }
}
