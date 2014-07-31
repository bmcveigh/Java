package main;


import java.awt.Color;
import java.awt.Container;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


import snake.SnakeGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
public class Main {
		
	public static JFrame main;
	static Container pane;
		
	public static void main(String [] args)
	{
		if(!FileSystem.Directory.exists())	{FileSystem.Directory.mkdirs();}	
		CreateFrame();
		FileSystem.InitConfig();
		Draw();
	}
	
	public static void CreateFrame() {
		main = new JFrame("Jframe Debugging");
		main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		main.addWindowListener(WindowL);
		main.setSize(FileSystem.width, FileSystem.height);
		main.setLocationRelativeTo(null);
		main.setResizable(false);
		pane = main.getContentPane();
		pane.setBackground(Color.BLACK);
		main.setVisible(true);
	}

	public static void AddButton(Size s, Location loc, String text, ActionListener al) {
		if(pane != null) {
			JButton b = new JButton(text);
			b.setBounds(loc.getX(), loc.getY(), s.getWidth(), s.getHeight());
			pane.add(b);
			main.repaint();
			b.addActionListener(al);
		}
	}
	static void exit(int Code) {
		if(Code > -1) //Config can be saved successfully
			FileSystem.SaveFiles();
		
		System.exit(Code);
	}
			
	public static void Draw() {
		pane.setBackground(Color.WHITE);
		Size s = new Size(150, 25);
		Location loc = new Location(20, 50);
		AddButton(s, loc, "Primzahl", Action);
		loc.setY(100);
		AddButton(s, loc, "Würfel", Action);
		loc.setY(150);
		AddButton(s, loc, "Snake", Action);
		//AddButton(new Size(150,50), new Location(250, 50), "<html>Neue Schaltfläche<br>hinzufügen</html>", Action);
		
		/*int num = 0;
		for(CustomButton cb : cbuttons) {
			AddButton(s, new Location(500, 50 + num * 50), cb.getText(), CustomAction);
			num++;
		}*/
	}
	
	static ActionListener Action = new ActionListener() { 
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() instanceof JButton) {
				JButton b = (JButton) e.getSource();
				if(b.getText().equals("Primzahl")) {
					PrimZahl();
				} else if(b.getText().equals("Würfel")) {
					Würfel();
				} else if(b.getText().equals("Snake")) {
					SnakeGame s = new SnakeGame(FileSystem.Snake_Highscore);
					s.OpenWindow();
				}
			}
		}
	}; 
	    
 
    public static void PrimZahl() {
    	InputDialog ID = new InputDialog("Primzahl", new String[] {"Hier können sie überprüfen ob ihre eingegebene Zahl\n" +
    									 "eine Primzahl ist!\n"+
    									 "Geben sie Zahl ein:"}, new String[] {"OK", "Abbrechen"});
    	
    	ID.show();
		int button = ID.GetClickedButtonIndex();
		String rawnum = ID.GetInputs()[0];
		if(button == 0) {
			try {
				int num = Integer.parseInt(rawnum);
				String ergebnis = num + " ist eine Primzahl!";
				if(num > 0) {
					for(int i = 1; i <= num; i++) {
						if(num % i == 0) {
							if(i != 1 && i != num) 
								ergebnis = num + " ist keine Primzahl!"; 
						}
					}
				} else {
					ergebnis = num + " ist keine Primzahl!";
				}
				MessageDialog MD = new MessageDialog("Ergebnis", ergebnis);
				MD.show();
				return;
			} catch(NumberFormatException e) {
				MessageDialog MD = new MessageDialog("Fehler", "Bitte geben sie eine gültige Zahl ein!", JOptionPane.ERROR_MESSAGE);
				MD.show();
				PrimZahl();
			}
		}  
    }

	public static void Würfel() {
		InputDialog ID = new InputDialog("Würfel", new String[] {"Mit diesem Würfel erzeugen sie eine zufällige Zahl\n" + 
										 "zwischen 1 und ihrer eingegebenden Zahl!\n" +
										 "Geben sie Zahl ein:"}, new String[] {"OK", "Abbrechen"});
		
		ID.show();
		int button = ID.GetClickedButtonIndex();
		String rawnum = ID.GetInputs()[0];
		if(button == 0) {
			try {
				int num = Integer.parseInt(rawnum);
				if(num <= 0) {
					MessageDialog MD = new MessageDialog("Fehler", "Bitte geben sie eine Zahl ein die größer als 0 ist!", JOptionPane.ERROR_MESSAGE);
					MD.show();
					Würfel();
				}
				Random r = new Random();
				int ausgabe = r.nextInt(num) + 1;
				MessageDialog MD = new MessageDialog("Ergebnis", "Die zufällig gewürfelte Zahl ist: " + ausgabe);
				MD.show();
			} catch(NumberFormatException e) {
				MessageDialog MD = new MessageDialog("Fehler", "Bitte geben sie eine Zahl ein die größer als 0 ist!", JOptionPane.ERROR_MESSAGE);
				MD.show();
				Würfel();
			}
		} 
	}
	
	static WindowListener WindowL = new WindowListener() {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			exit(0);
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		} 
		
	}; 
}
