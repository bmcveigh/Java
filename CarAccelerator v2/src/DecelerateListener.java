
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class DecelerateListener implements ActionListener {

	private int count = 0;
	
	private JLabel label;
	private Timer timer;
	
	public DecelerateListener(JLabel label, Timer timer) {
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
	public void actionPerformed(ActionEvent e) {
		timer.stop();
		if (count > 0) {
			count--;
			label.setText("Count: " + count);
		}
	}
	
}
