package project3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Preston Garno
 * @version 0.1
 */
public class AccountController {

	/**model instance of AbstractTableModel subclass "AccountState"*/
	private AccountState model;
	/**gui instance of AccountPanel, extends JPanel.*/
	private AccountPanel gui;


	/******************************************
	 * Constructor for class, instantiates new AccountState and 
	 * AccountModel objects.  Also sets ActionListeners for the 
	 * panel class as defined in the method setActions()
	 ******************************************/
	public AccountController() {
		this.model = new AccountState();
		this.gui = new AccountPanel(model);
		setActions();
	}


	/******************************************
	 * Returns current instance of the model class
	 ******************************************/
	public AbstractTableModel getAccountState() {
		return model;
	}


	/******************************************
	 * Returns current instance of the GUI
	 ******************************************/
	public AccountPanel getGui() {
		return gui;
	}

	/******************************************
	 * Attaches the appropriate ActionListeners to the buttons in gui
	 ******************************************/
	public void setActions() {
		gui.setActionTopMenu(forSaveToBinary, forOpenBinary, forSaveToText, forLoadFromText, forEditSelected, forSaveXML, forOpenXML, exit);
		gui.setActionButtons(forAddButton, forUpdateButton, forDeleteButton, forClearButton);
	}


	/******************************************
	 * ActionListener to edit selected accounts
	 ******************************************/
	ActionListener forEditSelected = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(";)");
		}
	};


	/******************************************
	 * ActionListener to Clear the text input fields
	 ******************************************/
	ActionListener forClearButton = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			gui.clearTextFields();
		}
	};


	/******************************************
	 * Adds an account in the model class from user input
	 ******************************************/
	ActionListener forAddButton = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] textFromFields = gui.getInputFields();
			int accountNumber = -1;
			double accountBalance = 0;
			double minBalance = -1;
			double interestRate = -1;

			try {
				accountNumber = Integer.parseInt(textFromFields[0]);
			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(null, "Invalid Account number!\nMust be a positive integer.");
			}

			String accountOwner = textFromFields[1];

			GregorianCalendar dateOpened = gui.getSelectedDate();

			try {
				accountBalance = Double.parseDouble(textFromFields[2]);
			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(null, "Invalid Balance!\nMust be an integer.");
			}

			if (gui.isCheckingSelected() == true) {
				double monthlyFee = -1;
				try {
					monthlyFee = Double.parseDouble(textFromFields[3]);
				} catch (NumberFormatException n) {
					JOptionPane.showMessageDialog(null, "Invalid Monthly Fee!\nMust be a positive integer.");
				}
				try {
					model.addAccount(new CheckingAccount(accountNumber, accountOwner, dateOpened, accountBalance, monthlyFee));
					gui.setSelectedAccount(model.getRowCount() - 1);
				} catch (Exception n) {
					JOptionPane.showMessageDialog(null, "Invalid Input!");
				}
			} else {
				try {
					minBalance = Double.parseDouble(textFromFields[4]);
					interestRate = Double.parseDouble(textFromFields[5]);
				} catch (NumberFormatException f) {
					JOptionPane.showMessageDialog(null, "Invalid Balance!\nMust be an integer.");
				}
				try {
					model.addAccount(new SavingsAccount(accountNumber, accountOwner, dateOpened, accountBalance, minBalance, interestRate));
					gui.setSelectedAccount(model.getRowCount() - 1);
				} catch (Exception n) {
					JOptionPane.showMessageDialog(null, "Invalid Input!");
				}
			}
			gui.adjustColumnWidths();
		}
	};


	/******************************************
	 * Updates selected account from user input in panel class
	 ******************************************/
	ActionListener forUpdateButton = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedAccountIndex = gui.getSelectedAccountIndex();
			String[] textFromFields = gui.getInputFields();
			int accountNumber = -1;
			double accountBalance = 0;
			double minBalance = -1;
			double interestRate = -1;

			try {
				accountNumber = Integer.parseInt(textFromFields[0]);
			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(null, "Invalid Account number!\nMust be a positive integer.");
			}

			String accountOwner = textFromFields[1];

			GregorianCalendar dateOpened = gui.getSelectedDate();

			try {
				accountBalance = Double.parseDouble(textFromFields[2]);
			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(null, "Invalid Balance!\nMust be an integer.");
			}

			if (gui.isCheckingSelected() == true) {
				double monthlyFee = -1;
				try {
					monthlyFee = Double.parseDouble(textFromFields[3]);
				} catch (NumberFormatException n) {
					JOptionPane.showMessageDialog(null, "Invalid Monthly Fee!\nMust be a positive integer.");
				}
				try {
					model.updateAccount(gui.getSelectedAccountIndex(), new CheckingAccount(accountNumber, accountOwner, dateOpened, accountBalance, monthlyFee));
					gui.setSelectedAccount(selectedAccountIndex);
				} catch (Exception n) {
					JOptionPane.showMessageDialog(null, "Invalid Input!");
				}
			} else {
				try {
					interestRate = Double.parseDouble(textFromFields[4]);
					minBalance = Double.parseDouble(textFromFields[5]);
				} catch (NumberFormatException f) {
					JOptionPane.showMessageDialog(null, "Invalid Balance!\nMust be an integer.");
				}
				try {
					model.updateAccount(gui.getSelectedAccountIndex(), new SavingsAccount(accountNumber, accountOwner, dateOpened, accountBalance, interestRate, minBalance));
					gui.setSelectedAccount(selectedAccountIndex);
				} catch (Exception n) {
					JOptionPane.showMessageDialog(null, "Invalid Input!");
				}
			}
			gui.adjustColumnWidths();
		}
	};


	/******************************************
	 * Actionlistener deletes selected account, calls delete() method in model
	 ******************************************/
	ActionListener forDeleteButton = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			//add confirm deletion window
			int indexForDeleted = gui.getSelectedAccountIndex();
			if (indexForDeleted != -1) {
				model.deleteAccount(indexForDeleted);
				gui.setSelectedAccount(model.getRowCount() - 1);
				gui.adjustColumnWidths();
			}
		}
	};


	/******************************************
	 * ActionListener calls saveAsBinary() method in the model class, saves to binary
	 ******************************************/
	ActionListener forSaveToBinary = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			//set windows look and feel for fileChooser, this one looks
			//like crap
			File accountFile = new File(System.getProperty("user.dir"));
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileHidingEnabled(false);
			if (fileChooser.showSaveDialog(gui) == JFileChooser.APPROVE_OPTION) {
				accountFile = fileChooser.getSelectedFile();
			}
			try {
				model.saveAsBinary(accountFile);
			} catch (FileNotFoundException fileNotFound) {
				fileNotFound.printStackTrace();
			} catch (IOException ioExc) {
				ioExc.printStackTrace();
			}
		}
	};


	/******************************************
	 * ActionListener calls loadFromBinary() method in the model class, loads binary file
	 ******************************************/
	ActionListener forOpenBinary = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			File accountFile = new File(System.getProperty("user.dir"));
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(gui) == JFileChooser.APPROVE_OPTION) {
				accountFile = fileChooser.getSelectedFile();
			}
			try {
				model.loadFromBinary(accountFile);
				gui.adjustColumnWidths();
			} catch (FileNotFoundException fileNotFound) {
				fileNotFound.printStackTrace();
			} catch (IOException ioExc) {
				ioExc.printStackTrace();
			} catch (ClassNotFoundException class404){
				class404.printStackTrace();
			}
		}
	};


	/******************************************
	 * ActionListener calls saveAsText() method in the model class, saves to txt file
	 ******************************************/
	ActionListener forSaveToText = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			//set windows look and feel for fileChooser, this one looks
			//like crap
			File accountFile = new File(System.getProperty("user.dir"));
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileHidingEnabled(false);
			if (fileChooser.showSaveDialog(gui) == JFileChooser.APPROVE_OPTION) {
				accountFile = fileChooser.getSelectedFile();
			}
			try {
				model.saveAsText(accountFile);
			} catch (FileNotFoundException fileNotFound) {
				fileNotFound.printStackTrace();
			} catch (IOException ioExc) {
				ioExc.printStackTrace();
			}
		}
	};

	ActionListener forLoadFromText = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			File accountFile = new File(System.getProperty("user.dir"));
			ArrayList<String> rawDataRead = new ArrayList<>();
			ArrayList<Account> newAccounts = new ArrayList<>();
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(gui) == JFileChooser.APPROVE_OPTION) {
				accountFile = fileChooser.getSelectedFile();
			}

			try {
				Scanner sc = new Scanner(accountFile);
				while (sc.hasNextLine()) {
					//array of strings now, just raw data
					rawDataRead.add(sc.nextLine());
				}
				gui.adjustColumnWidths();
			} catch (FileNotFoundException fileNotFound) {
				fileNotFound.printStackTrace();
			}

			for (int i = 0; i < rawDataRead.size(); i++){
				//surround loop with try catch block, so that it can read files that have a couple lines changed
				String[] accountDataAsString = rawDataRead.get(i).split(";;");

				int accountNumber;
				double balance, fee, interest, minBal;
				String owner, desc, dateOpened;

				accountNumber = Integer.parseInt(accountDataAsString[0]);
				owner = accountDataAsString[1];
				dateOpened = accountDataAsString[2];
				balance = Double.parseDouble(accountDataAsString[3]);
				desc = accountDataAsString[4];
				try{
					fee = Double.parseDouble(accountDataAsString[5]);
				} catch (NumberFormatException n){
					fee = -1;
				}

				String withoutPercentage = accountDataAsString[6].replace("%", "");
				interest = Double.parseDouble(withoutPercentage);

				String withoutDashes = accountDataAsString[7].replace("-", "0");
				minBal = Double.parseDouble(withoutDashes);

				DateFormatSymbols dfs = new DateFormatSymbols();
				String[] monthsAsString = dfs.getMonths();

				String[] splitUpDate = dateOpened.split(" ");
				int day,month,year;

				month = 13;
				for (int m = 0; m < 12; m++) {
					if (splitUpDate[0].equals(monthsAsString[m])) {
						month = m;
					}
				}

				String withoutComma = splitUpDate[1].replace(",", "");
				day = Integer.parseInt(withoutComma);
				year = Integer.parseInt(splitUpDate[2]);

				GregorianCalendar date = new GregorianCalendar(year, month, day);

				if(desc.equals("Checking")){
					newAccounts.add(new CheckingAccount(accountNumber, owner, date, balance, fee));
				} else if (desc.equals("Savings")){
					newAccounts.add(new SavingsAccount(accountNumber, owner, date, balance, interest, minBal));
				}
			}
			if(newAccounts.size()>0){
				model.readFromText(newAccounts);
			} else {
				// throw new error, say unreadable
				System.out.println("Unreadable file!");
			}
		}
	};

	ActionListener forSaveXML = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try{

				File accountFile = new File(System.getProperty("user.dir"));
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileHidingEnabled(false);
				if (fileChooser.showSaveDialog(gui) == JFileChooser.APPROVE_OPTION) {
					accountFile = fileChooser.getSelectedFile();
				}

				String requestString = AccountToString(model.getAccounts());

				requestString = requestString.replaceFirst("^([\\W]+)<","<");

				// Parse the given input
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(new InputSource(new StringReader(requestString)));

				// Write the parsed document to an xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result =  new StreamResult(accountFile);
				transformer.transform(source, result);



			} catch(IOException e1){
				e1.printStackTrace();
			} catch (TransformerException e2) {
				e2.printStackTrace();
			} catch (SAXException e3) {
				e3.printStackTrace();
			} catch (ParserConfigurationException e4) {
				e4.printStackTrace();
			}

		}

	};


	ActionListener forOpenXML = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e){
			try{

				File accountFile = new File(System.getProperty("user.dir"));
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(gui) == JFileChooser.APPROVE_OPTION) {
					accountFile = fileChooser.getSelectedFile();
				}
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(accountFile);

				doc.getDocumentElement().normalize();

				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

				NodeList nList = doc.getElementsByTagName("AccountInformation");

				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);

					System.out.println("\nCurrent Element :" + nNode.getNodeName());

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;

						System.out.println("Account Number : " + eElement.getElementsByTagName("Number").item(0).getTextContent());
						System.out.println("Account Owner : " + eElement.getElementsByTagName("Owner").item(0).getTextContent());
						System.out.println("Month : " + eElement.getElementsByTagName("Month").item(0).getTextContent());
						System.out.println("Day : " + eElement.getElementsByTagName("Day").item(0).getTextContent());
						System.out.println("Year : " + eElement.getElementsByTagName("Year").item(0).getTextContent());

					}
				}
			}catch (FileNotFoundException fileNotFound) {
				System.out.println("FILE NOT FOUND");
				fileNotFound.printStackTrace();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	};

	private String AccountToString(ArrayList<Account> s) {

		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < s.size(); i++){
			sb.append(s.get(i).getAccountNumber() + " " +
					s.get(i).getOwner() + " " +
					s.get(i).dateToString() + " " +
					s.get(i).getBalance() + " ");
			if (s.get(i).getAccountTypeDescription().equals("Checking")){
				sb.append("Checking" + " ");
				sb.append(s.get(i).getMonthlyFee() + " ");
			}
			else{
				sb.append("Savings" + " ");
				sb.append(s.get(i).getInterestRate() + " " +
						s.get(i).getMinBalance() + " ");
			}
		}
		String[] tokens = sb.toString().split(" ");

		StringBuffer xmlString = new StringBuffer("<AccountInformation>");

		for (String t : tokens)
			System.out.println(t);

		System.out.println(tokens.length);

		for(int j = 0, index = 0; j < s.size(); j++)
		{
			xmlString.append("<Number>").append(tokens[index]).append("</Number>");
			index++;
			xmlString.append("<Owner>").append(tokens[index]).append("</Owner>");
			index++;
			xmlString.append("<Month>").append(tokens[index]).append("</Month>");
			index++;
			xmlString.append("<Day>").append(tokens[index]).append("</Day>");
			index++;
			xmlString.append("<Year>").append(tokens[index]).append("</Year>");
			index++;
			xmlString.append("<Balance>").append(tokens[index]).append("</Balance>");
			index++;
			if (s.get(j).getAccountTypeDescription().equals("Checking")){
				xmlString.append("<Type>").append(tokens[index]).append("</Type>");
				index++;
				xmlString.append("<MonthlyFee>").append(tokens[index]).append("</MonthlyFee>");
				index++;
			}
			else{
				xmlString.append("<Type>").append(tokens[index]).append("</Type>");
				index++;
				xmlString.append("<InterestRate>").append(tokens[index]).append("</InterestRate>");
				index++;
				xmlString.append("<MinBalance>").append(tokens[index]).append("</MinBalance>");
				index++;
			}
		}
		xmlString.append("</AccountInformation>");

		return xmlString.toString();
	}

	ActionListener exit = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {

			System.exit(-1);

		}

	};

}
