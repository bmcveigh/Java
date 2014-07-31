import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author BrianMcVeigh
 *
 */
public class Main {
	private static Timer timer = new Timer(false);
	
	private static ReminderWindow window = new ReminderWindow();
	
	public static void schedule() {
	
		Calendar next = Calendar.getInstance(); // Set the time to the current time as a new Calendar object
		next.add(Calendar.HOUR, 1);
		
		window.setScheduledTimeString(next.getTime().toString()); // update the time for the window
		
		String nextReminderString = "Your next break is scheduled for " + next.getTime().toString(); // next.getTime().toString() gets the next scheduled break time
		
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // set it so the close button does not work (so user can control whether they want to be reminded)
		
		System.out.println(nextReminderString);
		
		// Every time the timer fires, it will display a dialog box informing the user to take their break.
		timer.schedule(new TimerTask() {
			
			public void run() {
				System.out.println("due");
				JOptionPane.showMessageDialog(null, "BREAK TIME :-)");
				schedule();
			}
		}, next.getTime());
	}
	
	public static void main(String[] args) {
		schedule();
	}
}
