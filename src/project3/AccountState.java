package project3;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.AbstractListModel;
import javax.swing.table.AbstractTableModel;

/**
 * @author Preston Garno
 */
public class AccountState extends AbstractTableModel {
    private ArrayList<Account> accounts;
    
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
    	if(index != -1){
    		accounts.set(index, a);
    	}
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
			if (accounts.get(row).getMonthlyFee() == -1){
				return "--";
			}else{
				return accounts.get(row).getMonthlyFee();
			}
		case 6:
			String returnValue = Double.toString(accounts.get(row).getInterestRate()) + "%";
			return returnValue;
		case 7:
			if (accounts.get(row).getMinBalance() == -1){
				return "--";
			} else {
				return accounts.get(row).getMinBalance();
			}
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