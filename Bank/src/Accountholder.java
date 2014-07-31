import java.util.ArrayList;

/**
 * 
 * @project Bank
 * @file Accountholder.java
 * @author BrianMcVeigh
 * @date Jul 30, 2014
 * @time 2:03:06 PM
 */

public class Accountholder {
	private String username;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String zip;
	private ArrayList<Account> accountsOwned;

	public Accountholder(String username, String firstName, String lastName,
			String street, String city, String state, String zip) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		accountsOwned = new ArrayList<Account>();
	}

	public String getUsername() {
		return username;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public ArrayList<Account> getAccountsOwned() {
		return accountsOwned;
	}

	public void setAccountsOwned(ArrayList<Account> accountsOwned) {
		this.accountsOwned = accountsOwned;
	}
	
	// This method should be invoked when the user needs to add an account for an Accountholder
	public void addAccount(Account account) {
		accountsOwned.add(account);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Accountholder [username=" + username + ", firstName="
				+ firstName + ", lastName=" + lastName + ", street=" + street
				+ ", city=" + city + ", state=" + state + ", zip=" + zip
				+ ", accountsOwned=" + accountsOwned + "]";
	}
	
	

}
