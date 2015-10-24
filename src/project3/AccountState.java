package project3;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.AbstractListModel;
import javax.swing.table.AbstractTableModel;

/**
 * @author Preston Garno
 */
public class AccountState extends AbstractTableModel {
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
    	fireTableDataChanged();
	}
    
    public void deleteAccount(int index){
        accounts.remove(index);
        fireTableDataChanged();
    }
    
    public void updateAccount(int index, Account a){
    	accounts.set(index, a);
    	fireTableDataChanged();
    }
    
    public Object find(String KeyWord){
        throw new UnsupportedOperationException("not implemented");
    }
    
    public void sort(int type){
        
    }

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public int getRowCount() {
		return accounts.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		switch (col) {
		case 0:
			return accounts.get(row).getAccountNumber();
		case 1:
			return accounts.get(row).getOwner();
		case 2:
			return accounts.get(row).dateToString();
		case 3:
			return accounts.get(row).getBalance();
		case 4:
			return accounts.get(row).getAccountTypeDescription();
		case 5:
			if(accounts.get(row) instanceof CheckingAccount){
				return accounts.get(row).getMonthlyFee();
			} else {
				return "--";
			}
		case 6:
			return accounts.get(row).getInterestRate();
		case 7:
			return accounts.get(row).getMinBalance();
		default:
			return null;
		}
	}
	
	private String[] colNames = new String[] {"Acct No.", "Owner", "Date Opened", "Balance", "Type","Fee","Interest","Min. Bal."};

	@Override
	public String getColumnName(int col) {
	    return colNames[col];
	}
    
    //loading and saving methods

}