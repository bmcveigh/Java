/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author brianmcveigh
 */

public class MainWindow extends JFrame {
    
	private static final long serialVersionUID = 1L;
	private JLabel rowsLabel;
    private JTextField rowsField;
    private JLabel colsLabel;
    private JTextField colsField;
    // Cell padding
    private JLabel cpLabel;
    private JTextField cpField;
    // Cell spacing
    private JLabel csLabel;
    private JTextField csField;
    // Border size
    private JLabel bsLabel;
    private JTextField bsField;
    private JButton submit;
    
    public MainWindow() {
        // Set up the window
        setSize(200, 400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize and add rows labels and fields
        rowsLabel = new JLabel("Enter # of rows: ");
        add(rowsLabel);
        rowsField = new JTextField(10);
        add(rowsField);
      
        // Initialize and add column labels and fields
        colsLabel = new JLabel("Enter # of columns: ");
        add(colsLabel);
        colsField = new JTextField(10);
        add(colsField);
        
         // Initialize and add cell padding labels and fields
        cpLabel = new JLabel("Enter Cell Padding #: ");
        add(cpLabel);
        cpField = new JTextField(10);
        add(cpField);
        
        // Initialize and add cell spacing labels and fields
        csLabel = new JLabel("Enter Cell Spacing #: ");
        add(csLabel);
        csField = new JTextField(10);
        add(csField);
        
        // Initialize and add border size labels and fields
        bsLabel = new JLabel("Enter Border Size #: ");
        add(bsLabel);
        bsField = new JTextField(10);
        add(bsField);
        
        submit = new JButton("Submit");
        submit.setOpaque(true);
        submit.setForeground(Color.blue);
        submit.setBackground(Color.red);
        add(submit);
        
        SubmitButtonListener sbListener = new SubmitButtonListener();
        submit.addActionListener(sbListener);
    }
    
    private class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int rows = Integer.parseInt(rowsField.getText());
            int cols = Integer.parseInt(colsField.getText());
            int bdrSize = Integer.parseInt(bsField.getText());
            int cellPadding = Integer.parseInt(cpField.getText());
            int cellSpacing = Integer.parseInt(csField.getText());
            
            PopulateCellsWindow w = new PopulateCellsWindow(rows, cols, bdrSize, cellPadding, cellSpacing);
            w.setVisible(true);
        }
        
    }
}
