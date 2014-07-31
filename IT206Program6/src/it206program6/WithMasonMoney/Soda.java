/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it206program6.WithMasonMoney;

/**
 *
 * @author brianmcveigh
 */
public class Soda extends Item 
{
    private static int numberOfSodas;
    public Soda()
    {
        super();
    }
    
    public Soda(String itemName, double price, int inventory)
    {
        super(itemName,  price, inventory);
        type = "Soda";
        numberOfSodas++;
    }
    
    @Override
    public String toString()
    {
        return super.toString();
    }
}
