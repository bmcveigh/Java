public class Owner
{
	private String firstName;
	private String lastName;
	private String city;
	private String state;
	private String phone;
	private int id;

	private static int numberOfOwners = 0;

	public Owner()
	{
		this("unknown", "unknown", "unknown", "unknown", "unknown");
	}

	public Owner(String fn, String ln,String c, String s, String p)
	{
		firstName = fn;
		lastName = ln;
		city = c;
		state = s;
		phone = p;
		id = ++numberOfOwners;
		//System.out.println("Made it to Owner constructor");
	}

	public String getFirstName() { return firstName; }
	public void setFirstName(String fn) { firstName = fn;}

	public String getLastName() { return lastName; }
	public void setLastName(String ln) { lastName = ln;}

	public String getCity() { return city; }
	public void setCity(String c) { city = c;}

	public String getState() { return state; }
	public void setState(String s) { state = s;}

	public String getPhone() { return phone; }
	public void setPhone(String p) { phone = p;}

	public int getId() { return id; }

	public static int getNumberOfOwners() {return numberOfOwners;}

	public String toString()
	{
		String message = "\nFirst Name: " + firstName
		  + "\nLast Name: " + lastName
		  + "\nCity: " + city
		  + "\nState: " + state
		  + "\nPhone: " + phone
		  + "\nOwner Id: " + id
		  + "\n";
		return message;
	} // end toString





} // end class