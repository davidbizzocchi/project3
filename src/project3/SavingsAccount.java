package project3;

import java.util.GregorianCalendar;

/**
 * Class for savings account information
 * 
 * @author Preston Garno
 * @author David Bizzocchi
 * 
 * @version November, 2015
 */
public class SavingsAccount extends Account {

    private double minBalance;
    private double interestRate;

    public SavingsAccount(int accountNumber, String owner, 
    		GregorianCalendar dateOpened, double balance, 
    		double interestRate, double minBalance) {
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

    /**********************************************************
     * get method for minimum balance on account
     * 
     * @return minimum balance on account
     *********************************************************/
    @Override
    public double getMinBalance() {
        return minBalance;
    }

    /**********************************************************
     * get method for interest rate on account
     * 
     * @return interest rate on account
     *********************************************************/
    @Override
    public double getInterestRate() {
        return interestRate;
    }

    /**********************************************************
     * set method for minimum balance on account, ensures it is
     * higher than current balance of account
     * 
     * @param minimum balance for account
     *********************************************************/
    @Override
    public void setMinBalance(double minBalance) {
        if (minBalance > getBalance()) {
            throw new IllegalArgumentException();
        } else {
            this.minBalance = minBalance;
        }
    }

    /**********************************************************
     * set method for interest rate on account
     * 
     * @param interest rate for account, must be positive
     *********************************************************/
    @Override
    public void setInterestRate(double interestRate) {
        if (interestRate < 0) {
            throw new IllegalArgumentException();
        } else {
            this.interestRate = interestRate;
        }
    }

    /*********************************************
     * get method for monthly fee
     * 
     * @return -1, monthly fee not required for savings
     ********************************************/
    @Override
    public double getMonthlyFee() {
        return -1;
    }

    /***************************************************
     * setter for monthly fee on checking account
     * 
     * @param monthly fee, not requited for savings
     **************************************************/    
    @Override
    public void setMonthlyFee(double MonthlyFee) {
    }

    /*****************************************************
     * sets all info specific to savings account to string
     * 
     * @return string with all info from savings account
     *****************************************************/
    @Override
    public String toString() {
        return Integer.toString(super.getAccountNumber()) + "  " + 
    getOwner() + "  " + super.dateToString() + "  $" + getBalance() + 
    "   Savings   $" + getInterestRate() + "%   " + getMinBalance() + 
    " min. bal.";
    }

    /**********************************************************
     * get method for type description of account
     * 
     * @return string containing type of account (Savings)
     *********************************************************/
    @Override
    public String getAccountTypeDescription() {
        return "Savings";
    }
}
