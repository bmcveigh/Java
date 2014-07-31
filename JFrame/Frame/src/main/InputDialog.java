package main;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InputDialog {
	
	private String textfields[];
	private String title;
	private int MessageType;
	private String[] buttons;
	
	private int buttonclicked;
	private String[] inputs;
	
	public InputDialog(String t, String ins[]) {
		this.textfields = ins;
		this.title = t;
		this.MessageType = JOptionPane.PLAIN_MESSAGE;
		this.buttons = new String[] {"OK"};
	}
	
	public InputDialog(String t, String ins[], String[] b) {
		this.textfields = ins;
		this.title = t;
		this.MessageType = JOptionPane.PLAIN_MESSAGE;
		this.buttons = b;
	}
	
	public InputDialog(String t, String ins[], String[] b, int MT) {
		this.textfields = ins;
		this.title = t;
		this.MessageType = MT;
		this.buttons = b;
	}
	
	public void show() {
		
		Object[] objects = new Object[textfields.length * 2];
		JTextField[] fields = new JTextField[textfields.length];
		for(int i = 0; i < textfields.length; i++) {
			objects[i * 2] = textfields[i];
			fields[i] = new JTextField();
			objects[i * 2 + 1] = fields[i];				
		}	 
		 
		buttonclicked = JOptionPane.showOptionDialog(null, objects, title,
			    JOptionPane.DEFAULT_OPTION, 
                MessageType, 
			    null, buttons, buttons[0]);
		 
		 inputs = new String[textfields.length];
		 for(int i = 0; i <  fields.length; i++) {
			 inputs[i] = fields[i].getText();
		 }
	}
	
	public int GetClickedButtonIndex() {	
		return buttonclicked;
	}
	
	public String GetClickedButtonName() {
		if(buttonclicked >= 0 && buttonclicked <= buttons.length)
			return buttons[buttonclicked];
		else
			return "Exit";
	}
	
	public String[] GetInputs() {
		return inputs;
	}
}
