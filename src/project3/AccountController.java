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
        model.addCheckingAccount(1234, "John", new GregorianCalendar(2014, 9, 23), 555.55, 33.33);
        model.addCheckingAccount(4321, "Dave", new GregorianCalendar(2015, 10, 23), 555.55, 33.33);
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
    
    public void addCheckingAccount(int accountNumber, String owner, GregorianCalendar dateOpened, double balance, double MonthlyFee){
        model.addCheckingAccount(accountNumber, owner, dateOpened, balance, MonthlyFee);
    }
    
    public void addSavingsAccount(int accountNumber, String owner, GregorianCalendar dateOpened, double balance, double minBalance, double interestRate){
        model.addSavingsAccount(accountNumber, owner, dateOpened, balance, minBalance, interestRate);
    }
    
    public void deleteAccount(int index){
        model.deleteAccount(index);
    }
    
    public void updateCheckingAccount(int index, int accountNumber, String owner, GregorianCalendar dateOpened, double balance, double MonthlyFee){
        model.updateCheckingAccount(index, accountNumber, owner, dateOpened, balance, MonthlyFee);
    }
    
    public void updateSavingsAccount(int index, int accountNumber, String owner, GregorianCalendar dateOpened, double balance, double minBalance, double interestRate){
        model.updateSavingsAccount(index, accountNumber, owner, dateOpened, balance, minBalance, interestRate);
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
                System.out.println("Checking is selected!");
                //model.addCheckingAccount(accountNumber, null, null, balance, MonthlyFee);
            } else {
                //model.addSavingsAccount(accountNumber, null, null, balance, minBalance, interestRate);
            }
        }
    };
}
