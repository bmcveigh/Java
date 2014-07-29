/*
	Brian McVeigh
	Lab Assignment 8
	April 8, 2011
*/

import javax.swing.JOptionPane;

public class Dealer
{
	public static final int NUMBER_OF_CARS = 25;
	public static void main(String[] args)
	{
		Car[] m_cars = new Car[NUMBER_OF_CARS];
		int m_numCars = 0;
		
		for (;;)
		{
			String make = JOptionPane.showInputDialog("Enter make: ");
			String model = JOptionPane.showInputDialog("Enter model: ");
			int stereo = JOptionPane.showConfirmDialog(null, "Do you want a car with a stereo?", "Select an Option", JOptionPane.YES_NO_OPTION);
			if (stereo == JOptionPane.YES_OPTION)
			{
				CarWithStereo car = new CarWithStereo(make, model);
				m_cars[m_numCars++] = car;
			}
			else
			{
				Car car = new Car(make, model);
				m_cars[m_numCars++] = car;
			}
			if (JOptionPane.showConfirmDialog(null, "Do you want to enter another car?", "Select an Option", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				continue;
			}
			else break;
		} //end for
		
		int result = JOptionPane.showConfirmDialog(null, "Do you want to see cars with a stereo?", "Select an Option", JOptionPane.YES_NO_OPTION);
		//Display cars without stereos
		String output = "";
		if (result == JOptionPane.YES_OPTION)
		{
			output += "Cars with stereo: \n";
		}
		else
		{
			output += "Cars without stereo: \n";
		}

		for (int i = 0; i < m_numCars; i++)
		{
				if (result == JOptionPane.YES_OPTION && m_cars[i] instanceof CarWithStereo)
				{
						output += m_cars[i].toString();
				}
				else if (result == JOptionPane.NO_OPTION && !(m_cars[i] instanceof CarWithStereo))
				{
						output += m_cars[i].toString();
						
				}
				
		}
				JOptionPane.showMessageDialog(null, output);
	}

}
