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

	public boolean move(char dir, Game game) {
		switch (dir) {
		case 'u':
			if (game.getLab()[position.x - 1][position.y] != 'X' && game.getLab()[position.x - 1][position.y] != 'J') {
				inAnyCase(position.x - 1, position.y, game);
				return true;
			}
			return false;
		case 'd':
			if (game.getLab()[position.x + 1][position.y] != 'X' && game.getLab()[position.x + 1][position.y] != 'J') {
				inAnyCase(position.x + 1, position.y, game);
				return true;
			}
			return false;
		case 'r':
			if (game.getLab()[position.x][position.y + 1] != 'X' && game.getLab()[position.x][position.y + 1] != 'J') {
				inAnyCase(position.x, position.y + 1, game);
				return true;
			}
			return false;
		case 'l':
			if (game.getLab()[position.x][position.y - 1] != 'X' && game.getLab()[position.x][position.y - 1] != 'J') {
				inAnyCase(position.x, position.y - 1, game);
				return true;
			}
			return false;
		}
		return true;
	}

	private boolean instancePacman(int x, int y, Game game) {
		if (game.getLab()[x][y] == 'G') {
			if (game.getGhost(x, y).isVunerable()) {
				return false;
			} else {
				((Pacman) this).death();
				return true;
			}
		} else if (game.getLab()[x][y] == 'g')
			game.setScore(game.getScore() + 10);
		else if (game.getLab()[x][y] == 'S') {
			game.setScore(game.getScore() + 50);
			for (int i = 1; i < game.characters.length; ++i)
				((Ghost) (game.characters[i])).setVunerable(true);
		}
		return false;
	}

	private void inAnyCase(int x, int y, Game game) {
		if (this instanceof Pacman)
			if(instancePacman(x, y, game))
				return;
		char old = game.getLab()[x][y];
		game.getLab()[x][y] = game.getLab()[position.x][position.y];
		game.getLab()[position.x][position.y] = game.getTopStack(this);
		position.x = x;
		position.y = y;
		if (!(this instanceof Pacman))
			game.setTopStack(this, old);
	}
}
