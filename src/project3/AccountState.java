package project3;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.AbstractListModel;

/**
 * @author Preston Garno
 */
public class AccountState extends AbstractListModel {
    // essentially BankModel
	// Make it so that saved any account object that gets passed to it
    private ArrayList<Account> accounts;
    //MAKE REUSABLE

    public AccountState(){
        accounts = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }
    
    public ArrayList getAccounts(){
        return accounts;
    }
    
    public void addAccount(Account a){
    	accounts.add(a);
    	fireContentsChanged(accounts, 0, getSize());
    }
    
    public void deleteAccount(int index){
        accounts.remove(index);
        super.fireContentsChanged(accounts, 0, getSize());
    }
    
    public void updateAccount(int index, Account a){
    	accounts.set(index, a);
    	fireContentsChanged(accounts, 0, getSize());
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
        return accounts.get(index).toString();
    }
    
    @Override
    public String toString(){
        return "IS THIS THING ON";
    }
}