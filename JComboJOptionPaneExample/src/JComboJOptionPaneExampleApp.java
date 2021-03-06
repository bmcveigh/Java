import javax.swing.*;

/**
 * 
 * @author BrianMcVeigh
 *
 */
public class JComboJOptionPaneExampleApp {
	
	public static void main(String[] args) {
		
		// Create our array of combo box choices
		String[] comboBoxChoices = {
				"Select an option...",
				"Choice 1", 
				"Choice 2", 
				"Choice 3"
		};
		
		// Create our text field and combo box objects
		JTextField textField = new JTextField();
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBox = new JComboBox(comboBoxChoices);
		comboBox.setSelectedIndex(0);
		
		
		// Create an array of Object objects
		Object[] objects = {
			"JComboBox Example",
			"Text Box: ", textField,
			"Combo Box: ", comboBox
		};
		
		// Display a JOptionPane dialog box with the text box and combo box
		// The variable choice just allows you to listen to the user's choice 
		int choice = JOptionPane.showConfirmDialog(null, objects, "Select an Option", JOptionPane.OK_CANCEL_OPTION);
		
		if (choice == JOptionPane.OK_OPTION) {
			String output = "Entered text: " + textField.getText() + "\n";
			output += "Selected item: " + comboBox.getSelectedItem().toString();
			
			JOptionPane.showMessageDialog(null, output);
		} else {
			// Add what you want to happen if the user selects Cancel
		}
	}

}
