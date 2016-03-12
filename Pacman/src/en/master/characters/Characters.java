package en.master.characters;

import java.awt.Point;

import en.master.Game;
import en.master.Timer;

public abstract class Characters {

	protected String sprite;
	protected Point position;
	protected char dir;
	private Point initialPosition;

	public Characters(String sprite) {
		position = new Point(0, 0);
		initialPosition = (Point) position.clone();
		dir = 'u';
		this.sprite = sprite;
	}

	public Characters(String sprite, int x, int y) {
		position = new Point(x, y);
		initialPosition = (Point) position.clone();
		dir = 'u';
		this.sprite = sprite;
	}

	public Point getPosition() {
		return position;
	}

	public Point getInitialPosition() {
		return initialPosition;
	}

	public void reinit(Game game) {
		dir = 'u';
		game.getLab()[position.x][position.y] = ' ';
		position.setLocation(initialPosition);
		game.getLab()[position.x][position.y] = toChar();
	}

	/**
	 * 
	 * @param game
	 *            where to set the changes
	 * @return a boolean : true if the character moved, false if he didn't.
	 */
	public boolean move(Game game) {
		if (canMove(game)) {
			int x = position.x, y = position.y;
			switch (dir) {
			case 'u':
				x = Math.floorMod(position.x - 1, game.getLab().length);
				break;
			case 'd':
				x = Math.floorMod(position.x + 1, game.getLab().length);
				break;
			case 'r':
				y = Math.floorMod(position.y + 1, game.getLab()[0].length);
				break;
			case 'l':
				y = Math.floorMod(position.y - 1, game.getLab()[0].length);
				break;
			}
			if (this instanceof Pacman)
				instancePacman(x, y, game);
			else
				instanceGhost(x, y, game);
			return true;
		} else
			return false;
	}

	private void makeMove(int x, int y, Game game) {
		char old = game.getLab()[x][y];
		game.getLab()[x][y] = toChar();
		if (this instanceof Ghost && old != 'G' && old != 'V' && old != 'E') {
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
			game.eatGum();
			break;
		case 'S':
			game.setScore(game.getScore() + 50);
			((Pacman) this).eatSupergum();
			for (int i = 1; i < game.characters.length; ++i) {
				((Ghost) (game.characters[i])).ill();
			}
			((Pacman) (game.characters[0])).setInvulnerable(Timer.SUPERGUM);
			game.eatGum();
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
			if (!g.isReturningToJail() && !g.isVulnerable()) {
				((Pacman) game.characters[0]).death();
				game.setRestartNeed(true);
			} else {
				game.getLab()[x][y] = 'P';
				game.setScore(game.getScore() + (200 * (int) Math.pow(2, ((Pacman) game.characters[0]).eatGhost())));
				g.setReturningToJail(true);
			}
			g.setOld(' ');
		} else {
			game.getLab()[x][y] = toChar();
		}
	}

	public abstract char toChar();

	public char getDir() {
		return dir;
	}

	public void setDir(char dir) {
		this.dir = dir;
	}

	private boolean canMove(Game game) {
		switch (dir) {
		case 'u':
			if (game.getLab()[Math.floorMod(position.x - 1, game.getLab().length)][position.y] == 'X')
				return false;
			else if (this instanceof Pacman
					&& game.getLab()[Math.floorMod(position.x - 1, game.getLab().length)][position.y] == 'D')
				return false;
			else
				return true;
		case 'd':
			if (game.getLab()[Math.floorMod(position.x + 1, game.getLab().length)][position.y] == 'X')
				return false;
			else if (this instanceof Pacman
					&& game.getLab()[Math.floorMod(position.x + 1, game.getLab().length)][position.y] == 'D')
				return false;
			else
				return true;
		case 'l':
			if (game.getLab()[position.x][Math.floorMod(position.y - 1, game.getLab()[0].length)] == 'X')
				return false;
			else if (this instanceof Pacman
					&& game.getLab()[position.x][Math.floorMod(position.y - 1, game.getLab()[0].length)] == 'D')
				return false;
			else
				return true;
		case 'r':
			if (game.getLab()[position.x][Math.floorMod(position.y + 1, game.getLab()[0].length)] == 'X')
				return false;
			else if (this instanceof Pacman
					&& game.getLab()[position.x][Math.floorMod(position.y + 1, game.getLab()[0].length)] == 'D')
				return false;
			else
				return true;
		}
		return false;
	}

	public String toString() {
		return position.toString();
	}

}
