
package project3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

/**
 * @author Preston Garno
 */

public class AccountPanel extends JPanel {
    
    private data accountWindow;
    private menu topMenu;
    private inputFields input;
    private inputActions buttons;
    private AccountState accounts;
    private JTextField[] fields;
    ButtonGroup accountTypeSelect;
    
    public AccountPanel(AccountState accounts){
        this.accounts = accounts;
        accountTypeSelect = new ButtonGroup();
        accountWindow = new data();
        topMenu = new menu();
        input = new inputFields();
        buttons = new inputActions();
        
        setLayout(new BorderLayout());
        add(topMenu, BorderLayout.NORTH);
        JScrollPane j = new JScrollPane(accountWindow);
        j.setPreferredSize(new Dimension(450, 300));
        add(j);
        
        JSplitPane bottomPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        bottomPanel.setEnabled(false);
        bottomPanel.add(input);
        bottomPanel.add(buttons, JSplitPane.RIGHT);
        
        add(bottomPanel, BorderLayout.PAGE_END);
    }
    
    private class data extends JList {
        public data(){
            setModel(accounts);
            System.out.println(accounts.getAccounts().size());
        }
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
        JLabel chk = new JLabel("Checking");
        JLabel save = new JLabel("Savings");
        JRadioButton typeChecking = new JRadioButton();
        JRadioButton typeSavings = new JRadioButton();
        JLabel number = new JLabel("  Account Number:");
        JLabel owner = new JLabel("  Account Owner:");
        JLabel date = new JLabel("  Date Opened:");
        JLabel bal = new JLabel("  Account Balance:");
        JLabel fee = new JLabel("  Monthly Fee:");
        JLabel rate = new JLabel("  Interest Rate:");
        JLabel min = new JLabel("  Minimum Balance:");
        public inputFields(){
            typeChecking.setSelected(true);
            accountTypeSelect.add(typeChecking);
            accountTypeSelect.add(typeSavings);
            setLayout(new GridLayout(8,2,0,0));
            fields = new JTextField[7];
            for (int i = 0; i < 7; i++){
                fields[i] = new JTextField(15);
                fields[i].setHorizontalAlignment(JTextField.CENTER);
            }
            
            JPanel CheckingSelect = new JPanel();
            CheckingSelect.add(chk);
            CheckingSelect.add(typeChecking);
            add(CheckingSelect);
            
            JPanel SavingsSelect = new JPanel();
            SavingsSelect.add(save);
            SavingsSelect.add(typeSavings);
            add(SavingsSelect);
            
            add(number);
            add(fields[0]);
            
            add(owner);
            add(fields[1]);
            
            add(date);
            add(fields[2]);
            
            add(bal);
            add(fields[3]);
            
            add(fee);
            add(fields[4]);
            
            add(rate);
            add(fields[5]);
            
            add(min);
            add(fields[6]);
        }
        public boolean getAccountType(){
            if (typeChecking.isSelected()){
                return true;
            } else if (typeSavings.isSelected()){
                return false;
            } else{
                throw new IllegalArgumentException();
            }
        }
    }
    
    private class inputActions extends JPanel {
        JButton Add = new JButton("Add");
        JButton Delete = new JButton("Delete");
        JButton Update = new JButton("Update");
        JButton Clear = new JButton("Clear");
        public inputActions(){
            setLayout(new GridLayout(6,1,1,4));
            add(new JLabel(""));
            add(Add);
            add(Delete);
            add(Update);
            add(Clear);
        }
    }

    public void setActionWindow(data window) {
        
    }

    public void setActionTopMenu(ActionListener forEditSelected) {
        topMenu.EditSelected.addActionListener(forEditSelected);
    }

    public void setActionInput(inputFields input) {
        
    }

    public void setActionButtons(ActionListener Add) {
        buttons.Add.addActionListener(Add);
    }
    
    public String getText(int index){
        if (index <= 0 && index < 7){
            return fields[index].getText();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    
    public boolean isCheckingSelected(){
        if (input.getAccountType() == true){
            return true;
        } else {
            return false;
        }
    }
    
    public JTextField[] getInputFields(){
        return fields;
        //last move - from the fields, take text, validate input, pass to model
        // creating a new account object
    }
}