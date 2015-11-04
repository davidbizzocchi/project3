package project3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.table.AbstractTableModel;

/**
 * Class for JTable information on bank application GUI
 * 
 * @author Preston Garno
 * @author David Bizzocchi
 * 
 * @version November, 2015
 */
public class AccountState extends AbstractTableModel {
	
	/**ArrayList of objects of type Account*/
    private ArrayList<Account> accounts;
    
    /******************************************
    * Constructor instantiates Arraylist
    ******************************************/
    public AccountState() {
        this.accounts = new ArrayList<>();
    }
    
    /******************************************
    * Returns the ArraayList of Accounts
    ******************************************/
    public ArrayList getAccounts() {
        return accounts;
    }
    
    /******************************************
    * Adds account passed in as parameter to ArrayList accounts
    * @param a Account object to add to list
    ******************************************/
    public void addAccount(Account a) {
        accounts.add(a);
        fireTableDataChanged();
    }
    
    /******************************************
    * Deletes account from ArrayList using specified index
    * @param index index of account to be deleted
    ******************************************/
    public void deleteAccount(int index) {
        accounts.remove(index);
        fireTableDataChanged();
    }
    
    /******************************************
    * Updates account using a new Account object and replaces object
    * in ArrayList accounts[index]
    * @param index Index of account to update
    * @param a Updated account
    ******************************************/
    public void updateAccount(int index, Account a) {
        if (index != -1) {
            accounts.set(index, a);
        }
        fireTableDataChanged();
    }
    
    /******************************************
    * Finds Accounts using specified Keyword
    * @param KeyWord String that you would like to match
    ******************************************/
    public Object find(String KeyWord) {
        throw new UnsupportedOperationException("not implemented");
    }
    
    /******************************************
    * Not implemented yet
    ******************************************/
    public void sort(int type) {

    }
    
    /******************************************
    * Method overrides super method and returns 
    * constant column count of 8
    ******************************************/
    @Override
    public int getColumnCount() {
        return 8;
    }
    
    /******************************************
    * Method overrides super method and returns the length
    * of the ArrayList accounts
    ******************************************/
    @Override
    public int getRowCount() {
        return accounts.size();
    }
    
    /******************************************
    * Method overrides super method and returns value at each cell
    * @return Value at each cell
    ******************************************/
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
                if (accounts.get(row).getMonthlyFee() == -1) {
                    return "--";
                } else {
                    return accounts.get(row).getMonthlyFee();
                }
            case 6:
                String returnValue = Double.toString(accounts.get(row).
                		getInterestRate()) + "%";
                return returnValue;
            case 7:
                if (accounts.get(row).getMinBalance() == -1) {
                    return "--";
                } else {
                    return accounts.get(row).getMinBalance();
                }
            default:
                return null;
        }
    }
    
    /**An array containing column header information*/
    private String[] colNames = new String[]{"Acct No.", "Owner", 
    		"Date Opened", "Balance", "Type", "Fee", "Interest", 
    		"Min. Bal."};
    
    /******************************************
    * Method overrides super method and returns the name of the
    * column specified by parameter "col"
    * @param col the index of the column 
    * @return colNames[col] the name of the column
    ******************************************/
    @Override
    public String getColumnName(int col) {
        return colNames[col];
    }

    /******************************************
    * Method saves the current account model to a binary file
    * @param accountFile the File the ObjectOutputStream will write to
    * @throws FileNotFoundException, IOException
    ******************************************/
    public void saveAsBinary(File accountFile) 
    		throws FileNotFoundException, IOException {
        FileOutputStream fileOut;
        fileOut = new FileOutputStream(accountFile);
        ObjectOutputStream objectOut;
        objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(accounts);
        objectOut.close();
    }
    
    
    /******************************************
    * Method loads a binary file to the state
    * @param accountFile the File the ObjectInputStream will read
    * @throws FileNotFoundException, IOException, 
    * {@link ClassNotFoundException}
    ******************************************/
    public void loadFromBinary(File accountFile) 
    		throws IOException, ClassNotFoundException {
    	FileInputStream fileIn;
    	fileIn = new FileInputStream(accountFile);
    	ObjectInputStream objectIn = new ObjectInputStream (fileIn);
    	Object object = objectIn.readObject();
    	if (object instanceof ArrayList<?>){
    		accounts = (ArrayList<Account>) object;
    	} else {
    		System.out.print("Something Didn't work!");
    	}
    	fileIn.close();
    	fireTableDataChanged();
    }
    
    /**************************************************************
     * Method that saves file as text
     * 
     * @param accountFile, file to save as text
     * @throws IOException
     *************************************************************/
    public void saveAsText(File accountFile) throws IOException{
    	FileWriter writer = new FileWriter(accountFile); 
    	for(int row = 0; row < accounts.size(); row ++) {
    		for (int col = 0; col < 8; col++){
    			writer.write(this.getValueAt(row, col).toString() + 
    					";;");
    		}
    		writer.write(System.getProperty( "line.separator" ));
		}
    	writer.close();
    }
    
    /************************************************************
     * Method that reads accounts from text
     * 
     * @param accounts being read from text
     ***********************************************************/
    public void readFromText(ArrayList<Account> accounts){
    	clearAllAccounts();
    	this.accounts = accounts;
    	fireTableDataChanged();
    }
    
    /***********************************************************
     * clears accounts on file and gui
     **********************************************************/
    private void clearAllAccounts(){
    	accounts.clear();
    	fireTableDataChanged();
    }
}
