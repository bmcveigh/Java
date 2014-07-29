import javax.swing.JOptionPane;

public class Employee
{
    private String name;
    private int salary;
    private int years;
    private int performance;

/* 
	Method name: Employee()
	Purpose: Initializes the Employee class
	Return value: none
	Parameters: none
*/
    public Employee()
    {
			name = null;
        	salary = 0;
			years = 0;
    }
	 
/* 
	Method name: inputEmployeeName()
	Purpose: Prompts for the employee name and stores it in name
	Return value: none
	Parameters: none
*/
    public void inputEmployeeName()
    {
      String employeename = JOptionPane.showInputDialog("Enter employee name: ");
      name = employeename;  
    }

/* 
	Method name: inputMonth()
	Purpose: Asks for the number of months worked
	Return value: none
	Parameters: none
*/
    public void inputMonth()
    {
	 		boolean done = false;
	 		do {
	 		
				int months = 0;
				try {

        			months = Integer.parseInt(JOptionPane.showInputDialog("Input number of months worked: "));
				}
				catch( NumberFormatException a)
				{
					JOptionPane.showMessageDialog(null, "Invalid Input.");
					continue;
				}
				years = (months/12) + ((months%12)/6);
				break;
				
			
			} while (!done);
				
        
    }

/* 
	Method name: inputSalary()
	Purpose: Prompts and gets the salary of the employee
	Return value: none
	Parameters: none
*/
    public void inputSalary()
    {
	 	do {
       int value = 0;
       
		 try //Validation
		 {
		 	value = Integer.parseInt(JOptionPane.showInputDialog("Enter salary:"));
		 }
		 catch(NumberFormatException a)
		 {
				JOptionPane.showMessageDialog(null, "Invalid Input.");
				continue;
		 }
 
		 salary = value;
		 
		 if (salary < 8000 || salary > 200000)
		 {
		 	JOptionPane.showMessageDialog(null, "The employee cannot have a salary less than $8000 or more than $200000");
   	 }

		 } while (salary < 8000 || salary > 200000);
		 
		 	}

/* 
	Method name: inputPerformance()
	Purpose: Asks for the performance category where the user can enter 1 for excellent,
	2 for average, and 3 for poor
	Return value: none
	Parameters: none
*/
    public void inputPerformance()
    {
	 		boolean done = false;
			 
			do {
				int category = 0;
	 	      try 
				{
					category = Integer.parseInt(JOptionPane.showInputDialog("Enter " + name + "'s latest review...\n1.  Excellent\n2.  Average\n3.  Poor"));
			   }
				catch(NumberFormatException a)
			 	{
					JOptionPane.showMessageDialog(null, "Invalid Input.");
					continue;
		 		}
				performance = category;
				break; //Ends the loop
			} 	while (!done);

	//Validate the input

	
    }

/* 
	Method name: calculateRaise()
	Purpose: Checks to see if the employee has worked for the company has worked
	for more than 5 years and calculates the raise and displays the eligibility if
	this is true.
	Return value: none
	Parameters: none
*/
    public void calculateRaise()
    {
       if (years >= 5)
       {
	  JOptionPane.showMessageDialog(null, "The employee is eligible for a raise.");
	  salary += (salary * 0.04); 
       }
	else
	{
	JOptionPane.showMessageDialog(null, "The employee is not eligible for a raise.");
        }
    }

/* 
	Method name: performance()
	Purpose: Checks to see if the performance is excellent.  If it is,
	the program adds $2000 to the salary.
	Return value: none
	Parameters: none
*/
    public void calculateBonus()
    {
       if (performance == 1)
       {
          salary = salary += 2000;
          JOptionPane.showMessageDialog(null, "The employee has received a bonus.");

       }
       else
       {
           JOptionPane.showMessageDialog(null, "The employee has not received a bonus.");
       }
    }

/* 
	Method name: outputEmployee()
	Purpose: Outputs the employee information, including the Employee Name, Employee Salary, and the
	years employed with the company.
	Return value: none
	Parameters: none
*/
   public void outputEmployee()
   {
       String outputDisplay = "Employee Name: " + name + "\n";
       outputDisplay += "Employee Salary: $" + salary + "\n";
       outputDisplay += "Employed with company: " + years + " years.";

       JOptionPane.showMessageDialog(null, outputDisplay);
   }



}