package project3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.AbstractListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Preston Garno
 */
public class AccountController {

    private AccountState model;
    private AccountPanel gui;

    public AccountController() {
        this.model = new AccountState();
        this.gui = new AccountPanel(model);
        setActions();
    }

    public AbstractTableModel getAccountState() {
        return model;
    }

    public AccountPanel getGui() {
        return gui;
    }

    public void setActions() {
        gui.setActionTopMenu(forEditSelected);
        gui.setActionButtons(forAddButton, forUpdateButton, forDeleteButton, forClearButton);
    }

    ActionListener forEditSelected = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(";)");
        }
    };
    
    ActionListener forClearButton = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.clearTextFields();
		}
	};

    ActionListener forAddButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] textFromFields = gui.getInputFields();
            int accountNumber = -1;
            double accountBalance = 0;
            double minBalance = -1;
            double interestRate = -1;
            
            try{
            	accountNumber = Integer.parseInt(textFromFields[0]);
            } catch (NumberFormatException n){
            	JOptionPane.showMessageDialog(null, "Invalid Account number!\nMust be a positive integer.");
            } 
            
            String accountOwner = textFromFields[1];
            
            GregorianCalendar dateOpened = gui.getSelectedDate();
            
            try {
            	accountBalance = Double.parseDouble(textFromFields[2]);
            } catch (NumberFormatException n){
            	JOptionPane.showMessageDialog(null, "Invalid Balance!\nMust be an integer.");
            }
            
            if (gui.isCheckingSelected() == true) {
            	double monthlyFee = -1;
            	try{
            		monthlyFee = Double.parseDouble(textFromFields[3]);
            	}catch (NumberFormatException n){
                	JOptionPane.showMessageDialog(null, "Invalid Monthly Fee!\nMust be a positive integer.");
            	}
                try{
                	model.addAccount(new CheckingAccount(accountNumber, accountOwner, dateOpened, accountBalance, monthlyFee));
                    gui.setSelectedAccount(model.getRowCount() - 1);
                } catch (Exception n){
                	JOptionPane.showMessageDialog(null, "Invalid Input!");
                }
            } else {
                try{
                	minBalance = Double.parseDouble(textFromFields[4]);
                    interestRate = Double.parseDouble(textFromFields[5]);
                } catch (NumberFormatException f){
                	JOptionPane.showMessageDialog(null, "Invalid Balance!\nMust be an integer.");
                }
                try{
                	model.addAccount(new SavingsAccount(accountNumber, accountOwner, dateOpened, accountBalance, minBalance, interestRate));
                    gui.setSelectedAccount(model.getRowCount() - 1);
                } catch (Exception n){
                	JOptionPane.showMessageDialog(null, "Invalid Input!");
                }
            }
        }
    };

    ActionListener forUpdateButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] textFromFields = gui.getInputFields();
            int accountNumber = -1;
            double accountBalance = 0;
            double minBalance = -1;
            double interestRate = -1;
            
            try{
            	accountNumber = Integer.parseInt(textFromFields[0]);
            } catch (NumberFormatException n){
            	JOptionPane.showMessageDialog(null, "Invalid Account number!\nMust be a positive integer.");
            } 
            
            String accountOwner = textFromFields[1];
            
            GregorianCalendar dateOpened = gui.getSelectedDate();
            
            try {
            	accountBalance = Double.parseDouble(textFromFields[2]);
            } catch (NumberFormatException n){
            	JOptionPane.showMessageDialog(null, "Invalid Balance!\nMust be an integer.");
            }
            
            if (gui.isCheckingSelected() == true) {
            	double monthlyFee = -1;
            	try{
            		monthlyFee = Double.parseDouble(textFromFields[3]);
            	}catch (NumberFormatException n){
                	JOptionPane.showMessageDialog(null, "Invalid Monthly Fee!\nMust be a positive integer.");
            	}
                try{
                	model.updateAccount(gui.getSelectedAccountIndex(), new CheckingAccount(accountNumber, accountOwner, dateOpened, accountBalance, monthlyFee));
                    gui.setSelectedAccount(model.getRowCount() - 1);
                } catch (Exception n){
                	JOptionPane.showMessageDialog(null, "Invalid Input!");
                }
            } else {
                try{
                	minBalance = Double.parseDouble(textFromFields[4]);
                    interestRate = Double.parseDouble(textFromFields[5]);
                } catch (NumberFormatException f){
                	JOptionPane.showMessageDialog(null, "Invalid Balance!\nMust be an integer.");
                }
                try{
                	model.updateAccount(gui.getSelectedAccountIndex(), new SavingsAccount(accountNumber, accountOwner, dateOpened, accountBalance, minBalance, interestRate));
                    gui.setSelectedAccount(model.getRowCount() - 1);
                } catch (Exception n){
                	JOptionPane.showMessageDialog(null, "Invalid Input!");
                }
            }
        }
    };
    
    ActionListener forDeleteButton = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			//add confirm deletion window
			int indexForDeleted = gui.getSelectedAccountIndex();
			if(indexForDeleted != -1){
				model.deleteAccount(indexForDeleted);
			}
		}
	};
}