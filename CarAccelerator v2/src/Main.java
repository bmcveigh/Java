
import java.awt.FlowLayout;
	
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Counter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		JButton button = new JButton("Click me!");
		frame.add(button);
		
		JButton button2 = new JButton("Decelerate");
		frame.add(button2);
		
		JLabel label = new JLabel("Count: 0");
		frame.add(label);
	
		// Pass the label into the MyListener constructor
		AccelerateListener listener = new AccelerateListener(label);
		// add the action listener to the JButton
		button.addActionListener(listener);
		
		// the timer fires every 1000 MS (1 second)
		// when it does, it calls the actionPerformed() method of MyListener
		Timer timer = new Timer(200, listener);
		listener.setTimer(timer);
		timer.start();
		
		frame.setSize(225, 100);
		frame.setVisible(true);
	}

}
