package main;

import javax.swing.JOptionPane;

public class Account {

	public static int tries = 4;
	
	public static void Login() {
		InputDialog ID = new InputDialog("Einloggen", new String[] {"Passwort:"}, new String[] {"OK", "Beenden"});
		ID.show();
		String[] result = ID.GetInputs();
		String pw = result[0];
		int clicked = ID.GetClickedButtonIndex();
		if(clicked == -1 || clicked == 1)
			Main.exit(1);
		if(pw.length() > 0) {
			if(!pw.equals(FileSystem.password)) {
				MessageDialog msgbox = new MessageDialog("Erfolgreich", "Erfolgreich eingeloggt!");
				msgbox.show();
				return;
			} else {				
				tries--;
				if(tries == 0) {
					MessageDialog msgbox = new MessageDialog("Zugriff verweigert", "Sie haben zu oft das falsche Passwort eingegeben!\n"
														   + "Das Programm wird jetzt beendet!",
														   JOptionPane.WARNING_MESSAGE);	
					msgbox.show();
					Main.exit(1);
				} else {		
					System.out.println("Password: " + pw);
					MessageDialog msgbox = new MessageDialog("Zugriff verweigert", "Sie haben ein falsches Passwort eingegeben!\n"
														   + "Übrige Versuche: " + tries + "/3",
														   JOptionPane.WARNING_MESSAGE);			
					msgbox.show();
					Login();
					return;
				}
			}
		}
		MessageDialog msgbox = new MessageDialog("Fehler", "Bitte lassen Sie keine Felder leer!",
												JOptionPane.ERROR_MESSAGE);	
		msgbox.show();
		Login();	
		return;
	}
	
	public static void CreateAccount() {		
		InputDialog ID = new InputDialog("Neuer Account", new String[] {"Passwort"}, new String[] {"OK", "Beenden"});
		ID.show();
		String[] result = ID.GetInputs();
		int clicked = ID.GetClickedButtonIndex();
		if(clicked == -1 || clicked == 1)
			Main.exit(1);
		String pw = result[0];
		if(pw.length() >= 3 && pw.length() <= 16) {			
			//check if its clean
			String chars = "abcdefghijklmnopqrstuvwxyz123456789";	
			for(char c : pw.toCharArray()) {
				boolean included = false;
				for(char cc : chars.toCharArray()) {
					if(Character.toLowerCase(c) == cc)
							included = true;
					}
					if(!included) {
						MessageDialog msgbox = new MessageDialog("Fehler", "Ihre Eingabe(n) enthalten ungültige Zeichen!\n"
								   + "Bitte verwenden sie nur folgende Zeichen:\n"
								   + chars,
								   JOptionPane.ERROR_MESSAGE);			
						msgbox.show();	
						CreateAccount();
						return;
					}
				}
			MessageDialog msgbox = new MessageDialog("Erfolgreich", "Ihr Account wurde erfolgreich erstellt!");			
			msgbox.show();	
			FileSystem.password = pw;
			return;
		}
		MessageDialog msgbox = new MessageDialog("Fehler", "Die Passwortlänge muss zwichen 3 und 16 Zeichen sein!",
				   JOptionPane.ERROR_MESSAGE);			
		msgbox.show();	
		CreateAccount();
	}
	
}
