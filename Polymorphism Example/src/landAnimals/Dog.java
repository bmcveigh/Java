package landAnimals;
import animals.LandAnimal;


/**
 * 
 * @author BrianMcVeigh
 *
 */
public class Dog extends LandAnimal {
	public Dog() {
		super();
	}
	
	public String speak() {
		return "Woof woof";
	}
	
	public String breathingMethod() {
		return "Mouth";
	}
	
	public String toString() {
		return super.toString();
	}
}
