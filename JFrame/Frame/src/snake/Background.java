package snake;


import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.JPanel;

import main.Location;
import main.Size;

public class Background extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static BufferedImage img;
	private Location loc;
	private Size size;
	private Container container;
	private Size gridsize;
	
	public Background(Location l, Size s) {
		this.loc = l;
		this.size = s;
		this.container = SnakeGame.pane;
		gridsize = new Size(img.getWidth(), img.getHeight());
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g); 
		for(int x = 0; x < (size.getWidth() / img.getWidth()); x++) {
			for(int y = 0; y < (size.getHeight() / img.getHeight()); y++) {
				g.drawImage(img, x * img.getWidth(),  y * img.getHeight(), this);
			}
		}
	}
	
	public void draw() {
		setBounds(loc.getX(), loc.getY(), size.getWidth(), size.getHeight());
		container.add(this);
		container.repaint();
	}

	public Size GetGridSize() {
		return gridsize;		
	}
	
	public Size GetSize() {
		return size;
	}
	
	public SnakeTile GetSnake(Location l) {
		if(SnakeGame.snake != null) {
			for(Map.Entry<SnakeTile, Location> part : SnakeGame.snake.GetSnake().entrySet()) {
				Location loc = part.getValue();
			    if(l.getX() == loc.getX() && l.getY() == loc.getY())
			    	return part.getKey();
			}
		}
		return null;
	}
	
	public boolean IsPowerUp(Location loc) {
		if(SnakeGame.power != null) {
			Location l = SnakeGame.power.GetLocation();
			if(l.getX() == loc.getX() && l.getY() == loc.getY()) {
				return true;
			}
		}
		return false;
	}
}
