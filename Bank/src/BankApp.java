import java.util.ArrayList; // import our ArrayList functionality

import javax.swing.JOptionPane; // Import JOptionPane so we can use the dialog boxes
import javax.swing.JTextField;

// BrianMcVeigh.com
// A simple bank application

/**
 * 
 * @project Bank
 * @file BankApp.java
 * @author BrianMcVeigh
 * @date Jul 30, 2014
 * @time 12:51:15 PM
 */
public class BankApp {

	// Create our array list for the accounts
	private static ArrayList<Account> accounts;

	// Create our array list for the accountholders
	private static ArrayList<Accountholder> accountholders;

	// This string will be used to store the entered account number 
	private static String enteredAccountNumber;

	// Start of main method
	public static void main(String[] args) {
		// Initialize the accounts array list
		accounts = new ArrayList<Account>();

		// Initialize the accountholders array list
		accountholders = new ArrayList<Accountholder>();

		// Create an accountholder
		/* Here's our parameters: String username, String firstName, String lastName,
		String street, String city, String state, String zip,
		Account[] accountsOwned */
		Accountholder sjones = new Accountholder("sjones", "Sam", "Jones", "123 Anytown Street", "Madison", "Wisconsin", "12345");
		accountholders.add(sjones);

		// Create 3 accounts for testing purposes and add them to the accounts array list
		// Account 1
		Account acct1 = new Account(500.00,sjones);
		accounts.add(acct1); // add to array list
		sjones.addAccount(acct1);

		// Account 2
		Account acct2 = new Account(250.00,sjones);
		accounts.add(acct2); // add to accounts array list
		sjones.addAccount(acct2);

		// Account 3
		Account acct3 = new Account(20000.00,sjones);
		accounts.add(acct3); // add to accounts array list
		sjones.addAccount(acct3);


		// Create admin account, set the account number to admin, and add to the array list
		Account admin = new Account(1000.00,sjones);
		admin.setAccountNumber("admin");
		accounts.add(admin);




		boolean done = false;
		// Prompt for account number or create a new account
		do {
			int choice = Integer.parseInt(JOptionPane.showInputDialog("Welcome to the Bank\nMember FDIC. Equal Opportunity Employer\n-------------------\nSelect an Option:\n 1. " +
					"Enter account number\n 2. Create account\n 3. Quit"));

			switch (choice) {
			// Case 1 searches and opens up the account
			case 1:
				searchAccount();
				/* Once the searchAccount method is done, we will ask if
					the user wants to go back to the first main menu */
				int selection = JOptionPane.showConfirmDialog(null, "Do you want to return to the main lobby?", "Select an Option", JOptionPane.YES_NO_OPTION);

				// If the user says "No", break out of the loop to quit the program
				if (selection != JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Thank you for using the banking app!");
					done = true;
					break;
				}
				break;

				// Case 2 creates an account if the balance is at least $100.00
			case 2:
				createAccount();
				break;

				// Case 3, we quit the app
			case 3:
				done = true;
				break;
			default:
				break;

			}
		} while (!done);

	}

	public static void searchAccount() {
		boolean found = false;
		// Prompt for account number
		enteredAccountNumber = JOptionPane.showInputDialog("Enter your account number:");

		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNumber().equals(enteredAccountNumber)) {


				found = true;

				if (!enteredAccountNumber.equals("admin")) {

					boolean done = false;
					do {
						String[] choices = {"Deposit","Withdraw","Check Balance","View Transactions","View Profile","Quit"};
						String choicesStr = "ACCOUNT #: " + accounts.get(i).getAccountNumber() + "\n"; // will display the choices like an ordered list

						for (int j = 0; j < choices.length; j++) {
							choicesStr += (j+1) + ". " + choices[j] + "\n";
						}

						int choice = Integer.parseInt(JOptionPane.showInputDialog(null, choicesStr));

						switch (choice) {
						case 1:
							double enteredDepositAmt = Double.parseDouble(JOptionPane.showInputDialog("Enter deposit amount:"));
							accounts.get(i).deposit(enteredDepositAmt);
							JOptionPane.showMessageDialog(null, "Deposit Submitted Successfully" + " in the amount of $" + enteredDepositAmt);
							break;
						case 2:
							double enteredWithdrawalAmt = Double.parseDouble(JOptionPane.showInputDialog("Enter withdrawal amount:"));
							
							if (accounts.get(i).withdraw(enteredWithdrawalAmt))
								JOptionPane.showMessageDialog(null, "Withdrawal in the amount of $" + enteredWithdrawalAmt + " is complete.");
							else
								JOptionPane.showMessageDialog(null, "Insufficient funds.");
							
							break;
						case 3:
							JOptionPane.showMessageDialog(null, "Available Balance: $" + accounts.get(i).getBalance());
							break;
						case 4:
							String transactions = "";
							for (int j = 0; j < accounts.get(i).getTransactions().size(); j++) {
								transactions += "" + accounts.get(i).getTransactions().get(j).getDescription() + "\n" +
										"$" + accounts.get(i).getTransactions().get(j).getAmount() + "\n" + "--------------\n";
							}
							JOptionPane.showMessageDialog(null, "Transaction History: \n" + transactions);
							break;
						case 5:
							displayProfile(i);
							break;
						case 6:
							done = true;
							break;
						}

					} while (!done);
				}
				else 
				{
					// if the account is equal to <admin>, then we open the administrator menu
					found = true; // Say that we found the admin account
					boolean done = false; // this will be for our do-while loop. When the value is true, the loop ends.
					do {
						int choice = Integer.parseInt(JOptionPane.showInputDialog(null, "ADMINISTRATOR MENU" + "\n1. View All Accounts\n2. View Total Bank Funding\n3. Deposit" +
								"\n4. Withdraw\n5. Check Balance\n6. View Transaction History\n7. Create an Account\n8. View All Accountholders\n9. Search for an Accountholder\n10. Quit"));
						switch (choice) {
						case 1:
							String accountsString = "Account #   Balance\n";
							for (int j = 0; j < accounts.size(); j++) {
								accountsString += accounts.get(j).getAccountNumber() + "- $" + accounts.get(j).getBalance() + "\n";
							}
							JOptionPane.showMessageDialog(null, accountsString); // Display the accounts in a dialog box
							break;
						case 2:
							double reserves = 0.0;

							for (int j = 0; j < accounts.size(); j++) {
								reserves += accounts.get(j).getBalance();
							}
							String fundingString = "Total Reserves: $" + reserves;
							JOptionPane.showMessageDialog(null, fundingString); // Display the accounts in a dialog box
							break;
						case 3:
							double amount1 = Double.parseDouble(JOptionPane.showInputDialog("Enter deposit amount:"));
							accounts.get(i).deposit(amount1);
							break;
						case 4:
							double amount2 = Double.parseDouble(JOptionPane.showInputDialog("Enter withdrawal amount:"));
							accounts.get(i).withdraw(amount2);
							break;
						case 5:
							JOptionPane.showMessageDialog(null, "Available Balance: $" + accounts.get(i).getBalance());
							break;
						case 6:
							String transactions = "";
							for (int j = 0; j < accounts.get(i).getTransactions().size(); j++) {
								transactions += "" + accounts.get(i).getTransactions().get(j).getDescription() + "\n" +
										"$" + accounts.get(i).getTransactions().get(j).getAmount() + "\n" + "--------------\n";
							}
							JOptionPane.showMessageDialog(null, "Transaction History: \n" + transactions);
							break;
						case 7:
							createAccount();
							break;
						case 8:
							viewAccountholders();
							break;
						case 9: 
							searchForAccountholder();
							break;
						case 10:
							done = true;
							break;
						}
					} while (!done);


				}
			}

		}

		if (!found) {
			JOptionPane.showMessageDialog(null, "Account not found.");
		}
	}

	private static void displayProfile(int index) {
		String profileInfo = "Username: " + accounts.get(index).getAccountholder().getUsername() + "\n"
				+"First Name: " + accounts.get(index).getAccountholder().getFirstName() + "\n"
				+ "Last Name: " + accounts.get(index).getAccountholder().getLastName() + "\n"
				+ "Street: " + accounts.get(index).getAccountholder().getStreet() + "\n"
				+ accounts.get(index).getAccountholder().getCity() + ", " 
				+ accounts.get(index).getAccountholder().getState() + " "
				+ accounts.get(index).getAccountholder().getZip() + "\n";
		JOptionPane.showMessageDialog(null, "Profile: \n" + profileInfo);
		
	}

	public static void createAccount() {
		
		int selection = JOptionPane.showConfirmDialog(null, "Are you a new to The Bank?","Select an Option",JOptionPane.YES_NO_OPTION);
		
		// Create and initialize the text fields for the user registration dialog box
		JTextField username = new JTextField();
		JTextField firstName = new JTextField();
		JTextField lastName = new JTextField();
		JTextField street = new JTextField();
		JTextField city = new JTextField();
		JTextField state = new JTextField();
		JTextField zip = new JTextField();
		
		Accountholder accountholder = null;
		
		if (selection == JOptionPane.YES_OPTION) {
			

			/* This array of Object objects stores both a labels (as strings) and text fields
			 * that will go into the dialog box */
			Object[] message = {
					"Username:", username,
					"First Name:", firstName,
					"Last Name:", lastName,
					"Street", street,
					"City", city,
					"State", state,
					"Zip code", zip
			};

			// dialog box that will include the registration form
			JOptionPane.showConfirmDialog(null, message, "Select an Option", JOptionPane.OK_CANCEL_OPTION);

			// create an accountholder based on the data the user entered from the text fields
			accountholder = new Accountholder(username.getText(), firstName.getText(), lastName.getText(), street.getText(), city.getText(), state.getText(), zip.getText());
			
			// add the account holder because they are a new customer
			accountholders.add(accountholder);

		} else {
			boolean found = false;
			int numTriesLeft = 3;
			
			do {
				String usr = JOptionPane.showInputDialog(null, "Enter your username");
				
				for (int i = 0; i < accountholders.size(); i++) {
					// If there is a match, that's what accountholder becomes
					if (usr.equalsIgnoreCase(accountholders.get(i).getUsername())) {
						accountholder = accountholders.get(i);
						found = true;
						displayProfile(i); // display the profile to the user
						break;
					}
				}
				if (!found) {
					
					JOptionPane.showMessageDialog(null, "Sorry, no match found!" + " " + numTriesLeft + " tries remaining");
					
					// if the user has some tries left, mark them off by one, otherwise exit this method
					if (numTriesLeft > 0)
						numTriesLeft--;
					else return;
				} else {
					
				}
			} while(!found);
			
			
		}

		double deposit = 0.0; // this will store what the user enters

		do {
			// Prompt for a deposit amount to set up the account
			deposit = Double.parseDouble(JOptionPane.showInputDialog("Enter the desired deposit amount:"));

			if (deposit < 100) {
				JOptionPane.showMessageDialog(null, "Not so fast, you need to make a deposit of $100 or more to create an account.");
			}
			else
				break;

		} while (false);

		Account account = new Account(deposit,accountholder);
		accounts.add(account);

		// Add an account to the Accountholders profile
		accountholder.addAccount(account);



		if (deposit >= 100.0) {

			JOptionPane.showMessageDialog(null, "Account Created!\nAccount Number: " + account.getAccountNumber() + "\nBalance: $" + account.getBalance() + "\n");
		}
		else {
			JOptionPane.showMessageDialog(null, "In order to create a bank account, you must deposit at least $100.00");
		}

	}

	public static void viewAccountholders() {
		String ahString = "Accountholders\n---------------\n";

		for (int i = 0; i < accountholders.size(); i++) {
			ahString += accountholders.get(i).toString() + "\n";
		}

		JOptionPane.showMessageDialog(null, ahString); // Display the accountholders in a dialog box
	}

	public static void searchForAccountholder() {
		String enteredName = JOptionPane.showInputDialog(null, "Enter the last name of the accountholder: ");
		String searchResults = "Search results for " + enteredName + "\n";
		boolean found = false;
		for (int i = 0; i < accountholders.size(); i++) {
			if (accountholders.get(i).getLastName().equalsIgnoreCase(enteredName)) {
				found = true; // say that we found at least one result
				searchResults += accountholders.get(i).toString() + "\n----------\n";
			} 
		}

		/* If we found at least one result, display it in a dialog box. Else, inform the
		   user that no results were found. */
		if (found) {
			JOptionPane.showMessageDialog(null, searchResults);
		} else {
			JOptionPane.showMessageDialog(null, "No results found for the search term " + enteredName + "!");
		}
	}

}

