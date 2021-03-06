import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @project SuperVendingMachine v4
 * @file VendingMachine.java
 * @author BrianMcVeigh
 * @date Jul 29, 2014
 * @time 1:41:58 PM
 */
public class VendingMachine extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/* Let the following constants determine what vending machine type will be selected */
	public static final int COKE_MACHINE = 0;
	public static final int PEPSI_MACHINE = 1;
	
	// This is what will write to a file
    private static BufferedWriter writer;
    
    // This variable will help format number to decimal format
    private static NumberFormat myFormatter;
	
	// composition - objects will all be stored in Array Lists.
	private ArrayList<Item> items; // the items stored in vending machine
    private ArrayList<MasonMoney> accounts; // mason money accounts stored as Array List
    private ArrayList<Transaction> transactions; // transactions stored as Array List

	// declare the buttons objects
	private JButton buyButton; // button to buy something from the vending machine
	private JButton viewItemsButton; // the button to view the items in vending machine
	private JButton depositButton; // the button to deposit funds to Mason Money account
	private JButton adminButton; // the button to open the Administrator window
	
	private JFrame vMachineButtonsFrame; // this JFrame will store the vending machine buttons
	
	private int machineType;
	
    private double sales; // total sales amount
	private double theTotal;

	protected String custName;

	

	
	/**
     * Method Name: VendingMachine()
     * Purpose: This will call the initComponents() method, which will build the object
     * Return type: <<constructor>>
     * Parameters: none
     */
	public VendingMachine() {
		initComponents(COKE_MACHINE);
	}
	
	/**
     * Method Name: VendingMachine(vendingMachineType: int)
     * Purpose: This will call the initComponents() method, which will build the object. When constructor is invoked, it collects the vending machine type
     * Return type: <<constructor>>
     * Parameters: vendingMachineType: integer
     */
	public VendingMachine(int vendingMachineType) {
		initComponents(vendingMachineType);
	}
	
	/**
     * Method Name: initComponents(vendingMachineType: int)
     * Purpose: This will build the VendingMachine object
     * Return type: <<constructor>>
     * Parameters: vendingMachineType: integer
     */
	public void initComponents(int vendingMachineType) {
		machineType = vendingMachineType; // set the vending machine type to what was passed in
		
		// Configure the window
		setLayout(new FlowLayout()); // set the layout of the window
		setSize(300,320); // set the size of the window
		setResizable(false); // set the window to not be resizeable (this is particularly useful for a Flow Layout)
		setVisible(true); // set the window to be visible
		setLocationRelativeTo(null); // place the window in the center of the screen
		
		// Initialize the array lists
		items = new ArrayList<Item>(); // initialize the ArrayList for the items that will be sold
		accounts = new ArrayList<MasonMoney>(); // initialize the Array List for the Mason Money accounts
		transactions = new ArrayList<Transaction>(); // initialize the Array List for the transactions
		
		addMasonMoneyAccounts(); // add the mason money accounts
		
		myFormatter = new DecimalFormat("0.00"); // initialize the NumberFormat object we will use for this class (it is needed quite a few times)
		
		// Initialize the buttons
		buyButton = new JButton("Buy Item");
		viewItemsButton = new JButton("View Items For Sale");
		depositButton = new JButton("Deposit to Mason Money");
		adminButton = new JButton("Administrative");
		
		
		// Create a JPanel that will hold the buttons
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(4, 1)); /* set the layout to a grid layout with the number of rows as the number of 
		buttons to be displayed and 1 column */
		buttonsPanel.setPreferredSize(new Dimension(300, 290)); // set the dimensions for the panel
		add(buttonsPanel); // add the panel to the frame
		
		switch (vendingMachineType) {
			case COKE_MACHINE:
				buyButton.setBackground(Color.RED);
				buyButton.setForeground(Color.WHITE);
				viewItemsButton.setBackground(Color.RED);
				viewItemsButton.setForeground(Color.WHITE);
				depositButton.setBackground(Color.RED);
				depositButton.setForeground(Color.WHITE);
				adminButton.setBackground(Color.RED);
				adminButton.setForeground(Color.WHITE);
				setTitle("Coca-Cola");
				addCokeProducts(); // Add the Coke products!
				break;
			case PEPSI_MACHINE:
				buyButton.setBackground(Color.BLUE);
				buyButton.setForeground(Color.WHITE);
				viewItemsButton.setBackground(Color.BLUE);
				viewItemsButton.setForeground(Color.WHITE);
				depositButton.setBackground(Color.BLUE);
				depositButton.setForeground(Color.WHITE);
				adminButton.setBackground(Color.BLUE);
				adminButton.setForeground(Color.WHITE);
				setTitle("Pepsi");
				addPepsiProducts(); // Add the Pepsi Products
				break;
		}
		
		// Add the buttons to the window
		buttonsPanel.add(buyButton);
		buttonsPanel.add(viewItemsButton);
		buttonsPanel.add(depositButton);
		buttonsPanel.add(adminButton);
		
		// Add button listeners
		BuyButtonListener l1 = new BuyButtonListener();
		buyButton.addActionListener(l1);
		
		ViewItemsListener l2 = new ViewItemsListener();
		viewItemsButton.addActionListener(l2);
		
		DepositButtonListener l3 = new DepositButtonListener();
		depositButton.addActionListener(l3);
		
		AdminButtonListener l4 = new AdminButtonListener();
		adminButton.addActionListener(l4);

		
	}
	// --------------------------------------------------------
	// This is the class for the pepsi machine
	private class BuyButtonListener implements ActionListener {

		private JButton[] buttons;
		
		@Override
		public void actionPerformed(ActionEvent e) {
            
            final int BUTTON_HEIGHT = 85;
            buttons = new JButton[items.size()];

            // Set up a items frame
            vMachineButtonsFrame = new JFrame();
    		vMachineButtonsFrame.setLayout(new GridLayout(buttons.length, 1));
    		vMachineButtonsFrame.setSize(300,buttons.length*BUTTON_HEIGHT);
    		vMachineButtonsFrame.setResizable(false);
    		vMachineButtonsFrame.setVisible(true);
    		vMachineButtonsFrame.setLocationRelativeTo(null); // place the window in the center of the screen
    		
            
            for (int i = 0; i < items.size(); i++)
            {
                try {
                	  String buttonText = items.get(i).getItemName() + " $" + myFormatter.format(items.get(i).getItemPrice()) + " | Number in stock: " + items.get(i).getInventory();
                	  buttons[i] = new JButton(buttonText);
                	  
                	  if (items.get(i) instanceof Coke) {
                		  buttons[i].setBackground(Color.RED);
                		  vMachineButtonsFrame.setTitle("Coca-Cola");
                		  
                	  } else {
                		  buttons[i].setBackground(Color.BLUE);
                		  vMachineButtonsFrame.setTitle("Pepsi");
                	  }
                	  buttons[i].setForeground(Color.WHITE);
                	  vMachineButtonsFrame.add(buttons[i]);
                	  
                	// Add an action listener
                	  ActionListener al = new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							System.out.println(e.getActionCommand());
							buyItem(e.getActionCommand());
							refreshButtons(); // refresh the display of the inventory on the buttons
						}
            			  
            		  };
            		  buttons[i].addActionListener(al);
					
    	              // refresh the vending machine buttons this way the number in stock display will be properly updated
    	         	  vMachineButtonsFrame.invalidate();
    	         	  vMachineButtonsFrame.repaint();
    	         	  vMachineButtonsFrame.validate();
                	
                }
                catch (NullPointerException ex) {ex.printStackTrace();}
            }
		}
		
		public void refreshButtons() {
            for (int i = 0; i < items.size(); i++)
            {
                try {
                	  String buttonText = items.get(i).getItemName() + " $" + myFormatter.format(items.get(i).getItemPrice()) + " | Number in stock: " + items.get(i).getInventory();
                	  buttons[i].setText(buttonText);

    	              // refresh the vending machine buttons this way the number in stock display will be properly updated
    	         	  vMachineButtonsFrame.invalidate();
    	         	  vMachineButtonsFrame.repaint();
    	         	  vMachineButtonsFrame.validate();
                	
                }
                catch (NullPointerException ex) {ex.printStackTrace();}
            }
		}
		
	}
	// ---------------------------------------------------------
	// This is the class for the view items button 
	private class ViewItemsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String output = "";
			for (int i = 0; i < items.size(); i++) {
				output += items.get(i).getItemName() + " $" + myFormatter.format(items.get(i).getItemPrice()) + "\n";
			}
			
			JOptionPane.showMessageDialog(null, output);
		}
		
	}
	// ----------------------------------------------------------
	// this is the class for the deposit to mason money button
	private class DepositButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			deposit(); // this will prompt the user for acct number
		}
	}
	
	
	// This is the class for the administrative button
	private class AdminButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Administrator window = new Administrator(VendingMachineApp.getSelectedVendingMachine());
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	}
	// -----------------------------------------------------------
	
	/**
     * Method Name: buyItem()
     * Purpose: Allows the user to buy an item and collects payment
     * Return type: void
     * Parameters: itemName: String
     */
    public void buyItem(String itemName)
    {
    	try {
            double total = 0.0;
//            double amountInserted = 0.0;
           
            int choice = 0;
            for (int i = 0; i < items.size(); i++) {
            	if (itemName.startsWith(items.get(i).getItemName())) {
            		choice = i+1;
            	}
            }
            
            int howMany = Integer.parseInt(JOptionPane.showInputDialog("How many " + items.get(choice-1).getItemName() + " would you like to buy?"));
            

            if (items.get(choice-1).getInventory() <= 0)
            {
                JOptionPane.showMessageDialog(null, "Sorry but we are out of stock!");
             

            } //end if
            else if (items.get(choice-1).getInventory() < howMany)
            {
                JOptionPane.showMessageDialog(null, "We don't have that many in stock!");
               
            } //end else if
            else
            {
            	items.get(choice-1).sell(howMany);
                total = items.get(choice-1).getTotal();
           
           sales += total;
           String custName = "";
           int paymentChoice = 0;
           double amountInserted = 0.0;
           boolean found = false;
           
             do { 
	             if (total > 0) { // if the user still has a balance, the condition is true
	            	 
	                 
	            	 paymentChoice = Integer.parseInt(JOptionPane.showInputDialog("Your total is: $" + myFormatter.format(total) + "\nAmount inserted: $" + myFormatter.format(amountInserted) + "\nEnter Coin:\n1. QUARTER\n2. DIME\n3. NICKEL\n4. $1 BILL\n5. $5 BILL\n6. OTHER AMOUNT\n7. Mason Money\n8. Remove change"));
	                 
	                  switch (paymentChoice)
	                  {
	                      
	                      default:
	                          JOptionPane.showMessageDialog(null, "Invalid choice.");
	                          break;
	                      case 1:
	                          total -= .25;
	                          amountInserted += .25;
	                          System.out.print("Quarter inserted\nBalance: " + myFormatter.format(total) + "\n");
	                          break;
	                      case 2:
	                          total -= .10;
	                          amountInserted += .10;
	                          System.out.print("Dime inserted\nBalance: $" + myFormatter.format(total) + "\n");
	                          break;
	                      case 3:
	                          total -= .05;
	                          amountInserted += .05;
	                          System.out.print("Nickel inserted\nBalance: $" + myFormatter.format(total) + "\n");
	                          break;
	                      case 4:
	                          total -= 1.00;
	                          amountInserted += 1.00;
	                          System.out.print("$1 inserted\nBalance: $" + myFormatter.format(total) + "\n");
	                          break;
	                      case 5:
	                          total -= 5.00;
	                          amountInserted += 5.00;
	                          System.out.print("$5 inserted\nBalance: $" + myFormatter.format(total) + "\n");
	                          break;
	                          
	                   	  case 6:
	                      	  double otherAmt = Double.parseDouble(JOptionPane.showInputDialog("Enter other amount"));
                      		  total -= otherAmt;
                      		  amountInserted += otherAmt;
	                          System.out.print("$" + otherAmt + " inserted\nBalance: $" + myFormatter.format(total) + "\n");
                      		  break;    
	                      case 7:
	                          String cardNumber = JOptionPane.showInputDialog("Enter your card number.");
	                         
	                          for (int i = 0; i < accounts.size(); i++)
	                          {
	                              if(cardNumber.equals(accounts.get(i).getCardNumber()))
	                              {
		                              found = true;
		                              custName = accounts.get(i).getStudentName();
		                              if (accounts.get(i).getBalance() > 0.0 && accounts.get(i).getBalance() <= total && accounts.get(i).getBalance() > 0.0)
		                              {
		                                 total -= accounts.get(i).getBalance();
		                                 accounts.get(i).setBalance(accounts.get(i).getBalance() - theTotal);
		                                 break;
		                              } //end if
		                              else if (accounts.get(i).getBalance() > 0.0 && accounts.get(i).getBalance() >= total)
		                              {
		                                  total -= total;
		                                  accounts.get(i).setBalance(accounts.get(i).getBalance() - theTotal);
		                                  break;
		                              
		                              } //end else if
		                              else if (accounts.get(i).getBalance() <= 0.0)
		                              {
		                            	  JOptionPane.showMessageDialog(null, "Sorry, you don't have any money on your card!");
		                                  accounts.get(i).setBalance(0.0);
		                                  break;
		                              } //end else if		                              
	                               }
	                               else if (found == false && MasonMoney.getNumberOfAccounts() - 1 != i)
	                               {
	                                      continue;
	                               } //end else if
	                               else if (found == false && MasonMoney.getNumberOfAccounts() - 1 == i)
	                               {
	                                    JOptionPane.showMessageDialog(null, "Card number not found");
	                                    break;
	                               } //end else if
	                             
	                             } //end for	                              
								 break;
		                    
                      	   case 8: 
	                          total += amountInserted;
	                          amountInserted = 0.0;
	                          JOptionPane.showMessageDialog(null, "Change removed.");
	                          break;
	                     } // end switch
	                  } // end if
	                  if (total <= 0) { // if the user has paid off the balance for the item they are purchasing, this is true
	                	  
	                	  boolean done = false;
		            	  
	             		  do {
	             			  if (paymentChoice != 7) {custName = JOptionPane.showInputDialog("Enter your full name");}
		            		  System.out.print("Hi " + custName);
			            	  Transaction tran = new Transaction(items.get(choice-1).getItemName(), items.get(choice-1).getItemPrice(), howMany, custName);
			              	  
			            	  // this will add a transaction and check to see if it has been added.
			            	  if (transactions.add(tran)) {
			            		  System.out.println("Transaction added.");
			            	  } else {
			            		  System.out.println("Error writing transaction!");
			            	  }
			              	  
			              	  System.out.print(tran.toString()); // print out the transaction to console
			              	  JOptionPane.showMessageDialog(null, "Transaction Complete \r\n" + transactions.get(transactions.size()-1).toString());
		            		  
			              	  int c = JOptionPane.showConfirmDialog(null, "Would you like your receipt, " + custName + "?", "Select an Option", JOptionPane.YES_NO_OPTION);
			              	  if (c == JOptionPane.YES_OPTION) {
				              	  // Write to a file - the false we are passing in writeFile is saying that this is not an error message
				            	  writeFile(tran.toString(), false); 
			              	  }

			            	  
			              	  done = true; // since customer included a space in the name they provided, set done to true so we can exit out of do-while loop
			            	  
		            	  } while (!done);
						  break;
		               } //end else
		           } while (total > 0);
           
         	  
	          } //end else
    	}
    	catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Error! You must enter a valid choice as an integer.");
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	} // end try-catch
    } //end buyItem()
    // -------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Method Name: addSomeCokeItems()
     * Purpose: adds some Item objects to the vending machine
     * Return type: void
     * Parameters: none
     */
    public void addCokeProducts()
    {
    	items.add(new Coke("Coca-Cola", 1.50, 10));
        items.add(new Coke("Sprite", 1.50, 10));
        items.add(new Coke("Dr. Pepper", 1.50, 10));
        items.add(new Coke("Fanta", 1.50, 10));
        items.add(new Coke("Diet Coke", 1.50, 10));
        items.add(new Coke("Dasani", 1.00, 10));
        items.add(new Coke("Doritos", 1.00, 10));
        items.add(new Coke("Snyders Pretzels", 1.00, 10)); 
    } //end addCokeProducts()
    // ------------------------------------------------------
    /**
     * Method Name: addSomeItems()
     * Purpose: adds some Item objects to the vending machine
     * Return type: void
     * Parameters: none
     */
    public void addPepsiProducts()
    {
    	items.add(new Pepsi("Pepsi", 1.50, 10));
        items.add(new Pepsi("Diet Pepsi", 1.50, 10));
        items.add(new Pepsi("Dr. Pepper", 1.50, 10));
        items.add(new Pepsi("Mug Root Beer", 1.50, 10));
        items.add(new Pepsi("Crush Orange Soda", 1.50, 10));
        items.add(new Pepsi("Aquafina", 1.00, 10));
    } //end addPepsiProducts()
    //---------------------------------------------------------
    /**
     * Method Name: restockInventory()
     * Purpose: restocks the inventory for items that have less than or equal to 1 Item
     * Return type: void
     * Parameters: none
     */
    public void restockInventory()
    {
        String output = "";
        for (int i = 0; i < Item.getNumberOfItems(); i ++)
        {
          if (items.get(i).getInventory() <= 1)
          {
               output += (i + 1) + ". " + items.get(i).getItemName() + " " + items.get(i).getItemPrice() + " Inventory: " + items.get(i).getInventory() + "\n";
          }
        }
        
        if (!output.equals("")) // if we have something in the output, this is true
        {
              int choice = Integer.parseInt(JOptionPane.showInputDialog("What would you like to restock?  \n\n" + output));
              items.get(choice-1).setInventory(10);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Good news! No need to restock!");
        }
    } //end restockInventory()
    // -----------------------------------------------------------------------------
    
    /**
     * Method Name: addItemToInventory()
     * Purpose: creates an item to the Inventory
     * Return type: void
     * Parameters: none
     */
    public void addItemToInventory()
    {
        String itemName = JOptionPane.showInputDialog("Enter the item name.");
        double itemPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter the item price."));
        int inventory = Integer.parseInt(JOptionPane.showInputDialog("Enter the item inventory."));

        for (int i = 0; i < items.size(); i++)
        {
            if (machineType == VendingMachine.COKE_MACHINE)
            {
                Item item = new Coke(itemName, itemPrice, inventory);
                if (items.add(item)) {
                	JOptionPane.showMessageDialog(null, "Item created.");
                } else {
                	JOptionPane.showMessageDialog(null, "Could not create item");
                }
                break;
            }
            else if (machineType == VendingMachine.PEPSI_MACHINE)
            {
            	Item item = new Pepsi(itemName, itemPrice, inventory);
                if (items.add(item)) {
                	JOptionPane.showMessageDialog(null, "Item created.");
                } else {
                	JOptionPane.showMessageDialog(null, "Could not create item");
                }
                break;
            }
        } //end for
    } //end addItemToInventory()
    //-----------------------------------------------------------------------------
    
    /**
     * Method Name: addMasonMoneyAccounts()
     * Purpose: adds some mason money accounts
     * Return type: void
     * Parameters: none
     */
    public void addMasonMoneyAccounts()
    {
        accounts.add(new MasonMoney("123456", "Brian McVeigh", "G00571693", 10.00));
        accounts.add(new MasonMoney("572126", "Colby McVeigh", "G00571694", 20.00));
        accounts.add(new MasonMoney("722418", "John Liebermann", "G00771593", 1.00));
        accounts.add(new MasonMoney("234567", "Luke Lam", "G005577123", 50.27));
        accounts.add(new MasonMoney("112233", "Gunston Patriot", "G006633335", 3.50));
    }
    // -----------------------------------------------------------------------------------
    
    /**
     * Method Name: displayInventory()
     * Purpose: displays inventory for each item
     * Return value: void
     * Parameters: none
     */
    public void viewInventory()
    {
        String output = "\n\nINVENTORY LIST\n\n";
        for (int i = 0; i < items.size(); i ++)
        {   
            output += (i + 1) + ". " + items.get(i).getItemName() + " $" + myFormatter.format(items.get(i).getItemPrice()) + " Inventory: " + items.get(i).getInventory() + "\n";
        }
        
        int choice = JOptionPane.showConfirmDialog(null, output + "\nWould you like this printed out?", "Select an Option", JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
        	writeFile(output,false);
        } else {
        	return;
        }
    } //end viewInventory()
    //---------------------------------------------------------------------------------------------------------------------------------------
    
    public void printMasonMoneyAccounts() {
    	String output = "";
    	for (int i=0;i<accounts.size();i++) {
    		try {
    			output += "Card Number: " + accounts.get(i).getCardNumber() + "\r\n";
    			output += "Student Name: " + accounts.get(i).getStudentName() + "\r\n";
    			output += "Card Balance: $" + myFormatter.format(accounts.get(i).getBalance()) + "\r\n";
    			output += "------------------------\n";
    		} catch (NullPointerException e) {
    			e.printStackTrace();
    			break;
    		}
    	}
    	//------------------------------------------------------------------------------------------------------------------------------------
    	// validate that there is at least 1 account
    	if (accounts.size() > 0) {
    		int choice = JOptionPane.showConfirmDialog(null, output + "\r\n\r\nWould you like this printed out?", "Select an option", JOptionPane.YES_NO_OPTION);
    		
    		if (choice == JOptionPane.YES_OPTION) {
    			writeFile(output, false);
    		}
    	} else {
    		output = "There are no Mason Money accounts created.";
    		JOptionPane.showMessageDialog(null, output);
    	}
    }
    //----------------------------------------------------------------------------------------------------------------------------------------
    
    /**
     * Method name: writeFile(String text)
     * Parameters: text: String
     * Return type: None
     * Description: this method writes a file. It writes the text based on the passed in text.
     */
    public void writeFile(String text, boolean error) {
    	if (!error) {
		     
	    	 JFileChooser chooser = new JFileChooser();
			  chooser.showSaveDialog(null);
			  File file = chooser.getSelectedFile();
			  
			  try {
			      writer = new BufferedWriter(new FileWriter(file));
			      writer.write(text);
			      JOptionPane.showMessageDialog(null, "Your receipt has been printed as " + file.getName(),
			  "Thank You!",
			      JOptionPane.INFORMATION_MESSAGE);
			      writer.close();
			  }       
			  catch (IOException e) {
			      JOptionPane.showMessageDialog(null, "Error writing file!");
			  }
			  
    	} else {
    		 try {
    			  File f = new File("C:/Users/brianmcveigh/Desktop/SuperVendingMachine-CrashReport.txt");
			      writer = new BufferedWriter(new FileWriter(f));
			      writer.write(text);
			      JOptionPane.showMessageDialog(null, "Your document has been printed as " + f.getName(),
			  "Thank You!",
			      JOptionPane.INFORMATION_MESSAGE);
			      writer.close();
			  }       
			  catch (IOException e) {
			      JOptionPane.showMessageDialog(null, "Error writing file!");
			  }
    	}
    }
    
    public String printItemsSold(String text) {    	
    	try {
	    	for (int i = 0; i < transactions.size(); i++) {
	    		text += "" + transactions.get(i).toString() + "\r\n";
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
	
	/**
     * Method Name: printSalesInformation()
     * Purpose: prints the total number of sales in dollars and transactions
     * Return type: void
     * Parameters: none
     */
    public void printSalesInformation()
    {
        String output = "";
    	if (Transaction.getNumberOfTransactions() > 0) {
    		int includeTrans = JOptionPane.showConfirmDialog(null, "Include transactions?", "Select an option", JOptionPane.YES_NO_OPTION);
	    	output = "\r\n\r\nSALES INFORMATION\r\n----------------------------\r\n";
	        output += "Total Sales: $" + myFormatter.format(sales) + "\r\nNumber of transactions: " + Transaction.getNumberOfTransactions() + "\r\n\r\n";
	        
	        if (includeTrans == JOptionPane.YES_OPTION) {
	        	writeFile(printItemsSold(output),false); // print out the items
	        }
	    } else {
	    	output = "No transactions have been performed.";
	    }
        JOptionPane.showMessageDialog(null, output);
    } //end printSalesInformation()
   //----------------------------------------------------------------------------------------------------------------------------------------
    
    /**
     * Method name: deposit()
     * Purpose: to deposit money into a user's Mason Money account.
     * Return type: void
     * Parameters: none
     */
    public void deposit() {
    	boolean found = false;
    	String id = JOptionPane.showInputDialog("Enter your Student ID");
    	for (int i=0; i < accounts.size(); i++) {
    		if (accounts.get(i).getCardNumber().equals(id)) {
    			found = true;
    			System.out.println("Account found");
    			double amt = Double.parseDouble(JOptionPane.showInputDialog("How much would you like to deposit, " + accounts.get(i).getStudentName() + "?"));
    			accounts.get(i).deposit(amt);
    			JOptionPane.showMessageDialog(null, "Deposit complete. Have a nice day!");
    		}
    	}
    	
    	if (!found) {
    		JOptionPane.showMessageDialog(null, "Student ID not found!");
    	}
    }
    //----------------------------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) 
    {
        VendingMachineApp app = new VendingMachineApp();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } //end
}
