package project3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Preston Garno
 * @version 0.1
 */
public class AccountController {
	
	/**model instance of AbstractTableModel subclass "AccountState"*/
    private AccountState model;
    /**gui instance of AccountPanel, extends JPanel.*/
    private AccountPanel gui;
    
    
    /******************************************
    * Constructor for class, instantiates new AccountState and 
    * AccountModel objects.  Also sets ActionListeners for the 
    * panel class as defined in the method setActions()
    ******************************************/
    public AccountController() {
        this.model = new AccountState();
        this.gui = new AccountPanel(model);
        setActions();
    }
    
    
    /******************************************
    * Returns current instance of the model class
    ******************************************/
    public AbstractTableModel getAccountState() {
        return model;
    }
    
    
    /******************************************
    * Returns current instance of the GUI
    ******************************************/
    public AccountPanel getGui() {
        return gui;
    }
    
    /******************************************
    * Attaches the appropriate ActionListeners to the buttons in gui
    ******************************************/
    public void setActions() {
        gui.setActionTopMenu(forSaveToBinary, forOpenBinary, forSaveToText, forLoadFromText, forEditSelected);
        gui.setActionButtons(forAddButton, forUpdateButton, forDeleteButton, forClearButton);
    }
    
    
    /******************************************
    * ActionListener to edit selected accounts
    ******************************************/
    ActionListener forEditSelected = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(";)");
        }
    };
    
    
    /******************************************
    * ActionListener to Clear the text input fields
    ******************************************/
    ActionListener forClearButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.clearTextFields();
        }
    };
    
    
    /******************************************
    * Adds an account in the model class from user input
    ******************************************/
    ActionListener forAddButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] textFromFields = gui.getInputFields();
            int accountNumber = -1;
            double accountBalance = 0;
            double minBalance = -1;
            double interestRate = -1;

            try {
                accountNumber = Integer.parseInt(textFromFields[0]);
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Invalid Account number!\nMust be a positive integer.");
            }

            String accountOwner = textFromFields[1];

            GregorianCalendar dateOpened = gui.getSelectedDate();

            try {
                accountBalance = Double.parseDouble(textFromFields[2]);
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Invalid Balance!\nMust be an integer.");
            }

            if (gui.isCheckingSelected() == true) {
                double monthlyFee = -1;
                try {
                    monthlyFee = Double.parseDouble(textFromFields[3]);
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Invalid Monthly Fee!\nMust be a positive integer.");
                }
                try {
                    model.addAccount(new CheckingAccount(accountNumber, accountOwner, dateOpened, accountBalance, monthlyFee));
                    gui.setSelectedAccount(model.getRowCount() - 1);
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(null, "Invalid Input!");
                }
            } else {
                try {
                    minBalance = Double.parseDouble(textFromFields[4]);
                    interestRate = Double.parseDouble(textFromFields[5]);
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "Invalid Balance!\nMust be an integer.");
                }
                try {
                    model.addAccount(new SavingsAccount(accountNumber, accountOwner, dateOpened, accountBalance, minBalance, interestRate));
                    gui.setSelectedAccount(model.getRowCount() - 1);
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(null, "Invalid Input!");
                }
            }
            gui.adjustColumnWidths();
        }
    };
    
    
    /******************************************
    * Updates selected account from user input in panel class
    ******************************************/
    ActionListener forUpdateButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedAccountIndex = gui.getSelectedAccountIndex();
            String[] textFromFields = gui.getInputFields();
            int accountNumber = -1;
            double accountBalance = 0;
            double minBalance = -1;
            double interestRate = -1;

            try {
                accountNumber = Integer.parseInt(textFromFields[0]);
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Invalid Account number!\nMust be a positive integer.");
            }

            String accountOwner = textFromFields[1];

            GregorianCalendar dateOpened = gui.getSelectedDate();

            try {
                accountBalance = Double.parseDouble(textFromFields[2]);
            } catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Invalid Balance!\nMust be an integer.");
            }

            if (gui.isCheckingSelected() == true) {
                double monthlyFee = -1;
                try {
                    monthlyFee = Double.parseDouble(textFromFields[3]);
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Invalid Monthly Fee!\nMust be a positive integer.");
                }
                try {
                    model.updateAccount(gui.getSelectedAccountIndex(), new CheckingAccount(accountNumber, accountOwner, dateOpened, accountBalance, monthlyFee));
                    gui.setSelectedAccount(selectedAccountIndex);
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(null, "Invalid Input!");
                }
            } else {
                try {
                    interestRate = Double.parseDouble(textFromFields[4]);
                    minBalance = Double.parseDouble(textFromFields[5]);
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "Invalid Balance!\nMust be an integer.");
                }
                try {
                    model.updateAccount(gui.getSelectedAccountIndex(), new SavingsAccount(accountNumber, accountOwner, dateOpened, accountBalance, interestRate, minBalance));
                    gui.setSelectedAccount(selectedAccountIndex);
                } catch (Exception n) {
                    JOptionPane.showMessageDialog(null, "Invalid Input!");
                }
            }
            gui.adjustColumnWidths();
        }
    };
    
    
    /******************************************
    * Actionlistener deletes selected account, calls delete() method in model
    ******************************************/
    ActionListener forDeleteButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //add confirm deletion window
            int indexForDeleted = gui.getSelectedAccountIndex();
            if (indexForDeleted != -1) {
                model.deleteAccount(indexForDeleted);
                gui.setSelectedAccount(model.getRowCount() - 1);
                gui.adjustColumnWidths();
            }
        }
    };
    
    
    /******************************************
    * ActionListener calls saveAsBinary() method in the model class, saves to binary
    ******************************************/
    ActionListener forSaveToBinary = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            //set windows look and feel for fileChooser, this one looks
            //like crap
            File accountFile = new File(System.getProperty("user.dir"));
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileHidingEnabled(false);
            if (fileChooser.showSaveDialog(gui) == JFileChooser.APPROVE_OPTION) {
                accountFile = fileChooser.getSelectedFile();
            }
            try {
                model.saveAsBinary(accountFile);
            } catch (FileNotFoundException fileNotFound) {
                fileNotFound.printStackTrace();
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        }
    };
    
    
    /******************************************
    * ActionListener calls loadFromBinary() method in the model class, loads binary file
    ******************************************/
    ActionListener forOpenBinary = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
            File accountFile = new File(System.getProperty("user.dir"));
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(gui) == JFileChooser.APPROVE_OPTION) {
			  accountFile = fileChooser.getSelectedFile();
			}
			try {
                model.loadFromBinary(accountFile);
                gui.adjustColumnWidths();
            } catch (FileNotFoundException fileNotFound) {
                fileNotFound.printStackTrace();
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            } catch (ClassNotFoundException class404){
            	class404.printStackTrace();
            }
		}
	};
	
	
    /******************************************
    * ActionListener calls saveAsText() method in the model class, saves to txt file
    ******************************************/
    ActionListener forSaveToText = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //set windows look and feel for fileChooser, this one looks
            //like crap
            File accountFile = new File(System.getProperty("user.dir"));
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileHidingEnabled(false);
            if (fileChooser.showSaveDialog(gui) == JFileChooser.APPROVE_OPTION) {
                accountFile = fileChooser.getSelectedFile();
            }
            try {
                model.saveAsText(accountFile);
            } catch (FileNotFoundException fileNotFound) {
                fileNotFound.printStackTrace();
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        }
    };
    
    ActionListener forLoadFromText = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
            File accountFile = new File(System.getProperty("user.dir"));
            ArrayList<String> rawDataRead = new ArrayList<>();
            ArrayList<Account> newAccounts = new ArrayList<>();
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(gui) == JFileChooser.APPROVE_OPTION) {
				accountFile = fileChooser.getSelectedFile();
			}
			
			try {
				Scanner sc = new Scanner(accountFile);
				while (sc.hasNextLine()) {
					//array of strings now, just raw data
		            rawDataRead.add(sc.nextLine());
		        }
                gui.adjustColumnWidths();
            } catch (FileNotFoundException fileNotFound) {
                fileNotFound.printStackTrace();
            }
			
			for (int i = 0; i < rawDataRead.size(); i++){
				//surround loop with try catch block, so that it can read files that have a couple lines changed
				String[] accountDataAsString = rawDataRead.get(i).split(";;");
				
				int accountNumber;
				double balance, fee, interest, minBal;
				String owner, desc, dateOpened;
				
				accountNumber = Integer.parseInt(accountDataAsString[0]);
				owner = accountDataAsString[1];
				dateOpened = accountDataAsString[2];
				balance = Double.parseDouble(accountDataAsString[3]);
				desc = accountDataAsString[4];
				try{
					fee = Double.parseDouble(accountDataAsString[5]);
				} catch (NumberFormatException n){
					fee = -1;
				}
				
				String withoutPercentage = accountDataAsString[6].replace("%", "");
				interest = Double.parseDouble(withoutPercentage);
				
				String withoutDashes = accountDataAsString[7].replace("-", "0");
				minBal = Double.parseDouble(withoutDashes);
				
				DateFormatSymbols dfs = new DateFormatSymbols();
	            String[] monthsAsString = dfs.getMonths();
	            
	            String[] splitUpDate = dateOpened.split(" ");
	            int day,month,year;
	            
	            month = 13;
	            for (int m = 0; m < 12; m++) {
	                if (splitUpDate[0].equals(monthsAsString[m])) {
	                    month = m;
	                }
	            }
	            
	            String withoutComma = splitUpDate[1].replace(",", "");
	            day = Integer.parseInt(withoutComma);
	            year = Integer.parseInt(splitUpDate[2]);
	            
	            GregorianCalendar date = new GregorianCalendar(year, month, day);
				
				if(desc.equals("Checking")){
					newAccounts.add(new CheckingAccount(accountNumber, owner, date, balance, fee));
				} else if (desc.equals("Savings")){
					newAccounts.add(new SavingsAccount(accountNumber, owner, date, balance, interest, minBal));
				}
			}
			if(newAccounts.size()>0){
				model.readFromText(newAccounts);
			} else {
				// throw new error, say unreadable
				System.out.println("Unreadable file!");
			}
		}
	};
}
