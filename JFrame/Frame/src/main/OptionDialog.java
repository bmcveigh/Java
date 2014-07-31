package main;

import javax.swing.JOptionPane;

public class OptionDialog {

	private String[] buttons;
	private String title;
	private String message;
	private int MessageType;
	
	public OptionDialog(String t, String msg, String[] b) {
		
		this.buttons = b;
		this.title = t;
		this.message = msg;	
		this.MessageType = JOptionPane.PLAIN_MESSAGE;
	}	
	public OptionDialog(String t, String msg, String[] b, int MT) {
		
		this.buttons = b;
		this.title = t;
		this.message = msg;
		this.MessageType = MT;
	}
	
	
	
	public int GetSelectedIndex() {	
		if(buttons != null && buttons.length > 0) {
			int selected = JOptionPane.showOptionDialog(null, message, title,
									    JOptionPane.DEFAULT_OPTION, 
		                                MessageType, 
									    null, buttons, buttons[0]);
			return selected;
		}
		return 0;
	}
	
	public String GetSelectedString() {
		if(buttons != null && buttons.length > 0) {
			int selected = JOptionPane.showOptionDialog(null, message, title,
									    JOptionPane.DEFAULT_OPTION, 
		                                MessageType, 
									    null, buttons, buttons[0]);
			if(selected >= 0 && selected <= buttons.length)
				return buttons[selected];
			else
				return "Exit";
		}
		return "";
	}
	
}
