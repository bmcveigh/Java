package main;

public class Base {

	public static int clamp(int amount, int min, int max) {
		if(!(min < max))
			return amount;
		if(amount < min)
			return min;
		if(amount > max)
			return max;
		return amount;
	}
	
	public static double clamp(double amount, double min, double max) {
		if(!(min < max))
			return amount;
		if(amount < min)
			return min;
		if(amount > max)
			return max;
		return amount;
	}
	
	  public static int atoi(String s) {
		  try {
			  return Integer.parseInt(s);
		  } catch(NumberFormatException e) {
			  return 0;
		  }
	  }
	
}
