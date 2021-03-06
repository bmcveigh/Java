

/**
 * 
 * @project SuperVendingMachine v4
 * @file Pepsi.java
 * @author BrianMcVeigh
 * @date Jul 29, 2014
 * @time 1:44:21 PM
 */
public class Pepsi extends Item
{
    public Pepsi()
    {
        super();
    }
    
    public Pepsi(String itemName, double price, int inventory)
    {
        super(itemName,  price, inventory);
        type = "Chips";
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
        return super.toString();
    }
}
