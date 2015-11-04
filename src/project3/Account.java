package project3;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Class that stores and maintains all information of accounts
 * 
 * @author Preston Garno
 * @author David Bizzocchi
 * 
 * @version November, 2015
 */
public abstract class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    private int accountNumber;
    private String owner;
    private GregorianCalendar dateOpened;
    private double balance;

    public Account(int accountNumber, String owner,
    		GregorianCalendar dateOpened, double balance) {
        if (accountNumber > 0){
            this.accountNumber = accountNumber;
        } else {
            throw new IllegalArgumentException();
        }
        this.owner = owner;
        this.dateOpened = dateOpened;
        this.balance = balance;
    }
    
    // abstract methods, specific to savings/checking accounts
    public abstract void setInterestRate(double interestRate);
    public abstract void setMinBalance(double minBalance);
    public abstract void setMonthlyFee(double monthlyFee);
    public abstract double getInterestRate();
    public abstract double getMinBalance();
    public abstract double getMonthlyFee();
    public abstract String getAccountTypeDescription();
    
    /*********************************************************
     * setter method for account number for account
     * 
     * @param accountNumber to set account to
     ********************************************************/
    public void setAccountNumber(int accountNumber) {
        if(accountNumber > 0){
        	this.accountNumber = accountNumber;
        } else {
        	throw new IllegalArgumentException();
        }
    }

    /********************************************************
     * setter method for owner of account
     * 
     * @param owner, name of account owner
     *******************************************************/
    public void setOwner(String owner) {
        if (owner.trim().isEmpty() || owner.equals(null)){
        	throw new IllegalArgumentException();
        } else {
        	this.owner = owner;
        }
    }

    /*********************************************************
     * setter method for account date opened
     * 
     * @param dateOpened, calendar date account was opened
     ********************************************************/
    public void setDateOpened(GregorianCalendar dateOpened) {
        this.dateOpened = dateOpened;
    }

    /********************************************************
     * setter method for balance on account
     * 
     * @param balance, amount of currency in account
     *******************************************************/
    public void setBalance(double balance) {
    	this.balance = balance;
    }

    /********************************************************
     * getter method for ID for Serial version
     * 
     * @return ID of serial version
     *******************************************************/
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /*******************************************************
     * getter method for account number
     * 
     * @return account number for selected account
     ******************************************************/
    public int getAccountNumber() {
        return accountNumber;
    }

    /*******************************************************
     * getter method for account owner
     * 
     * @return account owner in string form
     ******************************************************/
    public String getOwner() {
        return owner;
    }

    /*******************************************************
     * getter method for returning date account was opened
     * 
     * @return calendar date account was opened
     ******************************************************/
    public GregorianCalendar getDateOpened() {
        return dateOpened;
    }

    /*******************************************************
     * getter method for balance in account
     * 
     * @return balance in account
     *******************************************************/
    public double getBalance() {
        return balance;
    }

    /*******************************************************
     * returns string with all account information
     * 
     * @return string containing account info
     ******************************************************/
    @Override
    public String toString() {
        return "Account{" + "accountNumber=" + accountNumber + 
        		", owner=" + owner + ", dateOpened=" + dateOpened + 
        		", balance=" + balance + '}';
    }
    
    /*******************************************************
     * returns date of account opened in string format
     * 
     * @return string of date account was opened
     *******************************************************/
    public String dateToString(){
        return (dateOpened.getDisplayName(GregorianCalendar.MONTH, 2, 
        		Locale.ENGLISH) + " " + 
        		dateOpened.get(GregorianCalendar.DAY_OF_MONTH) + ", " 
        		+ dateOpened.get(GregorianCalendar.YEAR));
    }
}
