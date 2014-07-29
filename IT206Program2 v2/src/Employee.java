import javax.swing.JOptionPane;

public class Employee
{
	private String name;
	private int salary;
	private int years;
	private int performance;
	
	// CONSTANTS
	protected static final double MIN_SALARY = 8000.0;
	protected static final double MAX_SALARY = 200000.0;
	protected static final double BONUS_AMT = 2000.0;
	protected static final int YRS_GET_RAISE = 5; // the number of years required to get a raise
	
	/** 
		Method name: Employee()
		Purpose: Initializes the Employee class
		Return value: none
		Parameters: none
	*/
	public Employee() {
			name = null;
		salary = 0;
			years = 0;
	}
	 
	/** 
		Method name: inputEmployeeName()
		Purpose: Prompts for the employee name and stores it in name
		Return value: none
		Parameters: none
	*/
	public void inputEmployeeName() {
	String employeename = JOptionPane.showInputDialog("Enter employee name: ");
	name = employeename;  
	}
	
	/** 
		Method name: inputMonth()
		Purpose: Asks for the number of months worked
		Return value: none
		Parameters: none
	*/
	public void inputMonth() {
		boolean done = false;
		do {
			int months = 0;
			
			try {
				months = Integer.parseInt(JOptionPane.showInputDialog("Input number of months worked: "));
			} catch( NumberFormatException a) {
				JOptionPane.showMessageDialog(null, "Invalid Input.");
				continue;
			}
			years = (months/12) + ((months%12)/6);
			break;
		} while (!done);
	}
	
	/** 
		Method name: inputSalary()
		Purpose: Prompts and gets the salary of the employee
		Return value: none
		Parameters: none
	*/
	public void inputSalary() {
	 	do {
	 		int value = 0;
	       
			try { //Validation
				value = Integer.parseInt(JOptionPane.showInputDialog("Enter salary:"));
			} catch (NumberFormatException a) {
				JOptionPane.showMessageDialog(null, "Invalid Input.");
				continue;
			}
	
			salary = value;
			
			if (salary < MIN_SALARY || salary > MAX_SALARY) {
				JOptionPane.showMessageDialog(null, "The employee cannot have a salary less than $MIN_SALARY or more than $MAX_SALARY");
			}
		} while (salary < MIN_SALARY || salary > MAX_SALARY);
	}
	
	/** 
		Method name: inputPerformance()
		Purpose: Asks for the performance category where the user can enter 1 for excellent,
		2 for average, and 3 for poor
		Return value: none
		Parameters: none
	*/
	public void inputPerformance() {
		boolean done = false;
		 
		do {
			int category = 0;
			try {
				category = Integer.parseInt(JOptionPane.showInputDialog("Enter " + name + "'s latest review...\n1.  Excellent\n2.  Average\n3.  Poor"));
			}
			catch(NumberFormatException a) {
				JOptionPane.showMessageDialog(null, "Invalid Input.");
				continue;
			}
			performance = category;
			break; //Ends the loop
		} while (!done);
	}
	
	/** 
		Method name: calculateRaise()
		Purpose: Checks to see if the employee has worked for the company has worked
		for more than 5 years and calculates the raise and displays the eligibility if
		this is true.
		Return value: none
		Parameters: none
	*/
	public void calculateRaise() {
		if (years >= YRS_GET_RAISE) {
			JOptionPane.showMessageDialog(null, "The employee is eligible for a raise.");
			salary += (salary * 0.04); 
		}
	}
	
	/** 
		Method name: performance()
		Purpose: Checks to see if the performance is excellent.  If it is,
		the program adds $2000 to the salary.
		Return value: none
		Parameters: none
	*/
	public void calculateBonus() {
	if (performance == 1) {
	  salary = salary += BONUS_AMT;
	  JOptionPane.showMessageDialog(null, "The employee has received a bonus.");
	
	}
	else {
	   JOptionPane.showMessageDialog(null, "The employee has not received a bonus.");
	}
	}
	
	/** 
		Method name: outputEmployee()
		Purpose: Outputs the employee information, including the Employee Name, Employee Salary, and the
		years employed with the company.
		Return value: none
		Parameters: none
	*/
	public void outputEmployee() {
	String outputDisplay = "Employee Name: " + name + "\n";
	outputDisplay += "Employee Salary: $" + salary + "\n";
	outputDisplay += "Employed with company: " + years + " years.";
	JOptionPane.showMessageDialog(null, outputDisplay); // print the employee info to a dialog box
	}



}
