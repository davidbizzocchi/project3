package project3;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author Preston Garno
 */
public abstract class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    private int accountNumber;
    private String owner;
    private GregorianCalendar dateOpened;
    private double balance;

    public Account(int accountNumber, String owner, GregorianCalendar dateOpened, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.dateOpened = dateOpened;
        this.balance = balance;
    }
    
    public abstract void setInterestRate(double interestRate);
    public abstract void setMinBalance(double minBalance);
    public abstract void setMonthlyFee(double monthlyFee);
    public abstract double getInterestRate();
    public abstract double getMinBalance();
    public abstract double getMonthlyFee();
    public abstract String getAccountTypeDescription();
    
    public void setAccountNumber(int accountNumber) {
        if(accountNumber > 0){
        	this.accountNumber = accountNumber;
        } else {
        	throw new IllegalArgumentException();
        }
    }

    public void setOwner(String owner) {
        if (owner.trim().isEmpty() || owner.equals(null)){
        	throw new IllegalArgumentException();
        } else {
        	this.owner = owner;
        }
    }

    public void setDateOpened(GregorianCalendar dateOpened) {
        this.dateOpened = dateOpened;
    }

    public void setBalance(double balance) {
    	this.balance = balance;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public GregorianCalendar getDateOpened() {
        return dateOpened;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" + "accountNumber=" + accountNumber + ", owner=" + owner + ", dateOpened=" + dateOpened + ", balance=" + balance + '}';
    }
    
    public String dateToString(){
        return (dateOpened.getDisplayName(GregorianCalendar.MONTH, 2, Locale.ENGLISH) + " " + dateOpened.get(GregorianCalendar.DAY_OF_MONTH) + ", " + dateOpened.get(GregorianCalendar.YEAR));
    }
}
