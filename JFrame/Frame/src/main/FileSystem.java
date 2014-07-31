package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class FileSystem {

	public static String path = System.getenv("APPDATA") + "\\jframe\\";
	public static File Directory = new File(path);
	public static File Config = new File(path + "config.cfg");
	public static File Buttons = new File(path + "buttons.cfg");
	
	
	//stats	
	public static String password = "";
	public static int width = 800;
	public static int height = 500;
	//snake
	public static int Snake_Highscore = 0;
	
	static void InitConfig() {
		if(!Config.exists()) {
			try {	
				FileWriter fw = new FileWriter(Config);
				PrintWriter pw = new PrintWriter(fw);	
				pw.println("password ");	
				pw.println("scr_res_x " + width);
				pw.println("scr_res_y " + height);
				pw.println("snake_highscore " + Snake_Highscore);	
				pw.close();		
				Account.CreateAccount();
			} catch (IOException e) {
				MessageDialog msgbox = new MessageDialog("Fehler", "Die Konfigurationsdatei konnte nicht erstellt werden!\n"
													   + "Überprüfen sie ob der Ordner " + path + " existiert \n"
													   + "und ob Schreibrechte für diese Anwendung vorhanden sind!",
													   JOptionPane.ERROR_MESSAGE);			
				msgbox.show();	
				e.printStackTrace();
				Main.exit(-1);
			}		
		} else {
			try {
				FileReader fr = new FileReader(Config);
				BufferedReader br = new BufferedReader(fr);
	 			String line;
	 			String[] result;	 			
	 			do {
	 				line = br.readLine();
	 				if(line != null && line.length() > 0) {
	 					result = line.split(" ");
	 					if(result.length > 0) {
	 						switch(result[0]) {
	 							case "password" : {
	 								if(result.length >= 2) {
	 									password = result[1].substring(0, Base.clamp(16, 0, result[1].length()));
	 									System.out.println("Password: " + password);
	 								} 
	 							} break;
	 							case "scr_res_x" : {
	 								if(result.length >= 2) {
	 									width = Base.atoi(result[1]);
	 								} 
	 							} break;
	 							case "scr_res_y" : {
	 								if(result.length >= 2) {
	 									height = Base.atoi(result[1]);
	 								} 
	 							} break;
	 							case "snake_highscore": {
	 								if(result.length >= 2) {
	 									Snake_Highscore = Base.atoi(result[1]);
	 								} 
	 							}
	 						}
	 					}
	 					Main.main.setSize(width, height);
	 				}
	 			} while(line != null);
	 			br.close();
	 			Account.Login();
			} catch(NumberFormatException e) {
				Config.delete();
				InitConfig();
			} catch (Exception e) {
				MessageDialog msgbox = new MessageDialog("Fehler", "Die Konfigurationsdatei konnte nicht geladen werden!\n"
						   + "Überprüfen sie ob die Datei " + Config.getAbsolutePath() + " existiert \n"
						   + "und ob Leserechte für diese Anwendung vorhanden sind!",
						   JOptionPane.ERROR_MESSAGE);			
				msgbox.show();	
				e.printStackTrace();
				Main.exit(-1);
			}
		}
	}
	
	
	
	public static void SaveConfig() {
		try {
			FileWriter fw = new FileWriter(Config, false);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("password " + password);
			pw.println("scr_res_x " + width);
			pw.println("scr_res_y " + height);
			pw.println("snake_highscore " + Snake_Highscore);
			pw.close();
		} catch (IOException e) {
			MessageDialog msgbox = new MessageDialog("Fehler", "Die Konfigurationsdatei konnte nicht erstellt werden!\n"
					   + "Überprüfen sie ob der Ordner " + path + " existiert \n"
					   + "und ob Schreibrechte für diese Anwendung vorhanden sind!",
					   JOptionPane.ERROR_MESSAGE);			
			msgbox.show();	
			e.printStackTrace();
			Main.exit(-1);
		}	
	}


	public static void SaveFiles() {
		SaveConfig();
	}
}
