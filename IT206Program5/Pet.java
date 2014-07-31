
public abstract class Pet
{
	private String name;
	private String breed;
	private String color;
	private boolean rabies;
	private int id;
	private Owner owner;

	private boolean currentlyBoarding;
	private int kennelId;
	private int numberOfNights;

	private static int numberOfPets;

	public Pet()
	{
		this("unknown", "unknown", "unknown", null);
	}

	public Pet(String n, String b, String c,  Owner own)
	{
		name = n;
		breed = b;
		color = c;
		owner = own;
		numberOfPets++;
		id = numberOfPets;
	}

	public abstract double calculateFee(int numDays);

	public abstract boolean vaccinesCurrent();


	public String getName() { return name; }
	public void setName(String n) { name = n;}

	public String getBreed() { return breed; }
	public void setBreed(String b) { breed = b;}

	public String getColor() { return color; }
	public void setColor(String c) { color = c;}

	public boolean hadRabies() { return rabies; }
	public void setRabies(boolean r) { rabies = r; }

	public Owner getOwner() { return owner; }
	public void setOwner(Owner oh) { owner = oh;}

	public int getId() { return id; }

	public void setCurrentlyBoarding(boolean b) { currentlyBoarding = b; }
	public boolean isCurrentlyBoarding() { return currentlyBoarding; }

	public int getKennelId() { return kennelId; };
	public void setKennelId(int id) { kennelId = id; };

	public int getNumberOfNights() { return numberOfNights; };
	public void setNumberOfNights(int num) { numberOfNights = num; };

	public static int getNumberOfPets() { return numberOfPets;}

	public String toString()
	{
		String boardingInfo = "";
		String message =
		  "\nPET Id: " + id
			+ "\nPet name: " + name
			+ "\nType: " + ((this instanceof Dog)? "DOG": "CAT")
			+ "\nBreed: " + breed
			+ "\nColor: " + color
			+ "\nHad rabies vacinne? " + rabies
			+ "\n OWNER: " + owner.getFirstName() + " " + owner.getLastName()
			+ "\n Currently Boarding?: " + currentlyBoarding
			+ "\n";
	  if (currentlyBoarding)
	  {
			boardingInfo = "\n    Kennel Id: " + getKennelId()
			+ "\n    Number of nights: " +  getNumberOfNights()
			+ "\n";
			message += boardingInfo;
		}

		return message;
	} // end toString

	public void updatePetVaccinations()
	{
		this.setRabies(true);
		//this.setBordetella(true); // must cast to Dog first
		if (this instanceof Dog)
		{
			Dog dog = (Dog)this;
			dog.setBordetella(true);
		}
	}

	public int checkPetIn(int requestedNights, PetHotel myHotel)
	{
		// return -1 if vaccinations not current
		// return 0 if kennel not available
		// return 1 if checked in successfully

	// check vaccinations
			if (!this.vaccinesCurrent())
			{
				return -1;
			}
			Kennel openKennel = null;
			if (this instanceof Cat)
			{
				openKennel = myHotel.findOpenSmallKennel();
			}
			else if (this instanceof Dog)
			{
				Dog fakeDog = (Dog)this;
				if (fakeDog.getWeight() < 35)
				{
				  openKennel = myHotel.findOpenSmallKennel();
				}
				else
				{
					openKennel = myHotel.findOpenLargeKennel();
		    }
			} // end outer if
			if (openKennel == null)
			{
				return 0;
			}
			else
			{
				openKennel.setOccupied(true);
				this.setCurrentlyBoarding(true);
				this.setKennelId(openKennel.getId());
				this.setNumberOfNights(requestedNights);
				return 1;
			}
		} // end checkPetIn method


		public String checkPetOut(PetHotel theHotel)
		{
			String invoiceInfo = "";
			double costPerNight,totalDue;
			double totalDogSurcharge = 0.0;
			int kennelId = this.getKennelId();
			Kennel theKennel = theHotel.getKennel(kennelId);
			if (theKennel.getType().equalsIgnoreCase("small"))
				{ costPerNight = Kennel.COST_SMALL_KENNEL; }
			else
				{ costPerNight = Kennel.COST_LARGE_KENNEL; }
			if (this instanceof Dog)
			{
				totalDogSurcharge = Dog.SURCHARGE_FOR_DOG * numberOfNights;
			}
			totalDue = costPerNight * numberOfNights + totalDogSurcharge;
			invoiceInfo =  "Checking out  " + name + "."
				+ "\nNumber of nights: " + numberOfNights
				+ "\nCost per night: " + costPerNight;
				if (this instanceof Dog)
				{
					invoiceInfo += "\nSurcharge for dog: " + totalDogSurcharge;
				}
				invoiceInfo += "\nTotal due: " + totalDue
				+ "\n";
			theKennel.setOccupied(false);
			currentlyBoarding = false;
			kennelId = 0;
			numberOfNights = 0;
			return invoiceInfo;
		} // end checkPetOut method


}// end class

