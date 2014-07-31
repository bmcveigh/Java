package snake;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

import main.Location;

public class Powerup extends JPanel {


	private static final long serialVersionUID = 1L;
	public static BufferedImage img;
	
	private Location loc;
	private Background container;

	
	public Powerup() {
		this.container = SnakeGame.bg;
		spawn();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g); 		
		g.drawImage(img, 0, 0, null);
	}
	
	public void spawn() {
		Random r = new Random();			
		int x = r.nextInt(container.GetSize().getWidth() / container.GetGridSize().getWidth());
		int y = r.nextInt(container.GetSize().getHeight() / container.GetGridSize().getHeight());
		Location loc = new Location(x, y);
		do {
			x = r.nextInt(container.GetSize().getWidth() / container.GetGridSize().getWidth());
			y = r.nextInt(container.GetSize().getHeight() / container.GetGridSize().getHeight());
			loc = new Location(x, y);
		} while(container.GetSnake(loc) != null);	
		draw(loc);
		this.loc = loc;
	}
	
	public void draw(Location loc) {
		setBounds(loc.getX() * img.getWidth(), loc.getY() * img.getHeight(), img.getWidth(), img.getHeight());
		container.add(this);
		container.repaint();
	}
	
	public Location GetLocation() {
		return this.loc;
	}
	
	public void remove() {
		for(Component c : container.getComponents()) {
			if(c instanceof JPanel && c == this) {
				container.remove(c);
			}
		}
	}
}
