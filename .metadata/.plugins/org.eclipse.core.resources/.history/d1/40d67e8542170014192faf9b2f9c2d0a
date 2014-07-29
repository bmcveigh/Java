public class Cat extends Pet
{
	public Cat()
	{
		super("unknown", "unknown", "unknown", null);
	}

	public Cat(String n, String b,  String c, Owner own)
	{
		super (n, b, c, own);
	}

	public double calculateFee(int numDays)
	{
		return numDays * Kennel.COST_SMALL_KENNEL;
	}

	public boolean vaccinesCurrent() // cat needs just rabies
	{
		if (hadRabies())
		{
		 return true;
		}
		return false;
	}

	public String toString()
	{
		String output = super.toString();
		return output;
	}

}// end class

