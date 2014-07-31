package snake;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import main.Location;
import main.Size;

public class SnakeTile extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	private Size size;
	private Container container;
	private int Direction;
	
	public SnakeTile(Size s, BufferedImage i, Container c, int Dir) {
		this.img = i;
		this.size = s;
		this.container = c;
		this.Direction = Dir;
	}
	
	public SnakeTile(Size s, BufferedImage i, Container c) {
		this.img = i;
		this.size = s;
		this.container = c;
		this.Direction = 0;
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g); 		
		if(Direction != 90) {
	        AffineTransform at = new AffineTransform();
	        at.translate(getWidth() / 2, getHeight() / 2);
	        at.rotate(Math.toRadians(Direction - 90));
	        at.translate(-img.getWidth()/2, -img.getHeight()/2);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.drawImage(img, at, null);
		} else {
			g.drawImage(img, 0, 0, null);
		}
	}
	
	public void draw(Location loc) {
		setBounds(loc.getX() * img.getWidth(), loc.getY() * img.getHeight(), size.getWidth(), size.getHeight());
		container.add(this);
		container.repaint();
	}
	
	public void remove() {
		for(Component c : container.getComponents()) {
			if(c instanceof JPanel && c == this) {
				container.remove(c);
			}
		}
	}	
	
	public BufferedImage GetImage() {
		return img;	
	}
	
	public int GetDirection() {
		return Direction;
	}
	
	public boolean isWall(Location loc) {
		int x1 = container.getLocation().x;
		int x2 = x1 + SnakeGame.bg.GetSize().getWidth();
		int y1 = container.getLocation().y;
		int y2 = y1 + SnakeGame.bg.GetSize().getHeight();
		
		int locx = loc.getX() * img.getWidth() + container.getLocation().x;
		int locy = loc.getY() * img.getHeight() + container.getLocation().y;
		
		if(locx < x1 || locx >= x2 || locy < y1 || locy >= y2) {
			return true;
		}
		return false;
	}
	
	
}
