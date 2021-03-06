import java.text.DecimalFormat;
import java.text.NumberFormat;
/**
 * @project SuperVendingMachine v4
 * @file Transaction.java
 * @author BrianMcVeigh
 * @date Jul 29, 2014
 * @time 1:41:24 PM
 */
public class Transaction {
	private String itemName;
	private double itemPrice;
	private int quantityPurchased;
	private String custName;
	
	private static int numberOfTransactions;
	
	public Transaction(String itemName, double itemPrice,
			int quantityPurchased, String custName) {
		super();
		++numberOfTransactions;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.quantityPurchased = quantityPurchased;
		this.custName = custName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getQuantityPurchased() {
		return quantityPurchased;
	}

	public void setQuantityPurchased(int quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	
	
	public static int getNumberOfTransactions() {
		return numberOfTransactions;
	}

	@Override
	public String toString() {
		String s = "";
		NumberFormat myFormatter = new DecimalFormat("0.00");
		s += "Item purchased: " + itemName + "\r\n";
		s += "Item price: $" + myFormatter.format(itemPrice) + "\r\n";
		s += "Quantity purchased: " + quantityPurchased + "\r\n";
		s += "Customer name: " + custName + "\r\n";
		
		return s;
	}
}
