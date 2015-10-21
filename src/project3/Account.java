package project3;

import java.io.Serializable;
import java.util.GregorianCalendar;

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
    
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
    
}
