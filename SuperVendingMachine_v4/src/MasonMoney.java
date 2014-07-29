

/**
 *
 * @author brianmcveigh
 */
public class MasonMoney
{
    private String cardNumber;
    private String studentName;
    private String gNumber;
    private double balance;
    private static int numberOfAccounts;
    
    
    public MasonMoney()
    {
        cardNumber = "123456";
        studentName = "Gunston The Patriot";
        gNumber = "G12345678";
        balance = 5.00;
    }
    
    public MasonMoney(String cardNumber, String studentName, String gNumber, double balance)
    {
       
        this.cardNumber = cardNumber;
        this.studentName = studentName;
        this.gNumber = gNumber;
        this.balance = balance;
        numberOfAccounts++;
    }

    public String getCardNumber() 
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) 
    {
        this.cardNumber = cardNumber;
    }

    public String getStudentName() 
    {
        return studentName;
    }

    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public double getBalance() 
    {
        return balance;
    }

    public void setBalance(double balance) 
    {
        this.balance = balance;
    }

    public static int getNumberOfAccounts() 
    {
        return numberOfAccounts;
    }

	public void deposit(double amt) {
		balance += amt;
	}
    
    @Override
    public String toString() 
    {
        String output = "";
        output += "G Number: " + gNumber + "\n";
        output += "Student Name: " + studentName + "\n";
        output += "Card Number: " + "***" + cardNumber.substring(3, 6) + "\n";
        output += "Balance: $" + getBalance() + "\n";
        return output;
    }



    
    
    
    
    
}
