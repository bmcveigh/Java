/**
	Programmer: Brian McVeigh
	Date created: February 13, 2011
	George Mason University
	Programming Assignment 2
	
	The Payroll system holds information on each employee includes their name, number of years
	employed (determined based on the number of months and 
	‘rounds up’ if the number of months in a partial year is at least 6), performance 
	review (which can be: Excellent, Average or Poor) and their salary. Based on user 
	input, an employee may receive a raise (if they have been with the company at least 5 years – a 4% raise) and/or a bonus (if their performance has been Excellent, they receive $2000.)
	Employee information should be entered by the user and includes the employee’s 
	name, number of months employed (is between 0 and 360), salary 
	(between $8,000 and $200,000) and an indicator of performance review.

*/




import javax.swing.JOptionPane;

public class Payroll
{
    public static void main (String[] args)
    {
    	boolean done = false;
    	
    	while (!done) {
		
		    Employee employee = new Employee();

		    do {
		    	 int action = 0;
				 try {
				 	action = Integer.parseInt(JOptionPane.showInputDialog("1. Enter Employee's Information  \n2. Determine if Employee gets a raise \n3.  Determine if Employee gets a bonus \n4.  Output Employee's Information", "Select an option"));
				 } 
				 catch( NumberFormatException a) {
				 	JOptionPane.showMessageDialog(null, "Invalid Input.");
				 	continue;
				 }
				 
			   switch (action) {
				   case 1:
						employee.inputEmployeeName();
						employee.inputMonth();
						employee.inputSalary();
						employee.inputPerformance();
						break;
				
					case 2:
					   employee.calculateRaise();
					   break;
				
					case 3:
					   employee.calculateBonus();
					   break;
				
					case 4:
					   employee.outputEmployee();
					   int result = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm", JOptionPane.YES_NO_OPTION);
					   
					   if (result == JOptionPane.NO_OPTION) {
					       done = true;
					   }			
					   break;
					   
					default: 
					   JOptionPane.showMessageDialog(null, "Invalid input.  Please enter again.");
					   break;
						
			    } //end of switch
		 
		    } while (!done);
		
			if (done == true) {
			    break;
			}
	    }
    }
}


