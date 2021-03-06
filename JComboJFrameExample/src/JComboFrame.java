

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 
 * @author BrianMcVeigh
 *
 */
public class JComboFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel titleLabel;
	
	private JTextField textField;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	
	private JButton submitButton;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboFrame() {
		
		setResizable(false);
		setSize(300, 200);
		setLayout(new GridLayout(4, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize and add text label to JFrame window
		titleLabel = new JLabel("JFrame combo box example");
		add(titleLabel);
		
		// Initialize and add text field to JFrame window
		textField = new JTextField(10);
		add(textField);
		
		// Create our array of combo box choices
		String[] comboBoxChoices = {
				"Select an option...",
				"Choice 1", 
				"Choice 2", 
				"Choice 3"
		};
		
		// Initialize and add combo box to JFrame window
		comboBox = new JComboBox(comboBoxChoices);
		add(comboBox);
		
	   // Initialize and add submit button to JFrame window. Add an ActionListener for the button
		submitButton = new JButton("Submit");
		ButtonListener bListener = new ButtonListener();
		submitButton.addActionListener(bListener);
		add(submitButton);
	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Info submitted\nText entered in text box: " + textField.getText() + 
					"\nSelected option: " + comboBox.getSelectedItem());
		}

	}

	
}
