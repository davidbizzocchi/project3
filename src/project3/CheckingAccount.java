
package project3;

import java.util.GregorianCalendar;

/**
 * @author Preston Garno
 */

public class CheckingAccount extends Account {
    
       private static final long serialVersionUID = 1L;
       private double monthlyFee;

    public CheckingAccount(int accountNumber, String owner, GregorianCalendar dateOpened, double balance) {
        super(accountNumber, owner, dateOpened, balance);
    }

}
