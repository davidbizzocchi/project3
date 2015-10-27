package project3;

import java.util.GregorianCalendar;

/**
 * @author Preston Garno
 */
public class SavingsAccount extends Account {

    private double minBalance;
    private double interestRate;

    public SavingsAccount(int accountNumber, String owner, GregorianCalendar dateOpened, double balance, double interestRate, double minBalance) {
        super(accountNumber, owner, dateOpened, balance);
        if (minBalance > balance) {
            throw new IllegalArgumentException();
        } else {
            this.minBalance = minBalance;
        }

        if (interestRate < 0) {
            throw new IllegalArgumentException();
        } else {
            this.interestRate = interestRate;
        }
    }

    @Override
    public double getMinBalance() {
        return minBalance;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void setMinBalance(double minBalance) {
        if (minBalance > getBalance()) {
            throw new IllegalArgumentException();
        } else {
            this.minBalance = minBalance;
        }
    }

    @Override
    public void setInterestRate(double interestRate) {
        if (interestRate < 0) {
            throw new IllegalArgumentException();
        } else {
            this.interestRate = interestRate;
        }
    }

    @Override
    public double getMonthlyFee() {
        return -1;
    }

    @Override
    public void setMonthlyFee(double MonthlyFee) {
    }

    @Override
    public String toString() {
        return Integer.toString(super.getAccountNumber()) + "  " + getOwner() + "  " + super.dateToString() + "  $" + getBalance() + "   Savings   $" + getInterestRate() + "%   " + getMinBalance() + " min. bal.";
    }

    @Override
    public String getAccountTypeDescription() {
        return "Savings";
    }
}
