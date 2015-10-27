package project3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.GregorianCalendar;
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
        gui.setActionTopMenu(forSaveToBinary, forOpenBinary, forEditSelected);
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
}
