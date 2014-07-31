/*
 * Brian McVeigh
 * Program 6
 */
package it206program6;

/**
 *
 * @author brianmcveigh
 */
public abstract class Item
{

    protected String itemName;
    protected String type;
    protected double itemPrice;
    protected int inventory;
    protected static int numberOfItems;
    protected double total;
    private static String[] itemTypes = {"SODA", "CHIPS"};
    public Item() 
    {
        itemName = "unknown";
        type = "unknown";
        itemPrice = 0.0;
        total = 0.0;
    }
    
    public Item(String itemName, double itemPrice, int inventory)
    {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.inventory = inventory;
        numberOfItems++;
    }

    public String getItemName() 
    {
        return itemName;
    }

    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public double getItemPrice() 
    {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) 
    {
        this.itemPrice = itemPrice;
    }

    public String getType() 
    {
        return type;
    }

    public void setType(String type) 
    {
        this.type = type;
    }
    public static String getItemTypes()
    {
        String output = "";
        for (int i = 0; i < itemTypes.length; i++)
        {
            output += itemTypes[i] + "\n";
        }
        
        return output;
    }
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

    public int getInventory() 
    {
        return inventory;
    }

    public void setInventory(int inventory) 
    {
        this.inventory = inventory;
    }

    public static int getNumberOfItems() 
    {
        return numberOfItems;
    }
    
    

    @Override
    public String toString() 
    {
        String output = "";
        output += "Item Name: " + itemName + "\n";
        output += "Item Price: " + itemPrice + "\n";
        output += "Total: $" + total;
        return output;
    }

    public void sell(int howMany) 
    {
        total += (howMany * itemPrice);
        inventory -= howMany;
    }

    public double getTotal() 
    {
        return total;
    }
    
    
    
    
    
    
    
    
}
