import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author BrianMcVeigh
 *
 */
public class ReminderWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel timeLabel;
	
	private JButton stopButton;
	
	private String scheduledTimeString;
	
	public ReminderWindow() {
		// Configure the window
		setLayout(new FlowLayout()); // set the layout of the window
		setSize(300,100); // set the size of the window
		setResizable(false); // set the window to not be resizeable (this is particularly useful for a Flow Layout)
		setVisible(true); // set the window to be visible
		setLocationRelativeTo(null); // place the window in the center of the screen
		
		timeLabel = new JLabel(scheduledTimeString);
		add(timeLabel);
		
		stopButton = new JButton("Stop Reminding Me");
		add(stopButton);
		
		stopButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0); // terminate the application
			}
			
		});
		
	}

	/**
	 * @return the scheduledTimeString
	 */
	public String getScheduledTimeString() {
		return scheduledTimeString;
	}

	/**
	 * @param scheduledTimeString the scheduledTimeString to set
	 */
	public void setScheduledTimeString(String scheduledTimeString) {
		timeLabel.setText("Next Reminder: " + scheduledTimeString);
	}
	
	
}
