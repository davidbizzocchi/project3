/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public AccountController(){
        this.model = new AccountState();
        model.addAccount(new CheckingAccount(1234, "John", new GregorianCalendar(2014, 9, 23), 555.55, 33.33));
        model.addAccount(new CheckingAccount(4321, "Dave", new GregorianCalendar(2015, 10, 23), 555.55, 33.33));
        this.gui = new AccountPanel(model);
        setActions();
    }
    
    public AbstractListModel getAccountState() {
        return model;
    }
    
    public AccountPanel getGui(){
        return gui;
    }
    
    public void setActions(){
        gui.setActionTopMenu(forEditSelected);
        gui.setActionButtons(forAddButton);
    }
    
    public void deleteAccount(int index){
        model.deleteAccount(index);
    }
    
    public void updateSavingsAccount(){
    	if (gui.isCheckingSelected() == true){
        	String[] textFromFields = gui.getInputFields();
            System.out.println("Checking is selected!");
            int accountNumber = Integer.parseInt(textFromFields[0]);
            String accountOwner = textFromFields[1];
            //get and format date field temp fix to set fixed date
            GregorianCalendar dateOpened = new GregorianCalendar(2015,4,24);
            double accountBalance = Double.parseDouble(textFromFields[3]);
            double monthlyFee = Double.parseDouble(textFromFields[4]);
            //LOL getSource()
            model.updateAccount(1, new CheckingAccount(accountNumber, accountOwner, dateOpened, accountBalance, monthlyFee));
            //gui.refreshAccounts(model);
        } else {
            //model.addSavingsAccount(accountNumber, null, null, balance, minBalance, interestRate);
        }
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
            if (gui.isCheckingSelected() == true){
            	String[] textFromFields = gui.getInputFields();
                System.out.println("Checking is selected!");
                int accountNumber = Integer.parseInt(textFromFields[0]);
                String accountOwner = textFromFields[1];
                //get and format date field temp fix to set fixed date
                GregorianCalendar dateOpened = new GregorianCalendar(2015,4,24);
                double accountBalance = Double.parseDouble(textFromFields[3]);
                double monthlyFee = Double.parseDouble(textFromFields[4]);
                
                model.addAccount(new CheckingAccount(accountNumber, accountOwner, dateOpened, accountBalance, monthlyFee));
                //gui.refreshAccounts(model);
            } else {
                //model.addSavingsAccount(accountNumber, null, null, balance, minBalance, interestRate);
            }
        }
    };
    
    ActionListener forUpdateButton = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateSavingsAccount();
		}
	};
}
