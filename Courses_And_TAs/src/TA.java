import java.util.ArrayList;

/**
 * 
 * @author BrianMcVeigh
 *
 */
public class TA {
	private String firstName;
	private String lastName;
		
	private boolean isAssignedToCourse;
	
	private ArrayList<Course> coursesAvailable;
	
	private static int numTA;
	
	

	/**
	 * @param firstName
	 * @param lastName
	 * @param isAssignedToCourse
	 * @param coursesAvailable
	 */
	public TA(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the isAssignedToCourse
	 */
	public boolean isAssignedToCourse() {
		return isAssignedToCourse;
	}

	/**
	 * @param isAssignedToCourse the isAssignedToCourse to set
	 */
	public void setAssignedToCourse(boolean isAssignedToCourse) {
		this.isAssignedToCourse = isAssignedToCourse;
	}

	/**
	 * @return the numTA
	 */
	public static int getNumTA() {
		return numTA;
	}

	/**
	 * @return the coursesAvailable
	 */
	public ArrayList<Course> getCoursesAvailable() {
		return coursesAvailable;
	}

	/**
	 * @param coursesAvailable the coursesAvailable to set
	 */
	public void setCoursesAvailable(ArrayList<Course> coursesAvailable) {
		this.coursesAvailable = coursesAvailable;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	
	
	
}
