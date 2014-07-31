package main;

import java.awt.Component;

import javax.swing.JOptionPane;

public class MessageDialog {

	private String title;
	private String message;
	private int MessageType;
	private Component frame;
	
	public MessageDialog(String t, String msg) {
		this.title = t;
		this.message = msg;
		this.MessageType = JOptionPane.PLAIN_MESSAGE;
		frame = Main.main;
	}
	
	public MessageDialog(String t, String msg, int MT) {
		this.title = t;
		this.message = msg;
		this.MessageType = MT;
		frame = Main.main;
	}
	
	public MessageDialog(String t, String msg, int MT, Component f) {
		this.title = t;
		this.message = msg;
		this.MessageType = MT;
		this.frame = f;
	}
	
	public void show() {
		JOptionPane.showMessageDialog(frame , message, title, MessageType);
	}

}
