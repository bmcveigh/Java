import waterAnimals.Blowfish;
import landAnimals.Dog;
import animals.Animal;

/**
 * @author BrianMcVeigh
 *
 */
public class PolymorphismInAction {
	
	public static void main(String[] args) {
		
		Dog dog = new Dog();
		Blowfish blowfish = new Blowfish();
		
		Animal[] animals = {dog, blowfish};
		
		// Below is the polymorphism happening
		for (int i = 0; i < animals.length; i++) {
			System.out.println("Speak method invoked: " + animals[i].speak() + "\nBreathing method: " + animals[i].breathingMethod());
			System.out.println("============================================");
		}
	}
}
