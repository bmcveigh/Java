

/*
 * Developed by Brian McVeigh
 * George Mason University
 * December 8, 2012
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @project SuperVendingMachine v4
 * @file VendingMachineApp.java
 * @author BrianMcVeigh
 * @date Jul 29, 2014
 * @time 1:42:31 PM
 */
public class VendingMachineApp extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel buttonsPanel;
	private JButton button1;
	private JButton button2;
	
	private static VendingMachine selectedVendingMachine; // we only want to work with 1 vending machine at a time. This is the reason for the static.
	
	public VendingMachineApp() {
		// Configure the window
		setLayout(new FlowLayout());
		setSize(300,320);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null); // place the window in the center of the screen
		
		// Add a title label
		add(new JLabel("What machine would you like to purchase from?"));
		
		// Configure and add the buttons panel
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(2, 1));
		buttonsPanel.setPreferredSize(new Dimension(300, 290));
		add(buttonsPanel);
		
		// Configure and add the coke button
		button1 = new JButton("Coke");
        button1.setBackground(Color.RED);
        button1.setForeground(Color.WHITE);
        buttonsPanel.add(button1);
        
        // Configure and add the Pepsi button
        button2 = new JButton("Pepsi");
        button2.setBackground(Color.BLUE);
        button2.setForeground(Color.WHITE);
        buttonsPanel.add(button2);
        
        // Add action listeners to the buttons
        Button1Listener oneListener = new Button1Listener();
        button1.addActionListener(oneListener);
        
        Button2Listener twoListener =new Button2Listener();
        button2.addActionListener(twoListener);
        
	}
	
	// This is the class for the coke vending machine
	private class Button1Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("Coke button clicked");
			selectedVendingMachine = new VendingMachine(VendingMachine.COKE_MACHINE);
			selectedVendingMachine.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			//dispose(); // close the window because we don't need it anymore.
			
		}
		
	}
	
	public static VendingMachine getSelectedVendingMachine() {
		return selectedVendingMachine;
	}
	
	// This is the class for the pepsi machine
	private class Button2Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Pepsi button clicked");
			selectedVendingMachine = new VendingMachine(VendingMachine.PEPSI_MACHINE);
			selectedVendingMachine.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			//dispose(); // close the window because we don't need it anymore.
		}
		
	}
	
    public static void main(String[] args) 
    {
        JOptionPane.showMessageDialog(null, "Super Vending Machine\nCopyright 2014 Brian McVeigh");
    	VendingMachineApp app = new VendingMachineApp();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } //end
       

} //end class
