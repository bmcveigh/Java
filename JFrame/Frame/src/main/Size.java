package main;

public class Size {

	private int width;
	private int height;
	
	public Size(int x, int y) {
		this.width = x;
		this.height = y;
	}
		
	public int getWidth() {	
		return this.width;
	}
	
	public int getHeight() {	
		return this.height;
	}
	
	public void setHeight(int newH) {
		this.height = newH;
	}
	
	public void setWidth(int newW) {
		this.width = newW;
	}
}
