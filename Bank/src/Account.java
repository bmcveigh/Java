import java.util.ArrayList;

// BrianMcVeigh.com
// A simple bank application

/**
 * 
 * @project Bank
 * @file Account.java
 * @author BrianMcVeigh
 * @date Jul 30, 2014
 * @time 12:50:50 PM
 */

public class Account {
	private String accountNumber;
	private double balance;
	private ArrayList<Transaction> transactions;
	private Accountholder accountholder;
	
	private static int numberOfAccounts = 100;
	
	/**
	 * 
	 * @param balance
	 * @param accountholder
	 */
	public Account(double balance, Accountholder accountholder) {
		numberOfAccounts++;
		accountNumber = "" + numberOfAccounts;
		this.balance = balance;
		transactions = new ArrayList<Transaction>();
		this.accountholder = accountholder;
	}
	
	
	/**
	 * 
	 * @method_name setAccountNumber
	 * @return_type void
	 * @param accountNumber
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * 
	 * @method_name getBalance
	 * @return_type double
	 * @return
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * 
	 * @method_name setBalance
	 * @return_type void
	 * @param balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * 
	 * @method_name getTransactions
	 * @return_type ArrayList<Transaction>
	 * @return
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	/**
	 * 
	 * @method_name setTransactions
	 * @return_type void
	 * @param transactions
	 */
	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	/**
	 * 
	 * @method_name getAccountNumber
	 * @return_type String
	 * @return
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	
/**
 * 
 * @method_name withdraw
 * @return_type boolean
 * @param amount
 * @return
 */
	public boolean withdraw(double amount) {
		if (amount <= balance) { // makes sure the user has money in their bank account
			Transaction transaction = new Transaction("BANK", "WITHDRAWAL", amount);
			transactions.add(transaction); // Add the transaction to the array list
			balance -= amount; // Subtract from the account balance
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @method_name deposit
	 * @return_type void
	 * @param amount
	 */
	public void deposit(double amount) {
		Transaction transaction = new Transaction("BANK", "DEPOSIT", amount);
		transactions.add(transaction); // Add the transaction to the array list
		balance += amount; // Add to the account balance
	}
	
	

	/**
	 * 
	 * @method_name getAccountholder
	 * @return_type Accountholder
	 * @return
	 */
	public Accountholder getAccountholder() {
		return accountholder;
	}

	/**
	 * 
	 * @method_name setAccountholder
	 * @return_type void
	 * @param accountholder
	 */
	public void setAccountholder(Accountholder accountholder) {
		this.accountholder = accountholder;
	}

	@Override
	public String toString() {
		return "accountNumber=" + accountNumber + "\nbalance=$"
				+ balance + "\ntransactions=" + transactions;
	}

}
