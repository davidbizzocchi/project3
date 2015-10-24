package project3;

import java.util.GregorianCalendar;

/**
 * @author Preston Garno
 */
public class SavingsAccount extends Account {

    private double minBalance;
    private double interestRate;

    public SavingsAccount(int accountNumber, String owner, GregorianCalendar dateOpened, double balance, double minBalance, double interestRate) {
        super(accountNumber, owner, dateOpened, balance);
        this.minBalance = minBalance;
        this.interestRate = interestRate;
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
        this.minBalance = minBalance;
    }
    @Override
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    @Override
    public double getMonthlyFee(){
        return 0;
    }
    @Override
    public void setMonthlyFee(double MonthlyFee){
    }

    @Override
    public String toString() {
        return Integer.toString(super.getAccountNumber()) + "  " + getOwner() + "  " + super.dateToString() + "  $" + getBalance() + "   Savings   $" + getInterestRate()+ "%   " + getMinBalance() + " min. bal.";
    }

	@Override
	public String getAccountTypeDescription() {
		return "Savings";
	}
}