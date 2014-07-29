import javax.swing.JOptionPane;
public class Main
{
	public static void main (String[] args)
	{
		PetHotel myHotel = new PetHotel();

		myHotel.makeFourKennels();
		myHotel.testDataOwners();
		myHotel.testDataPets();

		kickItOff(myHotel);

	} // end main


	public static void kickItOff(PetHotel theHotel)
	{
		int answer;
		boolean keepGoing = true;
		while (keepGoing)
		{
			answer = printMenu();
			switch (answer)
			{
				case 1: addPet(theHotel); break;
				case 2: addOwner(theHotel); break;
				case 3: addKennel(theHotel); break;
				case 4: checkIn(theHotel); break;
				case 5: checkOut(theHotel);break;
				case 6: updateVaccinations(theHotel);break;
				case 7: System.out.println(theHotel.getPetInfo()); break;
				case 8: System.out.println(theHotel.getOwnerInfo()); break;
				case 9: System.out.println(theHotel.getKennelInfo()); break;
				case 10: keepGoing = false; break;
				default: JOptionPane.showMessageDialog(null, "Invalid choice."); break;
			} // end of switch
		} // end of while loop
	} // end kickItOff

	public static int printMenu()
	{

		String doWhat = "\nWhat would you like to do?" +
		"\n\n1.  Add pet" +
		"\n2.  Add owner" +
		"\n3.  Add kennel" +
		"\n4.  Check in pet" +
		"\n5.  Check out pet" +
		"\n6.  Update vaccination info" +
		"\n7.  Print all pet info" +
		"\n8.  Print all owner info" +
		"\n9.  Print all kennel info" +
		"\n10.  EXIT" +
		"\n\nEnter the number of your choice:  ";
		int choice = Integer.parseInt(JOptionPane.showInputDialog(null, doWhat));
		return choice;
	} // end printMenu

	public static void addPet(PetHotel theHotel)
	{
		String name = JOptionPane.showInputDialog("Name of pet to add?");
		String breed = JOptionPane.showInputDialog("Breed?");
		String color = JOptionPane.showInputDialog("Color?");

		String ownerList = getOwnerList(theHotel);

		int ownerId = Integer.parseInt(JOptionPane.showInputDialog(ownerList + "\n\nWHAT IS THE OWNER ID?\n"));
		Owner owner = theHotel.owners[ownerId - 1]; // owner is the reference variable
		String catOrDog = JOptionPane.showInputDialog("Cat or Dog?");
		if (catOrDog.equalsIgnoreCase("dog"))
		{
			double weight = Double.parseDouble(JOptionPane.showInputDialog("Weight"));
			theHotel.pets[Pet.getNumberOfPets()] = new Dog(name, breed,  color, weight, owner);
		}
		else
		{
			theHotel.pets[Pet.getNumberOfPets()] = new Cat(name, breed,  color, owner);
		}
		JOptionPane.showMessageDialog(null, "Your pet " + name + " has been added.");
	} // end addPet

	public static void addOwner(PetHotel theHotel)
	{
		String firstName = JOptionPane.showInputDialog("First name of owner to add?");
		String lastName = JOptionPane.showInputDialog("Last name?");
		String city = JOptionPane.showInputDialog("City?");
		String state = JOptionPane.showInputDialog("State?");
		String phone = JOptionPane.showInputDialog("Phone?");


		theHotel.owners[Owner.getNumberOfOwners()] = new Owner(firstName, lastName, city, state, phone);
		JOptionPane.showMessageDialog(null, "Owner " + firstName + " " + lastName + " has been added.");

	} // end addOwner

	public static void addKennel(PetHotel theHotel)
	{
		String size = JOptionPane.showInputDialog(null, "What size kennel would you like to add?");
		theHotel.kennels[Kennel.getNumberOfKennels()] = new Kennel(size);
		JOptionPane.showMessageDialog(null, "Kennel of size " + size + " has been added.");
	}

	public static Pet getYourPet(PetHotel theHotel)
	{
		// shows user a list of pet ids and pet names
		// user enters id of pet
		// the pet reference variable is returned
		String petList = "";
		for(int i = 0; i < Pet.getNumberOfPets(); i++)
		{
			petList = petList + "\nId: " + theHotel.pets[i].getId() + " Name: " + theHotel.pets[i].getName();
		}
		int petId = Integer.parseInt(JOptionPane.showInputDialog("What pet?\n" + petList + "\n\nEnter the pet id.\n"));
		return theHotel.pets[petId - 1];
	}// end findPet

	public static String getOwnerList(PetHotel theHotel)
	{
		String ownerList ="";
		for (int i = 0; i < Owner.getNumberOfOwners(); i++)
		{
			ownerList += "\nId: " + theHotel.owners[i].getId() + " " + theHotel.owners[i].getFirstName()
				+ " " + theHotel.owners[i].getLastName();
		}
		return ownerList;
	} // end getOwnerList


	public static void updateVaccinations(PetHotel theHotel)
	{
		 Pet pet = getYourPet(theHotel);
		 pet.updatePetVaccinations();
		 JOptionPane.showMessageDialog(null, "Vaccinations updated for  " + pet.getName());
	 } // end updateVaccinations

	public static void checkIn (PetHotel theHotel)
	{

		Pet pet = getYourPet(theHotel);
		int requestedNights = Integer.parseInt(JOptionPane.showInputDialog("How many nights?"));

		int checkInStatus = pet.checkPetIn(requestedNights, theHotel);
		if (checkInStatus == - 1 )
		{
			JOptionPane.showMessageDialog(null, "Vacinations not current for " + pet.getName() + ".");
		}
		else if (checkInStatus == 0)
		{
			JOptionPane.showMessageDialog(null, "Sorry, no kennels availabel for " + pet.getName() + ".");
		}
		else if (checkInStatus == 1)
		{
			JOptionPane.showMessageDialog(null, "Your pet " + pet.getName() + " is checked in for " + pet.getNumberOfNights() + " nights.");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Unexpected return status");
		}
	}// end checkin

	public static void checkOut (PetHotel theHotel)
	{
		Pet pet = getYourPet(theHotel);
		if (!pet.isCurrentlyBoarding())
		{
			JOptionPane.showMessageDialog(null, "Your pet " + pet.getName() + " is not currently boarding." );
			return;
		}
		else
		{
			String checkOutMessage  = pet.checkPetOut(theHotel);
			JOptionPane.showMessageDialog(null, checkOutMessage);
		}
	}// end checkin

} // end class
