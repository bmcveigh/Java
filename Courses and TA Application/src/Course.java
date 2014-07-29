/**
 * 
 * @author BrianMcVeigh
 *
 */
public class Course {
	private String name;
	private String description;
	
	private TA ta;
	
	private boolean hasTA;

	/**
	 * @param courseName
	 * @param courseDescription
	 * @param hasTA
	 */
	public Course(String courseName, String courseDescription) {
		super();
		this.name = courseName;
		this.description = courseDescription;
	}

	/**
	 * @return the courseName
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param courseName the courseName to set
	 */
	public void setName(String courseName) {
		this.name = courseName;
	}

	/**
	 * @return the courseDescription
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param courseDescription the courseDescription to set
	 */
	public void setDescription(String courseDescription) {
		this.description = courseDescription;
	}

	/**
	 * @return the hasTA
	 */
	public boolean isHasTA() {
		return hasTA;
	}

	/**
	 * @param hasTA the hasTA to set
	 */
	public void setHasTA(boolean hasTA) {
		this.hasTA = hasTA;
	}
	

	/**
	 * @return the ta
	 */
	public TA getTa() {
		return ta;
	}

	/**
	 * @param ta the ta to set
	 */
	public void setTa(TA ta) {
		this.ta = ta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Course [courseName=" + name + ", courseDescription="
				+ description + ", hasTA=" + hasTA + "]";
	}
	
	
	
}
