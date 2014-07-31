package snake;

import java.awt.image.BufferedImage;

import java.util.HashMap;
import java.util.Map;


import main.Location;
import main.Size;


public class Snake {

	public static BufferedImage body;
	public static BufferedImage head;
	
	private HashMap<SnakeTile, Location> pos;
	private int Direction;	

	
	public Snake(int Dir, Location s) { //Dir: 0 UP 90 RIGHT 180 DOWN 270 LEFT 360 UP	
		Direction = Dir;
		pos = new HashMap<SnakeTile, Location>();
		pos.put(new SnakeTile(new Size(head.getWidth(), head.getHeight()), head, SnakeGame.bg, Dir), s); //Head with Index 0 at Spawn
		pos.put(new SnakeTile(new Size(body.getWidth(), body.getHeight()), body, SnakeGame.bg, Dir), s.GetRelativeLocation(Dir + 180)); //Body with Index 1 and
		pos.put(new SnakeTile(new Size(body.getWidth(), body.getHeight()), body, SnakeGame.bg, Dir), s.GetRelativeLocation(Dir + 180).GetRelativeLocation(Dir + 180)); //Body with Index 1 and
		//pos.put(new SnakeTile(new Size(body.getWidth(), body.getHeight()), body, SnakeGame.bg, Dir), s.GetRelativeLocation(Dir).GetRelativeLocation(Dir).GetRelativeLocation(Dir)); //Body with Index 1 and
		draw();
	}
	
	public void draw() {
		for(Map.Entry<SnakeTile, Location> part : pos.entrySet()) {
			part.getKey().draw(part.getValue());
		}
	}
	
	public void Move(int d) {
		HashMap<SnakeTile, Location> newpos = new HashMap<SnakeTile, Location>();
		boolean grow = false;
		
		for(Map.Entry<SnakeTile, Location> part : pos.entrySet()) {
			SnakeTile t = part.getKey();
			Location loc = part.getValue();
			if(t.GetImage() == head) {
				if(t.isWall(loc.GetRelativeLocation(d))) { //Wand in Richtung des Kopfes?
					SnakeGame.Loose("Du bist gegen eine Wand gekracht!");
					return;
				} else if(SnakeGame.bg.GetSnake(loc.GetRelativeLocation(d)) != null) { //Schlangenteile in Richtung des Kopfes?
					SnakeGame.Loose("Du hast dich selbst gefressen!");
					return;
				} else if(SnakeGame.bg.IsPowerUp(loc.GetRelativeLocation(d))) { //Powerup in Richtung des Kopfes?
					SnakeGame.power.remove();
					SnakeGame.power = null;
					SnakeGame.SetScore(SnakeGame.score + 1);
					grow = true;
				}
				newpos.put(new SnakeTile(new Size(head.getWidth(), head.getHeight()), head, SnakeGame.bg, d), loc.GetRelativeLocation(d));
			} else { 
				
				Location loc2 = loc.GetRelativeLocation(t.GetDirection() + 180); //Tile Genau hinter dem aktuellen Teil
				if(SnakeGame.bg.GetSnake(loc2) == null && grow) { //Freier Raum am Ende der Schlange?
					newpos.put(new SnakeTile(new Size(body.getWidth(), body.getHeight()), 
							body,
							SnakeGame.bg,
							SnakeGame.bg.GetSnake(loc).GetDirection())
					, loc);
				}
					newpos.put(new SnakeTile(new Size(body.getWidth(), body.getHeight()), 
							body,
							SnakeGame.bg,
							SnakeGame.bg.GetSnake(loc).GetDirection())
					, loc.GetRelativeLocation(t.GetDirection()));
				//} //else {
				//	newpos.put(new SnakeTile(new Size(body.getWidth(), body.getHeight()), body, SnakeGame.bg, d), loc2);
				//}
				
			}
		}
		
		remove();
		this.pos.clear();
		this.pos = new HashMap<SnakeTile, Location>(newpos);
		draw();
		this.Direction = d;
	}
	
	public void remove() {
		for(Map.Entry<SnakeTile, Location> part : pos.entrySet()) {
			part.getKey().remove();
		}
	}	
	public int GetDirection() {
		return Direction;
	}
	
	public void SetDirection(int Dir) {
		if(Dir % 90 == 0) {
			this.Direction = Dir;
		}
	}
	
	public int GetSnakeLength() {
		return pos.size();
	}
	
	public HashMap<SnakeTile, Location> GetSnake() {
		return this.pos;
	}
}
