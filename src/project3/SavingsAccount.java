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

}
