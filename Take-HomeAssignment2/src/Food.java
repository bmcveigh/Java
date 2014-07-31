/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brianmcveigh
 */
public class Food 
{
    private String name;
    private double price;
    
    public Food()
    {
        name = "unknown";
        price = 0.0;
    }
    
    public Food(String n, double p)
    {
        name = n;
        price = p;
    }
    
    public void setName(String n)
    {
        name = n;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setPrice(double p)
    {
        price = p;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    @Override
    public String toString()
    {
        String output = "";
        output += "Dish Name: " + name + " Price: $" + price + "\n";
        return output;
    }
    
    
}
