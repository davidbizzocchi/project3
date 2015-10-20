package project3;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 * @author Preston Garno
 */
public class AccountState extends AbstractListModel {
    // essentially BankModel
    private ArrayList<Account> accounts;
    
    public AccountState(){
        this.accounts = new ArrayList<>();
    }
    
    public void addSavingsAccount(){
        
    }
    public void addCheckingAccount(){
        
    }
    
    public Object find(String KeyWord){
        throw new UnsupportedOperationException("not implemented");
    }
    
    public void sort(int type){
        
    }
    
    //loading and saving methods here
    
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
        return "";
    }
}
