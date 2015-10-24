package project3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.AbstractListModel;
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
            if (gui.isCheckingSelected() == true) {
                int accountNumber = Integer.parseInt(textFromFields[0]);
                String accountOwner = textFromFields[1];
                //get and format date field temp fix to set fixed date
                GregorianCalendar dateOpened = gui.getSelectedDate();
                double accountBalance = Double.parseDouble(textFromFields[3]);
                double monthlyFee = Double.parseDouble(textFromFields[4]);
                model.addAccount(new CheckingAccount(accountNumber, accountOwner, dateOpened, accountBalance, monthlyFee));
            } else {
                int accountNumber = Integer.parseInt(textFromFields[0]);
                String accountOwner = textFromFields[1];
                //get and format date field temp fix to set fixed date
                GregorianCalendar dateOpened = gui.getSelectedDate();
                double accountBalance = Double.parseDouble(textFromFields[3]);
                double minBalance = Double.parseDouble(textFromFields[5]);
                double interestRate = Double.parseDouble(textFromFields[6]);
                model.addAccount(new SavingsAccount(accountNumber, accountOwner, dateOpened, accountBalance, minBalance, interestRate));
            }
        }
    };

    ActionListener forUpdateButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] textFromFields = gui.getInputFields();
            if (gui.isCheckingSelected() == true) {
                int accountNumber = Integer.parseInt(textFromFields[0]);
                String accountOwner = textFromFields[1];
                GregorianCalendar dateOpened = gui.getSelectedDate();
                double accountBalance = Double.parseDouble(textFromFields[3]);
                double monthlyFee = Double.parseDouble(textFromFields[4]);
                model.updateAccount(gui.getSelectedAccountIndex(), new CheckingAccount(accountNumber, accountOwner, dateOpened, accountBalance, monthlyFee));
            } else {
                int accountNumber = Integer.parseInt(textFromFields[0]);
                String accountOwner = textFromFields[1];
                GregorianCalendar dateOpened = gui.getSelectedDate();
                double accountBalance = Double.parseDouble(textFromFields[3]);
                double minBalance = Double.parseDouble(textFromFields[5]);
                double interestRate = Double.parseDouble(textFromFields[6]);
                model.updateAccount(gui.getSelectedAccountIndex(),new SavingsAccount(accountNumber, accountOwner, dateOpened, accountBalance, minBalance, interestRate));
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