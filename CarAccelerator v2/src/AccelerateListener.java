/**
 * 
 * @author BrianMcVeigh
 *
 */
// staticvoidgames.com/tutorials/swing/SwingTimers.jsp
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class AccelerateListener implements ActionListener {

	private int count = 0;
	
	private JLabel label;
	private Timer timer;
	
	public AccelerateListener(JLabel label) {
		this.label = label;
	}
	
	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		count++;
		label.setText("Count: " + count);
	}
	
}
