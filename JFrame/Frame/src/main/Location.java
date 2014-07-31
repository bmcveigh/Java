package main;

public class Location {

	private int x;
	private int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}	
	
	public int getX() {	
		return this.x;
	}
	
	public int getY() {	
		return this.y;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public Location GetRelativeLocation(int Dir) {
		int newx = this.x;
		int newy = this.y;
		
		int Direction = Dir;
		while(Direction > 360) {
			Direction -= 360; 
		}

		switch(Direction) {
			case 0: {newy++;} break; 
			case 90: {newx--;} break;
			case 180: {newy--;} break;
			case 270: {newx++;} break;
			case 360: {newy++;} break;
		}
		Location loc = new Location(newx, newy);
		return loc;
	}
}
