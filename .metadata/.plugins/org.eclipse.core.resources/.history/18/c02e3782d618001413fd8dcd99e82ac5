public class PetHotel
{

	protected Pet[] pets;
	protected Owner[] owners;
	protected Kennel[] kennels;

	public PetHotel()
	{
		pets = new Pet[20];
		owners = new Owner[20];
		kennels = new Kennel[10];
	}

	public void makeFourKennels()
	{
		for (int i = 0; i < 4; i++)
		{
			if (i < 2)
				kennels[i] = new Kennel("small");
			else
				kennels[i] = new Kennel("large");
		}
	} // end createKennels


	public  void testDataOwners()
	{
		owners[0] = new Owner("George", "Washington", "Mount Vernon", "VA", "703-111-1111");
		owners[1] = new Owner("Abraham", "Lincoln", "Springfield", "IL", "312-222-2222");
		owners[2] = new Owner("Barack", "Obama", "Chicago", "IL", "312-333-3333");
	}

	public  void testDataPets()
	{
		pets[0] = new Dog("Fido", "Labrador",  "brown", 55.0, owners[0]);
		pets[1] = new Dog("Rover", "Poodle",  "white", 33.0, owners[0]);
		pets[2] = new Cat("Rasky", "Manx", "black", owners[1]);
		pets[3] = new Dog("Bo", "Portuguese",  "gray", 99.0, owners[2]);
		pets[4] = new Cat("Felix", "Cheshire", "gray",  owners[2]);
	}

	public  Kennel findOpenSmallKennel ()
	{
		for (int i = 0; i < Kennel.getNumberOfKennels(); i++)
		{
			if (!kennels[i].isOccupied() && kennels[i].getType().equalsIgnoreCase("small"))
			{
				return kennels[i];
			}
		} // end for
		return null;
	} // end assignKennel

	public  Kennel findOpenLargeKennel ()
	{
		for (int i = 0; i < Kennel.getNumberOfKennels(); i++)
		{
			if (!kennels[i].isOccupied() && kennels[i].getType().equalsIgnoreCase("large"))
			{
				return kennels[i];
			}
	} // end for
	return null;
	} // end assignKennel

	public  Kennel getKennel(int theId)
	{
		for (int i = 0; i < Kennel.getNumberOfKennels(); i++)
		{
			if (theId == kennels[i].getId())
			{
				return kennels[i];
			}
		}
		return null;
	} // getKennel


	public String getOwnerInfo()
	{
		String output = "\nLIST OF OWNERS\n";
		for(int i =0; i < Owner.getNumberOfOwners(); i++)
		{
			output = output + owners[i].toString();
		}
		return output;
	}// end printOwners

	public String getPetInfo()
	{
		String output = "\nLIST OF PETS \n";
		for(int i = 0; i < Pet.getNumberOfPets(); i++)
		{
			output += pets[i].toString();
		}
		return output;
	} // end getPetInfo

	public String getKennelInfo()
	{
		String output = "\nLIST OF KENNELS\n";
		for(int i =0; i < Kennel.getNumberOfKennels(); i++)
		{
			output += kennels[i].toString();
		}
		return output;
	}// end getKennelInfo

} // end class