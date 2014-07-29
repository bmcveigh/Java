public class Kennel
{

	public static final double COST_LARGE_KENNEL = 75.00;
	public static final double COST_SMALL_KENNEL = 50.00;

	private int id;
	private String type;
	private boolean occupied;

	private static int numberOfKennels;

	public Kennel (String theType)
	{
		occupied = false;
		type = theType;
		numberOfKennels++;
		id = numberOfKennels;
	}

	public int getId() { return id; }

	public boolean isOccupied() { return occupied; }
	public void setOccupied(boolean occ) { occupied = occ; }

	public String getType() { return type;}
	public void setType(String newType) { type = newType; }


	public static int getNumberOfKennels() { return numberOfKennels; };

	public String toString()
	{
		String output = "\n\nKennel id: " + id
      + "\nSize: " + type
		  + "\nOccupied " + occupied;

		return output;
	} // end toString

} // end class
