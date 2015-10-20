/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

/**
 *
 * @author Preston Garno
 */
public class AccountController {
    
    private AccountState model;
    
    public AccountController(){
        this.model = new AccountState();
    }
    
    //controller is anything that takes input from the user that is used 
    //to cause the model to transition state
    //joins together the model and the view basically
}
