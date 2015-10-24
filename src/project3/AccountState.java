package project3;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.AbstractListModel;

/**
 * @author Preston Garno
 */
public class AccountState extends AbstractListModel {
    // essentially BankModel
<<<<<<< HEAD
	// Make it so that saved any account object that gets passed to it
    private ArrayList<Account> accounts;
    //MAKE REUSABLE
=======
    private ArrayList<Account> accounts;
    
>>>>>>> 524b416d8db06c4b4c17f7a9c15d7f2638fc04d3
    public AccountState(){
        accounts = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }
    
    public ArrayList getAccounts(){
        return accounts;
    }
    
<<<<<<< HEAD
    public void addAccount(Account a){
    	accounts.add(a);
    	fireContentsChanged(accounts, 0, getSize());
=======
    public void addSavingsAccount(int accountNumber, String owner, GregorianCalendar dateOpened, double balance, double minBalance, double interestRate){
        accounts.add(new SavingsAccount(accountNumber, owner, dateOpened, balance, minBalance, interestRate));
    }
    public void addCheckingAccount(int accountNumber, String owner, GregorianCalendar dateOpened, double balance, double MonthlyFee){
        accounts.add(new CheckingAccount(accountNumber, owner, dateOpened, balance, MonthlyFee));
>>>>>>> 524b416d8db06c4b4c17f7a9c15d7f2638fc04d3
    }
    
    public void deleteAccount(int index){
        accounts.remove(index);
        super.fireIntervalRemoved(this, index, index);
    }
    
<<<<<<< HEAD
    public void updateAccount(int index, Account a){
    	accounts.set(index, a);
    	super.fireContentsChanged(accounts, index, index);
=======
    public void updateSavingsAccount(int index, int accountNumber, String owner, GregorianCalendar dateOpened, double balance, double minBalance, double interestRate){
        accounts.get(index).setAccountNumber(accountNumber);
        accounts.get(index).setOwner(owner);
        accounts.get(index).setDateOpened(dateOpened);
        accounts.get(index).setBalance(balance);
        accounts.get(index).setMinBalance(minBalance);
        accounts.get(index).setInterestRate(interestRate);

    }
    public void updateCheckingAccount(int index, int accountNumber, String owner, GregorianCalendar dateOpened, double balance, double monthlyFee){
        accounts.get(index).setAccountNumber(accountNumber);
        accounts.get(index).setOwner(owner);
        accounts.get(index).setDateOpened(dateOpened);
        accounts.get(index).setBalance(balance);
        accounts.get(index).setMonthlyFee(monthlyFee);
>>>>>>> 524b416d8db06c4b4c17f7a9c15d7f2638fc04d3
    }
    
    public Object find(String KeyWord){
        throw new UnsupportedOperationException("not implemented");
    }
    
    public void sort(int type){
        
    }
    
    //loading and saving methods
    
    @Override
    public int getSize() {
        return accounts.size();
    }

    @Override
    public Object getElementAt(int index) {
<<<<<<< HEAD
        return accounts.get(index).toString();
=======
        return accounts.get(index);
>>>>>>> 524b416d8db06c4b4c17f7a9c15d7f2638fc04d3
    }
    
    @Override
    public String toString(){
        return "IS THIS THING ON";
    }
}
