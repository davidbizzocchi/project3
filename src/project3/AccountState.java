package project3;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.AbstractListModel;

/**
 * @author Preston Garno
 */
public class AccountState extends AbstractListModel {
    // essentially BankModel
    private ArrayList<Account> accounts;
    
    public AccountState(){
        accounts = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }
    
    public ArrayList getAccounts(){
        return accounts;
    }
    
    public void addSavingsAccount(int accountNumber, String owner, GregorianCalendar dateOpened, double balance, double minBalance, double interestRate){
        accounts.add(new SavingsAccount(accountNumber, owner, dateOpened, balance, minBalance, interestRate));
    }
    public void addCheckingAccount(int accountNumber, String owner, GregorianCalendar dateOpened, double balance, double MonthlyFee){
        accounts.add(new CheckingAccount(accountNumber, owner, dateOpened, balance, MonthlyFee));
    }
    
    public void deleteAccount(int index){
        accounts.remove(index);
        super.fireIntervalRemoved(this, index, index);
    }
    
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
        return accounts.get(index);
    }
    
    @Override
    public String toString(){
        return "IS THIS THING ON";
    }
}
