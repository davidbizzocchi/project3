package project3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.text.DateFormatSymbols;
import java.util.GregorianCalendar;
import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

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
    private DateSelection dateSelectionPane;
    ButtonGroup accountTypeSelect;

    public AccountPanel(AccountState accounts) {
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
        bottomPanel.setResizeWeight(1.0);
        bottomPanel.setEnabled(false);
        bottomPanel.add(input);
        bottomPanel.add(buttons, JSplitPane.RIGHT);

        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private class data extends JTable {
        public data() {
            setModel(accounts);
        }
    }
    public int getSelectedAccountIndex(){
    	return accountWindow.getSelectedRow();
    }

    private class menu extends JMenuBar {

        JMenu File, Edit, Sort;
        JMenuItem EditSelected;
        JMenuItem byOwner, byDate, byAccount;

        public menu() {
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

    private class inputFields extends JPanel {

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

        public inputFields() {
        	dateSelectionPane = new DateSelection();
            typeChecking.setSelected(true);
            accountTypeSelect.add(typeChecking);
            accountTypeSelect.add(typeSavings);
            setLayout(new GridLayout(8, 2, 0, 0));
            fields = new JTextField[7];
            for (int i = 0; i < 7; i++) {
                fields[i] = new JTextField(15);
                fields[i].setHorizontalAlignment(JTextField.CENTER);
            }
            fields[5].setEnabled(false);
            fields[6].setEnabled(false);

            JPanel CheckingSelect = new JPanel();
            typeChecking.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (typeChecking.isSelected()){
						fields[4].setEnabled(true);
						fields[5].setEnabled(false);
						fields[6].setEnabled(false);
					}
				}
			});
            CheckingSelect.add(chk);
            CheckingSelect.add(typeChecking);
            add(CheckingSelect);

            JPanel SavingsSelect = new JPanel();
            typeSavings.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (typeSavings.isSelected()){
						fields[4].setEnabled(false);
						fields[5].setEnabled(true);
						fields[6].setEnabled(true);
					}
				}
			});
            SavingsSelect.add(save);
            SavingsSelect.add(typeSavings);
            add(SavingsSelect);
            // set gridlayout to add vertically and fix this 
            // long ass waste of space
            add(number);
            add(fields[0]);

            add(owner);
            add(fields[1]);

            add(date);
            add(dateSelectionPane);
            
            add(bal);
            add(fields[3]);

            add(fee);
            add(fields[4]);

            add(rate);
            add(fields[5]);

            add(min);
            add(fields[6]);
        }

        public boolean getAccountType() {
            if (typeChecking.isSelected()) {
                return true;
            } else if (typeSavings.isSelected()) {
                return false;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    private class inputActions extends JPanel {

        JButton Add = new JButton("Add");
        JButton Delete = new JButton("Delete");
        JButton Update = new JButton("Update");
        JButton Clear = new JButton("Clear");

        public inputActions() {
            setLayout(new GridLayout(6, 1, 1, 4));
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

    public void setActionButtons(ActionListener Add, ActionListener Update, ActionListener Delete, ActionListener Clear) {
        buttons.Add.addActionListener(Add);
        buttons.Update.addActionListener(Update);
        buttons.Delete.addActionListener(Delete);
        buttons.Clear.addActionListener(Clear);
    }

    public String getText(int index) {
        if (index <= 0 && index < 7) {
            return fields[index].getText();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isCheckingSelected() {
        if (input.getAccountType() == true) {
            return true;
        } else {
            return false;
        }
    }

    public String[] getInputFields() {
        String[] textFromFields = new String[7];
        for (int i = 0; i < 7; i++) {
            textFromFields[i] = fields[i].getText();
        }
        return textFromFields;
    }
    
    public void clearTextFields(){
    	for(int i = 0; i < fields.length; i++){
    		fields[i].setText("");
    	}
    }

    private class DateSelection extends JPanel {

        private String[] Months;
        private String[] Days;
        private String[] Year;

        JComboBox monthsBox;
        JComboBox daysBox;
        JComboBox yearsBox;

        public DateSelection() {
            setLayout(new FlowLayout());

            Months = new String[12];
            Days = new String[31];
            Year = new String[25];

            DateFormatSymbols dfs = new DateFormatSymbols();
            String[] monthsAsString = dfs.getMonths();

            for (int i = 0; i < 12; i++) {
                Months[i] = monthsAsString[i];
            }
            for (int z = 0; z < 31; z++) {
                Days[z] = Integer.toString(z + 1);
            }
            for (int y = 0; y < 25; y++) {
                Year[y] = Integer.toString(y + 1991);
            }

            monthsBox = new JComboBox(Months);
            daysBox = new JComboBox(Days);
            yearsBox = new JComboBox(Year);

            monthsBox.addActionListener(removesThe31);

            add(monthsBox);
            add(daysBox);
            add(yearsBox);
        }

        ActionListener removesThe31 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Days ComboBox remove the 31
                String s = (String) monthsBox.getSelectedItem();
                if (s.equals("April") || s.equals("September") || s.equals("November") || s.equals("June")) {
                    if (daysBox.getItemCount() == 31) {
                        daysBox.removeItemAt(30);
                    }
                } else if (daysBox.getItemCount() == 30) {
                    daysBox.addItem("31");
                } 
                //better logic here - February sometimes doesnt set to 28 
                //I wrote this half drunk at 2:30 AM
                if (s.equals("February")) {
                    if (daysBox.getItemCount() == 31) {
                        daysBox.removeItemAt(30);
                    }
                    daysBox.removeItemAt(29);
                    daysBox.removeItemAt(28);
                } else if (s.equals("April") || s.equals("September") || s.equals("November") || s.equals("June")) {
                    daysBox.addItem("29");
                    daysBox.addItem("30");
                } else {
                    daysBox.addItem("29");
                    daysBox.addItem("30");
                    daysBox.addItem("31");
                }

            }
        };
        
        public GregorianCalendar getDateAsGregorian(){
        	DateFormatSymbols dfs = new DateFormatSymbols();
            String[] monthsAsString = dfs.getMonths();
            
            String s = (String) monthsBox.getSelectedItem();
            
            //gets the month in number format
            int month = 13;
            for (int i = 0; i < 12; i++){
            	if(s.equals(monthsAsString[i])){
            		month = i;
            	}
            }
            
            int day;
            day = Integer.valueOf(daysBox.getSelectedItem().toString());
            
            int year;
            year = Integer.valueOf(yearsBox.getSelectedItem().toString());
            
        	GregorianCalendar dateOpened = new GregorianCalendar(year, month, day);
        	//sSystem.out.println(month+"/"+day+"/"+year);
        	return dateOpened;
        }
    }
    
    public GregorianCalendar getSelectedDate(){
    	return dateSelectionPane.getDateAsGregorian();
    }
}
