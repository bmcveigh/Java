/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it206program6;

/**
 *
 * @author brianmcveigh
 */
public class Chips extends Item
{
    private static int numberOfChips;
    public Chips()
    {
        super();
    }
    
    public Chips(String itemName, double price, int inventory)
    {
        super(itemName,  price, inventory);
        type = "Chips";
        numberOfChips++;
    }

    public int getNumberOfChips() 
    {
        return numberOfChips;
    }


    
    @Override
    public String toString()
    {
        return super.toString();
    }
}
