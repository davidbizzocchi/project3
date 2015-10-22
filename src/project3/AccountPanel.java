
package project3;

import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author Preston Garno
 */

public class AccountPanel extends JPanel {
    
    data window;
    menu topMenu;
    inputFields input;
    inputActions buttons;
    
    public AccountPanel(){
        window = new data();
        topMenu = new menu();
        input = new inputFields();
        buttons = new inputActions();
        
        setLayout(new BorderLayout());
        add(topMenu, BorderLayout.NORTH);
    }
    
    private class data extends JScrollPane {
        
    }
    
    private class menu extends JMenuBar{
        JMenu File, Edit, Sort;
        JMenuItem EditSelected;
        JMenuItem byOwner, byDate, byAccount;
        public menu(){
            File = new JMenu("File");
            Edit = new JMenu("Edit");
            Sort = new JMenu("Sort");
            
            EditSelected = new JMenuItem("EditSelected");
            byAccount = new JMenuItem("Sort By Account");
            byDate = new JMenuItem("Sort By Date");
            byOwner = new JMenuItem("Sort By Owner");
            
            Edit.add(EditSelected);
            Sort.add(byAccount);
            Sort.add(byDate);
            Sort.add(byOwner);
            
            add(File);
            add(Edit);
            add(Sort);
        }
    }
    
    private class inputFields extends JPanel{
        
    }
    
    private class inputActions extends JPanel {
        
    }
}
