
package project3;

import javax.swing.JFrame;

/**
 * @author Preston Garno
 */

public class AccountMain {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Accounts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AccountController c = new AccountController();
        frame.add(c.getGui());
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
    
}
