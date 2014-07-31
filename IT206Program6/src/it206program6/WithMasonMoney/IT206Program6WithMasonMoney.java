/*
 * Brian McVeigh
 * Program 6
 */
package it206program6.WithMasonMoney;

/**
 *
 * @author brianmcveigh
 */
import javax.swing.JOptionPane;
public class IT206Program6WithMasonMoney {

    /**
     * @param args the command line arguments
     */
    
    public static Item[] items;
    public static MasonMoney[] accounts;
    public static double sales;
    public static int numberOfTransactions;
    
    public IT206Program6WithMasonMoney()
    {
        
        items = new Item[200];
        accounts = new MasonMoney[100];
    }
    
    public static void main(String[] args) 
    {
        // TODO code application logic here
        IT206Program6WithMasonMoney vendingMachine = new IT206Program6WithMasonMoney();
        boolean done = false;
       
        
        vendingMachine.addSomeItems();
        
        addMasonMoneyAccounts();
        
       
        
            do {
                 try {
                 int choice = Integer.parseInt(JOptionPane.showInputDialog("What would you like to do?\n\n" + "1. Buy Item\n" +
                    "2. Restock Inventory\n" + "3. Add an Item to Inventory\n" + "4. View inventory for each item\n" +
                    "5. Display Mason Money Accounts \n6. Sales information\n7. EXIT\n"));
                switch(choice)
                {
                    case 1: 
                        buyItem();
                        break;
                    case 2: 
                        restockInventory();
                        break;
                    case 3: 
                        addItemToInventory();
                        break;
                    case 4:
                        displayInventory();
                        break;
                    case 5:
                        viewMasonMoneyAccounts();
                        break;
                    case 6:
                        printSalesInformation();
                        break;
                                
                    case 7:
                        done = true;
                        JOptionPane.showMessageDialog(null, "Have a nice day!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice.");
                        break;
                } //end switch
              }
              catch (Exception e)
              {
                   JOptionPane.showMessageDialog(null, "Sorry, but our developer did not realize what he was doing.\nPlease contact an administrator.\nException: " + e);  
              }
            } while(!done);
        }
       
    
    
    public void addSomeItems()
    {
        items[Item.getNumberOfItems()] = new Soda("Coca-Cola", 1.50, 10);
        items[Item.getNumberOfItems()] = new Soda("Sprite", 1.50, 10);
        items[Item.getNumberOfItems()] = new Soda("Dr. Pepper", 1.50, 10);
        items[Item.getNumberOfItems()] = new Soda("Pepsi", 1.50, 10);
        items[Item.getNumberOfItems()] = new Soda("Mountain Dew", 1.50, 10);
        
        items[Item.getNumberOfItems()] = new Chips("Lays", 1.00, 10);
        items[Item.getNumberOfItems()] = new Chips("Doritos", 1.00, 10);
        items[Item.getNumberOfItems()] = new Chips("Snyders Pretzels", 1.00, 10);
        
    }
    
    public static void addMasonMoneyAccounts()
    {
        accounts[0] = new MasonMoney("123456", "Brian McVeigh", "G00571693", 10.00);
        accounts[1] = new MasonMoney("572126", "Colby McVeigh", "G00571694", 20.00);
        accounts[2] = new MasonMoney("722418", "John Liebermann", "G00771593", 1.00);
        accounts[3] = new MasonMoney("234567", "Luke Lam", "G005577123", 50.27);
        accounts[4] = new MasonMoney("112233", "Gunston Patriot", "G006633335", 3.50);
    }
    
    /**
     * buyItem()
     */
    public static void buyItem()
    {
        boolean done = false;
    
            String output = "";
            double total = 0.0;
            double amountInserted = 0.0;

            for (int i = 0; i < Item.getNumberOfItems(); i ++)
            {
                output += (i + 1) + ". " + items[i].getItemName() + " $" + items[i].getItemPrice() + "\n";
            }

            int choice = Integer.parseInt(JOptionPane.showInputDialog("What would you like to buy?  \n\n" + output));
            int howMany = Integer.parseInt(JOptionPane.showInputDialog("How many " + items[choice - 1].getItemName() + " would you like to buy?"));
            

              

            if (items[choice - 1].getInventory() <= 0)
            {
                JOptionPane.showMessageDialog(null, "Sorry but we are out of stock!");
             

            }
            else if (items[choice - 1].getInventory() < howMany)
            {
                JOptionPane.showMessageDialog(null, "We don't have that many in stock!");
               
            }
            else
            {
                items[choice - 1].sell(howMany);
                total = items[choice - 1].getTotal();
  

           double theTotal = total;
         
           sales += total;
           boolean found = false;
             do { 
             if (total > 0)
              {

                  
                  int paymentChoice = Integer.parseInt(JOptionPane.showInputDialog("Your total is: $" + total + "\nAmount inserted: $" + amountInserted + "\nEnter Coin:\n1. QUARTER\n2. DIME\n3. NICKEL\n4. $1 BILL\n5. $5 BILL\n6. Mason Money\n7. Remove change"));
                
                  switch (paymentChoice)
                  {
                      
                      default:
                          JOptionPane.showMessageDialog(null, "Invalid choice.");
                          break;
                      case 1:
                          total -= .25;
                          amountInserted += .25;
                          JOptionPane.showMessageDialog(null, "Quarter inserted\nBalance: " + total);
                          break;
                      case 2:
                          total -= .10;
                          amountInserted += .10;
                          JOptionPane.showMessageDialog(null, "Dime inserted\nBalance: $" + total);
                          break;
                      case 3:
                          total -= .05;
                          amountInserted += .05;
                          JOptionPane.showMessageDialog(null, "Nickel inserted\nBalance: $" + total);
                          break;
                      case 4:
                          total -= 1.00;
                          amountInserted += 1.00;
                          JOptionPane.showMessageDialog(null, "$1 inserted\nBalance: $" + total);
                          break;
                      case 5:
                          total -= 5.00;
                          amountInserted += 5.00;
                          JOptionPane.showMessageDialog(null, "$5 inserted\nBalance: $" + total);
                          break;
                      case 6:
                          String cardNumber = JOptionPane.showInputDialog("Enter your card number.");
                         
                          for (int i = 0; i < accounts.length; i++)
                          {
                              
                              if(cardNumber.equals(accounts[i].getCardNumber()))
                              {
                                    found = true;  

                                      if (accounts[i].getBalance() > 0.0 && accounts[i].getBalance() <= total && accounts[i].getBalance() > 0.0)
                                      {
                                         total -= accounts[i].getBalance();
                                         accounts[i].setBalance(accounts[i].getBalance() - theTotal);
                                         if (total <= 0.0)
                                         {
                                             JOptionPane.showMessageDialog(null, "=====RECEIPT===== \n" + items[choice - 1].toString() + "\nQuantity: " + howMany + "\nAmount Tendered: $" + (Math.abs(total) + theTotal) + "\nChange: $ " + Math.abs(total) + "\n" + "Student Name: " + accounts[i].getStudentName().toUpperCase() + "\nBalance: " + accounts[i].getBalance() + "\n=====Thank You!!!=====\n");
                                         }
                                         break;
                                      }
                                      else if (accounts[i].getBalance() > 0.0 && accounts[i].getBalance() >= total)
                                      {
                                          total -= total;
                                          accounts[i].setBalance(accounts[i].getBalance() - theTotal);
                                          if (total <= 0.0)
                                          {
                                             JOptionPane.showMessageDialog(null, "=====RECEIPT===== \n" + items[choice - 1].toString() + "\nQuantity: " + howMany + "\nAmount Tendered: $" + (Math.abs(total) + theTotal) + "\nChange: $ " + Math.abs(total) + "\n" + "Student Name: " + accounts[i].getStudentName().toUpperCase() + "\nBalance: " + accounts[i].getBalance() + "\n=====Thank You!!!=====\n");
                                          }
                                          break;
                                      
                                      }
                                      else if (accounts[i].getBalance() <= 0.0)
                                      {
                                          JOptionPane.showMessageDialog(null, "Sorry, you don't have any money on your card!");
                                          accounts[i].setBalance(0.0);
                                          break;
                                      }
                                     
                                       
                                  
                              }
                               else if (found == false && MasonMoney.getNumberOfAccounts() - 1 != i)
                               {
                                  
                                      continue;
                               }
                               else if (found == false && MasonMoney.getNumberOfAccounts() - 1 == i)
                               {
                                    JOptionPane.showMessageDialog(null, "Card number not found");
                                    break;
                               }
                             
                         } //end for
                          
                          break;
                      case 7: 
                          
                          total += amountInserted;
                          amountInserted = 0.0;
                          JOptionPane.showMessageDialog(null, "Change removed.");
                          break;
                          
                     
                  } //end switch
                
                  if (total <= 0.0 && found == false)
                  {
                      JOptionPane.showMessageDialog(null, "=====RECEIPT===== \n" + items[choice - 1].toString() + "\nQuantity: " + howMany + "\nAmount Tendered: $" + (Math.abs(total) + theTotal) + "\nChange: $ " + Math.abs(total) + "\n=====Thank You!!!=====\n");
                  }
              if (total <= 0)
              {
                  numberOfTransactions++;
              }
            } //end if
             } while (total > 0);
              } //end else
                 

       
        
        
        
    }
    
    /**
     *
     */
    public static void restockInventory()
    {
        String output = "";
        int ok = 0;
        for (int i = 0; i < Item.getNumberOfItems(); i ++)
        {
          if (items[i].getInventory() <= 1)
          {
               output += (i + 1) + ". " + items[i].getItemName() + " " + items[i].getItemPrice() + " Inventory: " + items[i].getInventory() + "\n";
          }
          
           
        }
        
        if (!output.equals(""))
        {
              int choice = Integer.parseInt(JOptionPane.showInputDialog("What would you like to restock?  \n\n" + output));
              items[choice - 1].setInventory(10);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Good news!  No need to restock!");
        }
    }
    
    public static void addItemToInventory()
    {
        String chipsSoda = JOptionPane.showInputDialog("Are we adding chips or soda?");
        String itemName = JOptionPane.showInputDialog("Enter the item name.");
        double itemPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter the item price."));
        int inventory = Integer.parseInt(JOptionPane.showInputDialog("Enter the item inventory."));
        

        
        for (int i = 0; i < items.length; i++)
        {
            
            if (items[i] != null)
            {
                if (chipsSoda.equalsIgnoreCase("chips"))
                {
                    items[Item.getNumberOfItems()] = new Chips(itemName, itemPrice, inventory);
                     JOptionPane.showMessageDialog(null, "Item created.");
                    break;
                }
                else if (chipsSoda.equalsIgnoreCase("soda"))
                {
                    items[Item.getNumberOfItems()] = new Soda(itemName, itemPrice, inventory);
                     JOptionPane.showMessageDialog(null, "Item created.");
                    break;
                }
               
            }
        }
    }
    
    public static void displayInventory()
    {
        String output = "\n\nINVENTORY LIST\n\n";
        for (int i = 0; i < Item.getNumberOfItems(); i ++)
        {
          
            output += (i + 1) + ". " + items[i].getItemName() + " $" + items[i].getItemPrice() + " Inventory: " + items[i].getInventory() + "\n";
         
          
        }
        JOptionPane.showMessageDialog(null, output);
    }
    
    public static void viewMasonMoneyAccounts()
    {
        String output = "";
        for (int i = 0; i < accounts.length; i++)
        {
            if (accounts[i] != null)
            {
              output += accounts[i].toString() + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, output);
        System.out.print(output);
        System.out.print("Process Complete.");
    }
    
    public static void printSalesInformation()
    {
        String output = "\n\nSALES INFORMATION\n\n";
        output += "Total Sales: $" + sales + "\nNumber of transactions: " + numberOfTransactions;
        JOptionPane.showMessageDialog(null, output);
    }
}
