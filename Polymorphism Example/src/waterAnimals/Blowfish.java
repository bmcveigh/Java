package waterAnimals;
import animals.WaterAnimal;


/**
 * 
 * @author BrianMcVeigh
 *
 */
public class Blowfish extends WaterAnimal {
	public Blowfish() {
		super();
	}
	
	public String speak() {
		return "Puff puff splash splash";
	}
	
	public String breathingMethod() {
		return super.breathingMethod();
	}
	
	public String toString() {
		return super.toString();
	}
	
}
