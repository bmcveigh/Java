
import java.text.DecimalFormat;
import java.text.NumberFormat;

/*
 * Developed by Brian McVeigh
 * George Mason University
 * December 8, 2012
 */

/**
 * @project SuperVendingMachine v4
 * @file Item.java
 * @author BrianMcVeigh
 * @date Jul 29, 2014
 * @time 1:40:38 PM
 */
public abstract class Item
{

    protected String itemName;
    protected String type;
    protected double itemPrice;
    protected int inventory;
    protected static int numberOfItems;
    protected double total;
    protected MasonMoney masonMoney;
    private static String[] itemTypes = {"SODA", "CHIPS"};
     
    /**
     * Method Name: Item()
     * Purpose: constructor
     * Return value: none
     * Parameters: none
     */
    public Item() 
    {
        itemName = "unknown";
        type = "unknown";
        itemPrice = 0.0;
        total = 0.0;
        masonMoney = null;
    }
    
    /**
     * Method Name: Item(String, double, int)
     * Purpose: constructor
     * Return value: none
     * Parameters: itemName: String, itemPrice: double, inventory: int
     */
    public Item(String itemName, double itemPrice, int inventory)
    {
    
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.inventory = inventory;
        numberOfItems++;
    }

   
    /**
     * Method Name: getItemName()
     * Purpose: returns the Item name
     * Return value: String
     * Parameters: none
     */
    public String getItemName() 
    {
        return itemName;
    }
    
    /**
     * Method Name: setItemName(String)
     * Purpose: sets the item name
     * Return value: void
     * Parameters: itemName: String
     */
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    /**
     * Method Name: getItemPrice()
     * Purpose: gets the item price
     * Return value: double
     * Parameters: none
     */
    public double getItemPrice() 
    {
        return itemPrice;
    }

    /**
     * Method Name: setItemPrice(double)
     * Purpose: sets the item price
     * Return value: void
     * Parameters: itemPrice: double
     */
    public void setItemPrice(double itemPrice) 
    {
        this.itemPrice = itemPrice;
    }

    /**
     * Method Name: getType()
     * Purpose: gets the type of item (chips or soda)
     * Return value: String
     * Parameters: none
     */
    public String getType() 
    {
        return type;
    }

      /**
     * Method Name: setType(String)
     * Purpose: sets the item type
     * Return value: void
     * Parameters: type: String
     */
    public void setType(String type) 
    {
        this.type = type;
    }
    
    /**
     * Method Name: getItemTypes()
     * Purpose: gets the list of item types
     * Return value: String
     * Parameters: none
     */
    public static String getItemTypes()
    {
        String output = "";
        for (int i = 0; i < itemTypes.length; i++)
        {
            output += itemTypes[i] + "\n";
        }
        
        return output;
    }
    
    /**
     * Method Name: validateItemTypes(String)
     * Purpose: validates the type of item
     * Return value: boolean
     * Parameters: itemType: String
     */
    public boolean validateItemTypes(String itemType)
    {
       for (int i = 0; i < itemTypes.length; i++)
       {
            if (itemTypes[i].equalsIgnoreCase(itemType))
            {
                return true;
            }
       }
       return false;
    }

    /**
     * Method Name: getInventory()
     * Purpose: gets the inventory
     * Return value: int
     * Parameters: none
     */
    public int getInventory() 
    {
        return inventory;
    }

    /**
     * Method Name: setInventory(int)
     * Purpose: sets the inventory
     * Return value: void
     * Parameters: inventory: int
     */
    public void setInventory(int inventory) 
    {
        this.inventory = inventory;
    }

    /**
     * Method Name: getNumberOfItems()
     * Purpose: gets the number of Items
     * Return type: int
     * Parameters: none
     */
    public static int getNumberOfItems() 
    {
        return numberOfItems;
    }
    
    /**
     * Method Name: getMasonMoney()
     * Purpose: returns a MasonMoney object
     * Return type: MasonMoney
     * Parameters: none
     */
    public MasonMoney getMasonMoney()
    {
        return masonMoney;
    }
    
    /**
     * Method Name: setMasonMoney(MasonMoney)
     * Purpose: sets a mason money object
     * Return type: void
     * Parameters: masonMoney: MasonMoney
     */
    public void setMasonMoney(MasonMoney masonMoney)
    {
        this.masonMoney = masonMoney;
        
    }

    /**
     * Method Name: toString()
     * Purpose: override of toString() in the String class that provides a nice output
     * Return value:  String
     * Parameters: none
     */
    @Override
    public String toString() 
    {
        NumberFormat myFormatter = new DecimalFormat("0.00");
    	String output = "";
        output += "Item Name: " + itemName + "\r\n";
        output += "Item Price: $" + myFormatter.format(itemPrice) + "\r\n";
        output += "Total: $" + myFormatter.format(total) + "\r\n";
        return output;
    }

    /**
     * Method Name: sell(int)
     * Purpose: sells a certain number of items and adjusts the inventory
     * Return type: void
     * Parameters: howMany: int
     */
    public void sell(int howMany) 
    {
        total = (howMany * itemPrice);
        inventory -= howMany;
    }

    /**
     * Method Name: getTotal()
     * Purpose: returns the total price of the item
     * Return value: double
     * Parameters: none
     */
    public double getTotal() 
    {
        return total;
    }
    
    
    
    
    
    
    
    
    
}
