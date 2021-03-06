import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Window.java
 * @author BrianMcVeigh
 * @date Jul 29, 2014
 * @time 1:36:52 PM
 */
public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextArea results;
	private JLabel words;
	private JLabel chars;
	private JButton submit;
	
	public Window() 
	{
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setBackground(Color.green);
				
		results = new JTextArea(12, 20);
		results.setEditable(true);
		add(results);
		
		submit = new JButton("Submit");
		add(submit);
		ButtonListener listener = new ButtonListener();
		submit.addActionListener(listener);
		
		words = new JLabel("Word Count: 0");
		add(words);
		
		chars = new JLabel("Character count: 0");
		add(chars);
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] wordsArray = results.getText().split(" ");
			String[] newLineWordsArray = results.getText().split("\n");

			if (wordsArray.length == 1) {
				newLineWordsArray = new String[0];
			}
			
			int numberOfWords = wordsArray.length + newLineWordsArray.length - 1;
			words.setText("Word Count: " + numberOfWords);
			
			int numberOfCharacters  = results.getText().length();
			chars.setText("Character Count: " + numberOfCharacters);
			
		}
		
	}
}

