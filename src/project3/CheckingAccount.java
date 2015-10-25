package project3;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author Preston Garno
 */
public class CheckingAccount extends Account {

    private static final long serialVersionUID = 1L;
    private double monthlyFee;

    public CheckingAccount(int accountNumber, String owner, GregorianCalendar dateOpened, double balance, double monthlyFee) {
        super(accountNumber, owner, dateOpened, balance);
        if (monthlyFee > 0){
        	this.monthlyFee = monthlyFee;
        } else {
        	throw new IllegalArgumentException();
        }
    }

    // getter, setter, tostring and equals
    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        if (monthlyFee > 0){
        	this.monthlyFee = monthlyFee;
        } else {
        	throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return Integer.toString(super.getAccountNumber()) + "  " + getOwner() + "  " + super.dateToString() + "  $" + getBalance() + "   Checking   $" + getMonthlyFee() + "/month";

    }
    
    @Override
    public double getMinBalance() {
        return -1;
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

	@Override
	public String getAccountTypeDescription() {
		return "Checking";
	}
}
