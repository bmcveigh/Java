import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author BrianMcVeigh
 *
 */
public class Administrator  extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel unLabel; // username label
	private JLabel passLabel; // password label
	private JTextField text;
	private JPasswordField pass;
	private JButton loginButton;
	
	private JFrame adminFrame;
	private JFrame credsFrame;
	
	private int triesLeft;
	
	// composition
	private ArrayList<Item> items; // the items stored in vending machine
    private ArrayList<MasonMoney> accounts; // mason money accounts stored as Array List
    private ArrayList<Transaction> transactions; // transactions stored as Array List
    private static int numberOfTransactions; // total number of transactions
    private double sales; // total sales amount
    private static VendingMachine selectedVendingMachine;
		
	public Administrator() {
		super();
		initComponents();
	}
	
	public Administrator(VendingMachine vm) {
		super();
		triesLeft = 3;
		selectedVendingMachine = vm;
		initComponents();
	}
	
	public void initComponents() {
		credsFrame = new JFrame("Enter credentials");
		credsFrame.setLayout(new FlowLayout());
		credsFrame.setSize(200, 200);
		credsFrame.setVisible(true);
		credsFrame.setResizable(false);
		credsFrame.setLocationRelativeTo(null); // place the window in the center of the screen
		credsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		text = new JTextField(15);
		pass = new JPasswordField(15);
		loginButton = new JButton("Login");
		
		unLabel = new JLabel("Username");
		unLabel.setFont(new Font("Arial",Font.PLAIN,16));
		
		passLabel = new JLabel("Password");
		passLabel.setFont(new Font("Arial",Font.PLAIN,16));
		
		credsFrame.add(unLabel);
		credsFrame.add(text);
		credsFrame.add(passLabel);
		credsFrame.add(pass);
		credsFrame.add(loginButton);
		LoginButtonListener l = new LoginButtonListener();
		loginButton.addActionListener(l);
		
		// refresh (because sometimes does not appear at first on PC)
		credsFrame.invalidate();
		credsFrame.repaint();
		credsFrame.validate();

	}
	
	private class LoginButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			char[] pw = pass.getPassword(); // get the password
			if (text.getText().equalsIgnoreCase("admin") && isPasswordCorrect(pw) && triesLeft > 0) {
				triesLeft = 3;
				credsFrame.dispose();
				run(); // run the admin window
			}
			else if ((!text.getText().equalsIgnoreCase("admin") || !isPasswordCorrect(pw)) && triesLeft >
					0) {
				JOptionPane.showMessageDialog(null, "Incorrect Username or Password.");
				--triesLeft; // decrement the number of tries left by 1
			}
			else {
				JOptionPane.showMessageDialog(null, "ACCESS DENIED");
				credsFrame.dispose();
			}
		}
		
		private boolean isPasswordCorrect(char[] input) {
		    boolean isCorrect = true;
		    char[] correctPassword = { 'a', 'd', 'm', 'i', 'n', 'i', 's', 't', 'r', 'a', 't', 'o', 'r' };

		    if (input.length != correctPassword.length) {
		        isCorrect = false;
		    } else {
		        isCorrect = Arrays.equals (input, correctPassword);
		    }

		    //Zero out the password.
		    Arrays.fill(correctPassword,'0');

		    return isCorrect;
		}
		
	}
	
	// Button 1: Restock Inventory
	private class Button1Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			VendingMachineApp.getSelectedVendingMachine().restockInventory();
		}
		
	}
	
	// Button 2: add an item to inventory
	private class Button2Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			VendingMachineApp.getSelectedVendingMachine().addItemToInventory();
		}
		
	}
	
	// Button 3: view inventory
	private class Button3Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			VendingMachineApp.getSelectedVendingMachine().viewInventory();	
		}
		
	}
	
	// Button 4: display Mason Money accounts
	private class Button4Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			VendingMachineApp.getSelectedVendingMachine().printMasonMoneyAccounts();
		}
		
	}
	
	// Button 5: display sales metrics
	private class Button5Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			VendingMachineApp.getSelectedVendingMachine().printSalesInformation();
		}
		
	}
	
	private void run() {
		adminFrame = new JFrame("Administrative Options");
		
		// Configure the window
		adminFrame.setLayout(new GridLayout(5,1));
		adminFrame.setSize(300,380);
		adminFrame.setResizable(false);
		adminFrame.setVisible(true);
		adminFrame.setLocationRelativeTo(null); // place the window in the center of the screen
		adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Create Buttons
		JButton button1 = new JButton("Restock Inventory");
		JButton button2 = new JButton("Add item to inventory");
		JButton button3 = new JButton("View Inventory");
		JButton button4 = new JButton("Print Mason Money Accounts");
		JButton button5 = new JButton("Display Sales Metrics");
		
		// Add buttons to frame
		adminFrame.add(button1);
		adminFrame.add(button2);
		adminFrame.add(button3);
		adminFrame.add(button4);
		adminFrame.add(button5);
		
		// Add action listeners for buttons
		Button1Listener l1 = new Button1Listener();
		button1.addActionListener(l1);
		//-----------------------------------------
		Button2Listener l2 = new Button2Listener();
		button2.addActionListener(l2);
		//-----------------------------------------
		Button3Listener l3 = new Button3Listener();
		button3.addActionListener(l3);
		//-----------------------------------------
		Button4Listener l4 = new Button4Listener();
		button4.addActionListener(l4);
		//-----------------------------------------
		Button5Listener l5 = new Button5Listener();
		button5.addActionListener(l5);
		//-----------------------------------------
		
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public ArrayList<MasonMoney> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<MasonMoney> accounts) {
		this.accounts = accounts;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	public static int getNumberOfTransactions() {
		return numberOfTransactions;
	}

	public static void setNumberOfTransactions(int numberOfTransactions) {
		Administrator.numberOfTransactions = numberOfTransactions;
	}

	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}
	
    public String printItemsSold(String text) {    	
    	try {
	    	for (int i = 0; i < transactions.size(); i++) {
	    		text += transactions.get(i).toString() + "\r\n";
	    	}
	    	return text;
    	}
    	catch (NullPointerException e) {
    		e.printStackTrace();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return "";
    }

	public static VendingMachine getSelectedVendingMachine() {
		return selectedVendingMachine;
	}

	public static void setSelectedVendingMachine(
			VendingMachine selectedVendingMachine) {
		Administrator.selectedVendingMachine = selectedVendingMachine;
	}
    
    
	
}
