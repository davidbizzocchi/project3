/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.util.GregorianCalendar;
import javax.swing.AbstractListModel;

/**
 *
 * @author Preston Garno
 */

public class AccountController {
    
    private AccountState model;
    private AccountPanel gui;
    
    public AccountController(){
        this.model = new AccountState();
        this.gui = new AccountPanel();
    }
    
    public AbstractListModel getAccountState() {
        return model;
    }
    
    public AccountPanel getGui(){
        return gui;
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
}
