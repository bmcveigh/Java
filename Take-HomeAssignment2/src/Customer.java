/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brianmcveigh
 */
public class Customer 
{
    private String name;
    private String order;
    private int numberOfOrderedFoods;
    private static final int MAX_NUMBER_OF_FOODS = 3;
    
    public Customer()
    {
        name = "unknown";
        order = "unknown";
    }
    
    public Customer(String n, String o)
    {
        name = n;
        order = o;
        numberOfOrderedFoods++;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String n) 
    {
        name = n;
    }

    public String getOrder() 
    {
        return order;
    }

    public void setOrder(String o) 
    {
        order = o;
    }
    
    public String toString()
    {
        String output = "";
        output += "Customer name: " + name + " Order: " + order + "\n";
        return output;
    }
    
    
    
    
    
}
