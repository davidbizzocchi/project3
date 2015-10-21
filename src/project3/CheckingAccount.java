package project3;

import java.util.GregorianCalendar;

/**
 * @author Preston Garno
 */
public class CheckingAccount extends Account {

    private static final long serialVersionUID = 1L;
    private double monthlyFee;

    public CheckingAccount(int accountNumber, String owner, GregorianCalendar dateOpened, double balance, double MonthlyFee) {
        super(accountNumber, owner, dateOpened, balance);
        this.monthlyFee = monthlyFee;
    }

    // getter, setter, tostring and equals
    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    @Override
    public String toString() {
        return "Account{" + "accountNumber=" + super.getAccountNumber() + ", owner=" + super.getOwner() + ", dateOpened=" + getDateOpened() + ", balance=" + getBalance() + ", monthlyFee =" + monthlyFee + '}';

    }
    
    @Override
    public double getMinBalance() {
        return 0;
    }
    @Override
    public double getInterestRate() {
        return 0;
    }
    @Override
    public void setMinBalance(double minBalance) {
    }
    @Override
    public void setInterestRate(double interestRate) {
    }
}
