package project3;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * @author Preston Garno
 */
public class AccountMain {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Accounts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AccountController c = new AccountController();
        frame.add(c.getGui());
        //frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

}