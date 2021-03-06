import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 
 * @author BrianMcVeigh
 *
 */
public class App {
	
	// we will store our teaching assistants in an array list
	private ArrayList<TA> assistants;
	// same with the courses
	private ArrayList<Course> courses;
	
	public void addCourses() {
		Course it106 = new Course("IT106","Intro to Programming");
		courses.add(it106);
		
		Course it206 = new Course("IT206","OO Programming");
		courses.add(it206);
		
		Course it207 = new Course("IT207","Web Programming");
		courses.add(it207);
		
		Course it306 = new Course("IT306","Swing Programming");
		courses.add(it306);
		
		Course it308 = new Course("IT308","Event-driven Programming");
		courses.add(it308);
	}
	
	public void run() {
		assistants = new ArrayList<TA>();
		courses = new ArrayList<Course>();
		addCourses();
		
		int choice = JOptionPane.showConfirmDialog(null,"Hi. Would you like to add a TA?","Select an Option", JOptionPane.YES_NO_OPTION);
		
		
		
		boolean moreTA = false;
		do { // TA LEVEL
			ArrayList<Course> coursesTATeaching = new ArrayList<Course>();
			String firstName = JOptionPane.showInputDialog("Enter the TA's first name");
			String lastName = JOptionPane.showInputDialog("Enter the TA's last name");
			TA ta = new TA(firstName, lastName);
			
			boolean moreCourses = false;
			do { // COURSE LEVEL
				if (choice == JOptionPane.YES_OPTION) {
					String output = "Okay, which course will " + firstName + " be teaching?\n";
					for (int i = 0; i < courses.size(); i++) {
						if (!courses.get(i).isHasTA()) // display only the courses that TA is not registered for
							output += (i+1) + ". " + courses.get(i).getName() + "  " + courses.get(i).getDescription() + "\n";
					}
					choice = Integer.parseInt(JOptionPane.showInputDialog(output));
					
					// if the course selected does not have a TA, prompt for the name of the TA
					if (!courses.get(choice-1).isHasTA()) {
						coursesTATeaching.add(courses.get(choice-1));
						choice = JOptionPane.showConfirmDialog(null,firstName + " " + lastName + " is now available to teach " + courses.get(choice-1).getName() + ". Would you like to add another course for TA's availability?","Select an Option", JOptionPane.YES_NO_OPTION);
					} else {
						JOptionPane.showMessageDialog(null, "That course already has a TA");
						continue;
					}
					if (choice == JOptionPane.NO_OPTION) {
						ta.setCoursesAvailable(coursesTATeaching);
						assistants.add(ta);
						break;
					}
				} else {
					break;
				}
				
			} while (!moreCourses);
			choice = JOptionPane.showConfirmDialog(null, "Another TA?", "Select an option", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.NO_OPTION) {
				break;
			}
		} while (!moreTA);
		
		
		String output = "";
		for (int i = 0; i < assistants.size(); i++) {
			output += "TA Name: " + assistants.get(i).getFirstName() + " " + assistants.get(i).getLastName() + "\n";
			output += "Courses available to teach: ";
			for (int j = 0; j < assistants.get(i).getCoursesAvailable().size(); j++) {
				output += assistants.get(i).getCoursesAvailable().get(j).getName() + " ";
			}
			output += "\n--------------------------\n";
		}
		JOptionPane.showMessageDialog(null, output);
		
		assignTAs();
		JOptionPane.showMessageDialog(null, displayAssignedTAForCourse());
	}
	
	public void assignTAs() {
		
//		//----------------------------------------------------
//		int choice = Integer.parseInt(JOptionPane.showInputDialog(output));
//		output = "Assistants that can teach " + courses.get(choice-1).getName() + "\n";
//		for (int i = 0; i < courses.size(); i++) {
//			for (int j = 0; j < assistants.size(); j++) {
//				for (int k = 0; k < assistants.get(j).getCoursesAvailable().size(); k++) {
//					if ((assistants.get(j).getCoursesAvailable().get(k).getName()).equals(courses.get(i).getName())) {
//						output += assistants.get(j).getFirstName() +  " " + assistants.get(j).getLastName() + "\n";
//						break;
//					}
//				}
//			}
//		}
		int numCoursesNeedAssigned = courses.size();
		do {
			String output = "Select a course\n";
			for (int i = 0; i < courses.size(); i++) {
				if (!courses.get(i).isHasTA()) {
					output += (i+1) + ". " + courses.get(i).getName() + " " + courses.get(i).getDescription() + "\n";
				}
			}
			int choice = Integer.parseInt(JOptionPane.showInputDialog(output));
			
			String selectedCourse = courses.get(choice-1).getName();
			output = "TAs available to teach for " + courses.get(choice-1).getName() + "\n";
			for (int i = 0; i < assistants.size(); i++) {
				for (int j = 0; j < assistants.get(i).getCoursesAvailable().size(); j++) {
					if (selectedCourse.equals(assistants.get(i).getCoursesAvailable().get(j).getName())) {
						output += (i+1) + ". " + assistants.get(i).getFirstName() + " " + assistants.get(i).getLastName() + "\n";
					}
				}
			}
			int option = Integer.parseInt(JOptionPane.showInputDialog(output));
			courses.get(choice-1).setHasTA(true);
			courses.get(choice-1).setTa(assistants.get(option-1));
			numCoursesNeedAssigned--;
		} while (numCoursesNeedAssigned > 0);
	}
	
	public String displayAssignedTAForCourse() {
		String output = "";
		for (int i = 0; i < courses.size(); i++) {
			output += (i+1) + ". " + courses.get(i).getName() + " " + courses.get(i).getDescription() + "\n";
			output += "Assigned TA: " + courses.get(i).getTa().toString() + "\n--------------\n"; 
		}
		return output;
	}

}
