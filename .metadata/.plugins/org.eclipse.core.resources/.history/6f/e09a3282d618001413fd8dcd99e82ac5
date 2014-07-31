public class Dog extends Pet
{
	public static double SURCHARGE_FOR_DOG = 5.00;
	double weight;
	boolean bordetella;

	public Dog()
	{
		super("unknown", "unknown", "unknown", null);
		weight = 0.0;
	}

	public Dog(String n, String b,  String c, double w, Owner own)
	{
		super (n, b, c, own);
		weight = w;
	}

	public double getWeight() { return weight; }
	public void setWeight (double w) { weight = w; }

	public boolean vaccinesCurrent() // dog needs both
	{
		if (hadBordetella()  && hadRabies())
		{
		 return true;
	  }
	  return false;
	}

	public boolean hadBordetella() { return bordetella; }
	public void setBordetella(boolean b) { bordetella = b; }

	public double calculateFee(int numDays)
	{
		double fee = 0;
		if (getWeight() > 35)
		  fee =  numDays * Kennel.COST_LARGE_KENNEL + SURCHARGE_FOR_DOG;
		else
		  fee = numDays * Kennel.COST_SMALL_KENNEL ;
		fee += numDays * SURCHARGE_FOR_DOG ;
		return fee;
	}

	public String toString()
	{
		String output = super.toString();
		output = output + "Weight: " + getWeight();
		output = output + "\nHad bordetalla vaccine? " + hadBordetella()
		+ "\n\n";
		return output;
	}

}// end class

