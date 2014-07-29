/*
	Brian McVeigh
	Lab Assignment 8
	April 8, 2011
*/

public class Car
{
	private String m_make;
	private String m_model;
	public Car(String make, String model) 
	{
		m_make = make;
		m_model = model;
	}
	
	/*
		Method Name: toString()
		Purpose: returns the make and model
		Return value: String
		Parameters: none
	*/
	public String toString()
	{
		return m_make + " " + m_model + "\n";
	}
	
}