/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 *
 * @author Preston Garno
 */
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    private int number;
    private String owner;
    private GregorianCalendar dateOpened;
    private double balance;

}
