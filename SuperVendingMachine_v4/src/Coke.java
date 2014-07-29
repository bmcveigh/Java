

/**
 *
 * @author brianmcveigh
 */
public class Coke extends Item
{
    public Coke()
    {
        super();
    }
    
    public Coke(String itemName, double price, int inventory)
    {
        super(itemName,  price, inventory);
        type = "Soda";
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
