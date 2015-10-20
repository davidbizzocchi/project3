package project3;

import java.util.GregorianCalendar;

/**
 * @author Preston Garno
 */
public class SavingsAccount extends Account {

    private double minBalance;
    private double interestRate;

    public SavingsAccount(int accountNumber, String owner, GregorianCalendar dateOpened, double balance) {
        super(accountNumber, owner, dateOpened, balance);
    }

    public double getMinBalance() {
        return minBalance;
    }
    
    public double getInterestRate() {
        return interestRate;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "Account{" + "accountNumber=" + super.getAccountNumber() + ", owner=" + super.getOwner() + ", dateOpened=" + getDateOpened() + ", balance=" + getBalance() + ", minBalance=" + minBalance + ", interestRate=" + interestRate + '}';
    }
}
