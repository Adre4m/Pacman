package en.master;

import java.awt.Point;

public class Character {

	protected Point position;
	
	public Character() {
		position = new Point(0, 0);
	}
	
	public Character(int x, int y) {
		position = new Point(x, y);
	}
	
	public Point getPosition() {
		return position;
	}
	
	public boolean move() {
		return true;
	}
}
