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
    // add methods to find, add, delete, and update accounts
    // add methods to sort accounts on required fields
    // add methods to load/save accounts from/to a binary file
    // add methods to load/save accounts from/to a text file
    // add methods to load/save accounts from/to an XML file
    // add other methods as needed
}
