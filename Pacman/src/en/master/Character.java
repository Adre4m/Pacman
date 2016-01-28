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

	public boolean move(char dir, char[][] lab) {
		switch (dir) {
		case 'u':
			if (lab[position.x - 1][position.y] != 'X' && lab[position.x - 1][position.y] != 'J') {
				lab[position.x - 1][position.y] = lab[position.x][position.y];
				lab[position.x--][position.y] = ' ';
				return true;
			}
			return false;
		case 'd':
			if (lab[position.x + 1][position.y] != 'X' && lab[position.x + 1][position.y] != 'J') {
				lab[position.x + 1][position.y] = lab[position.x][position.y];
				lab[position.x++][position.y] = ' ';
				return true;
			}
			return false;
		case 'l':
			if (lab[position.x][position.y + 1] != 'X' && lab[position.x][position.y + 1] != 'J') {
				lab[position.x][position.y + 1] = lab[position.x][position.y];
				lab[position.x][position.y++] = ' ';
				return true;
			}
			return false;
		case 'r':
			if (lab[position.x][position.y - 1] != 'X' && lab[position.x][position.y - 1] != 'J') {
				lab[position.x][position.y - 1] = lab[position.x][position.y];
				lab[position.x][position.y--] = ' ';
				return true;
			}
			return false;
		}
		return true;
	}
}
