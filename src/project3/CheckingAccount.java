package project3;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * class for checking account information
 * 
 * @author Preston Garno
 * @author David Bizzocchi
 * 
 * @version November, 2015
 */
public class CheckingAccount extends Account {

    private static final long serialVersionUID = 1L;
    private double monthlyFee;

    public CheckingAccount(int accountNumber, String owner, 
    		GregorianCalendar dateOpened, double balance, 
    		double monthlyFee) {
        super(accountNumber, owner, dateOpened, balance);
        if (monthlyFee > 0){
        	this.monthlyFee = monthlyFee;
        } else {
        	throw new IllegalArgumentException();
        }
    }

    /*********************************************
     * get method for monthly fee
     * 
     * @return monthly fee for checking account
     ********************************************/
    public double getMonthlyFee() {
        return monthlyFee;
    }

    /***************************************************
     * setter for monthly fee on checking account
     * 
     * @param monthly fee to be set on checking account
     **************************************************/
    public void setMonthlyFee(double monthlyFee) {
        if (monthlyFee > 0){
        	this.monthlyFee = monthlyFee;
        } else {
        	throw new IllegalArgumentException();
        }
    }

    /*****************************************************
     * sets all info specific to checking account to string
     * 
     * @return string with all info from checking account
     *****************************************************/
    @Override
    public String toString() {
        return Integer.toString(super.getAccountNumber()) + "  " + 
    getOwner() + "  " + super.dateToString() + "  $" + getBalance() + 
    "   Checking   $" + getMonthlyFee() + "/month";

    }
    
    /**********************************************************
     * get method for minimum balance on account
     * 
     * @return -1, checking account doesnt require min balance
     *********************************************************/
    @Override
    public double getMinBalance() {
        return -1;
    }
    
    /**********************************************************
     * get method for interest rate on account
     * 
     * @return 0, checking account doesnt require interest rate
     *********************************************************/
    @Override
    public double getInterestRate() {
        return 0;
    }
    
    /**********************************************************
     * set method for minimum balance on account
     * 
     * @param minimum balance for account, not required for checking
     *********************************************************/
    @Override
    public void setMinBalance(double minBalance) {
    }
    
    /**********************************************************
     * set method for interest rate on account
     * 
     * @param interest rate for account, not required for checking
     *********************************************************/
    @Override
    public void setInterestRate(double interestRate) {
    }

    /**********************************************************
     * get method for type description of account
     * 
     * @return string containing type of account (Checking)
     *********************************************************/
	@Override
	public String getAccountTypeDescription() {
		return "Checking";
	}
}
