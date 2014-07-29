import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class PINFrame1 extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = -11240989827984138L;
	
	private JTextField pinField = new JTextField(4);
    private JLabel pinLabel = new JLabel("PIN:");
    private String userPIN = "";
   
    public PINFrame1() {
        super("PIN Entry");
        setLayout(new FlowLayout());
        
        add(pinLabel);
        add(pinField);
        
        PINListener listener = new PINListener();
        pinField.addActionListener(listener);
    }
    
    private class PINListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            userPIN = pinField.getText();
            if (userPIN.equals("1234")) {
                JOptionPane.showMessageDialog(
                        PINFrame1.this,
                        "Correct PIN!");
            }
            else {
                JOptionPane.showMessageDialog(
                        PINFrame1.this,
                        "Sorry, try again.");
            }
        }
    }
}