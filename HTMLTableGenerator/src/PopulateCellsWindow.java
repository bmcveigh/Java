/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author brianmcveigh
 */
public class PopulateCellsWindow extends JFrame {
    
		private static final long serialVersionUID = 1L;
		
		public static final int TXT_FIELD_LENGTH = 200;
        private int m_rows;
        private int m_cols;
        private int m_bdrSize;
        private int m_cellPadding;
        private int m_cellSpacing;
        private JTextField[] fields;
        private String htmlString;
        private JButton submit;
        private BufferedWriter writer;
        
        public PopulateCellsWindow(int rows, int cols, int bdrSize, int cellPadding, int cellSpacing){
            // Set the global variables to what was passed in
            m_rows = rows;
            m_cols = cols;
            m_bdrSize = bdrSize;
            m_cellPadding = cellPadding;
            m_cellSpacing = cellSpacing;
            
            // Set up the window
            setSize(TXT_FIELD_LENGTH*cols, TXT_FIELD_LENGTH*rows);
            setLayout(new FlowLayout());
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
           
            // The number of fields to add to the window is the number of rows times the number of columns
            int numFields = rows * cols;
            
            // Allocate room in memory for the # fields to be added
            fields = new JTextField[numFields];
            
            for (int i = 0; i < numFields; i++) {
                String s = "Field #" + (i + 1);
                fields[i] = new JTextField(s, 10);
                add(fields[i]);
            }
            submit = new JButton("Create Table");
            SubmitButtonListener sbListener = new SubmitButtonListener();
            submit.addActionListener(sbListener);
            add(submit);
         
        }
        
        private class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            createTable();
        }
            
        }
        
        public void createTable() {
            htmlString = "";
            htmlString += "<table border='"+m_bdrSize+"' cellpadding='" + m_cellPadding + "' cellspacing='" + m_cellSpacing + "'>\r";
            
            // Header
            htmlString += "\t<thead>\r\t\t<tr>";
            for (int i = 0; i < m_cols; i++) {
                htmlString += "\t\t\t<th>" + fields[i].getText() + "</th>\r";
            }
            htmlString += "\t\t</tr>\r\t</thead>\r";
            
            // Body
            htmlString += "\t<tbody>\r";
            for (int i = 0; i < m_rows-1; i++) { // it is m_rows-1 because we do not want to include the header
                htmlString += "\t<tr>\r";
                for (int j = 0; j < m_cols; j++) {
                    int indexAt = j + m_cols;
                    htmlString += "\t\t<td>" + fields[indexAt].getText() + "</td>\r";
                }
                htmlString += "\t\t</tr>\r";
            }
            htmlString += "</tbody>\r</table>";
            System.out.print(htmlString);
            
            // Write a file
            JFileChooser chooser = new JFileChooser();
            chooser.showSaveDialog(null);
            File file = chooser.getSelectedFile();
            
            try {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(htmlString);
                JOptionPane.showMessageDialog(this, "Message saved. (" + file.getName()+")",
                "ImPhil HTML Editer - Page Saved",
                JOptionPane.INFORMATION_MESSAGE);
                writer.close();
            }       
            catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        }
        
        
    
}
