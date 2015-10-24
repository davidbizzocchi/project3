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
        model.addAccount(new CheckingAccount(1234, "John", new GregorianCalendar(2014, 9, 23), 555.55, 33.33));
        model.addAccount(new CheckingAccount(4321, "Dave", new GregorianCalendar(2015, 10, 23), 555.55, 33.33));
        this.gui = new AccountPanel(model);
        setActions();
    }

    public AbstractListModel getAccountState() {
        return model;
    }

    public AccountPanel getGui() {
        return gui;
    }

    public void setActions() {
        gui.setActionTopMenu(forEditSelected);
        gui.setActionButtons(forAddButton, forUpdateButton, forDeleteButton);
    }

    public void deleteAccount(int index) {
        model.deleteAccount(index);
    }

    ActionListener forEditSelected = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(";)");
        }
    };

    ActionListener forAddButton = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] textFromFields = gui.getInputFields();
            if (gui.isCheckingSelected() == true) {
                System.out.println("Checking is selected!");
                int accountNumber = Integer.parseInt(textFromFields[0]);
                String accountOwner = textFromFields[1];
                //get and format date field temp fix to set fixed date
                GregorianCalendar dateOpened = gui.getSelectedDate();
                double accountBalance = Double.parseDouble(textFromFields[3]);
                double monthlyFee = Double.parseDouble(textFromFields[4]);
                model.addAccount(new CheckingAccount(accountNumber, accountOwner, dateOpened, accountBalance, monthlyFee));
            } else {
                System.out.println("Savings is selected!");
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
        	//model.updateaccount isn't getting called
        	//gui.ischeckingselected doesnt work correctly
        	
            String[] textFromFields = gui.getInputFields();
            if (gui.isCheckingSelected() == true) {
                System.out.println("Checking is selected!");
                int accountNumber = Integer.parseInt(textFromFields[0]);
                String accountOwner = textFromFields[1];
                //get and format date field temp fix to set fixed date
                GregorianCalendar dateOpened = gui.getSelectedDate();
                double accountBalance = Double.parseDouble(textFromFields[3]);
                double monthlyFee = Double.parseDouble(textFromFields[4]);
                //LOL getSource()
                model.updateAccount(gui.getSelectedAccountIndex(), new CheckingAccount(accountNumber, accountOwner, dateOpened, accountBalance, monthlyFee));
            } else {
                System.out.println("Savings is selected!");
                int accountNumber = Integer.parseInt(textFromFields[0]);
                String accountOwner = textFromFields[1];
                //get and format date field temp fix to set fixed date
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
		public void actionPerformed(ActionEvent arg0) {
			//
			model.deleteAccount(gui.getSelectedAccountIndex());
		}
	};
}