// BrianMcVeigh.com
// A simple bank application

/**
 * 
 * @project Bank
 * @file Transaction.java
 * @author BrianMcVeigh
 * @date Jul 30, 2014
 * @time 12:51:22 PM
 */

public class Transaction {
	private String vendor;
	private String description;
	private double amount;
	
	/* This constructor creates a new Transaction object
	 * by passing in the fields <vendor>, <description>, and
	 * <amount> */
	public Transaction(String vendor, String description, double amount) {
		super();
		this.vendor = vendor;
		this.description = description;
		this.amount = amount;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "vendor=" + vendor + ", description=" + description
				+ ", amount=$" + amount + "";
	}

}
