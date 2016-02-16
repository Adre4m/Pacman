package en.master;

import java.awt.Point;

public abstract class Character {

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

	/**
	 * 
	 * @param dir is a char that can be : 'u' for "up", 'd' for "down", 'l' for "left", 'r' for "right"
	 * @param game where to set the changes
	 * @return a boolean : true if the character moved, false if he didn't.
	 */
	public boolean move(char dir, Game game) {
		switch (dir) {
		case 'u':
			if (game.getLab()[position.x - 1][position.y] != 'X' && game.getLab()[position.x - 1][position.y] != 'J') {
				if (this instanceof Pacman)
					instancePacman(position.x - 1, position.y, game);
				else
					instanceGhost(position.x - 1, position.y, game);
				return true;
			}
			return false;
		case 'd':
			if (game.getLab()[position.x + 1][position.y] != 'X' && game.getLab()[position.x + 1][position.y] != 'J') {
				if (this instanceof Pacman)
					instancePacman(position.x + 1, position.y, game);
				else
					instanceGhost(position.x + 1, position.y, game);
				return true;
			}
			return false;
		case 'r':
			if (game.getLab()[position.x][position.y + 1] != 'X' && game.getLab()[position.x][position.y + 1] != 'J') {
				if (this instanceof Pacman)
					instancePacman(position.x, position.y + 1, game);
				else
					instanceGhost(position.x, position.y + 1, game);
				return true;
			}
			return false;
		case 'l':
			if (game.getLab()[position.x][position.y - 1] != 'X' && game.getLab()[position.x][position.y - 1] != 'J') {
				if (this instanceof Pacman)
					instancePacman(position.x, position.y - 1, game);
				else
					instanceGhost(position.x, position.y - 1, game);
				return true;
			}
			return false;
		}
		return false;
	}

	private void makeMove(int x, int y, Game game) {
		char old = game.getLab()[x][y];
		game.getLab()[x][y] = game.getLab()[position.x][position.y];
		if (this instanceof Ghost) {
			game.getLab()[position.x][position.y] = ((Ghost) this).getOld();
			((Ghost) this).setOld(old);
		} else {
			game.getLab()[position.x][position.y] = ' ';
		}
		position.x = x;
		position.y = y;
	}

	private boolean instancePacman(int x, int y, Game game) {
		switch (game.getLab()[x][y]) {
		case 'G':
			((Pacman) this).death();
			game.setRestartNeed(true);
			return true;
		case 'V':
			Ghost g = game.getGhost(x, y);
			g.setVunerable(false);
			g.isEaten();
			game.setScore(game.getScore() + (200 * (int) Math.pow(2, ((Pacman) this).eatGhost())));
			scoreFruit(g.getOld(), game);
			g.setOld(' ');
			g.setReturningToJail(true);
			break;
		default:
			scoreFruit(game.getLab()[x][y], game);
		}
		makeMove(x, y, game);
		return false;
	}

	private void scoreFruit(char c, Game game) {
		switch (c) {
		case 'g':
			game.setScore(game.getScore() + 10);
			break;
		case 'S':
			game.setScore(game.getScore() + 50);
			((Pacman) this).eatSupergum();
			for (int i = 1; i < game.characters.length; ++i)
				((Ghost) (game.characters[i])).setVunerable(true);
			break;
		case 'C':
			game.setScore(game.getScore() + 100);
			break;
		case 's':
			game.setScore(game.getScore() + 300);
			break;
		case 'O':
			game.setScore(game.getScore() + 500);
			break;
		case 'A':
			game.setScore(game.getScore() + 700);
			break;
		case 'M':
			game.setScore(game.getScore() + 1000);
			break;
		case 'b':
			game.setScore(game.getScore() + 2000);
			break;
		case 'B':
			game.setScore(game.getScore() + 3000);
			break;
		case 'K':
			game.setScore(game.getScore() + 5000);
			break;
		}
	}

	private void instanceGhost(int x, int y, Game game) {
		makeMove(x, y, game);
		Ghost g = (Ghost) this;
		if (g.getOld() == 'P') {
			if (!g.isReturningToJail()) {
				((Pacman) game.characters[0]).death();
				game.setRestartNeed(true);
			} else
				game.getLab()[x][y] = 'P';
		} else {
			game.getLab()[x][y] = toChar();
		}
	}

	public abstract char toChar();
}
