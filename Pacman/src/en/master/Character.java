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

	/**
	 * 
	 * @param x
	 * @param y
	 * @param game
	 * @return true if something went wrong, like being eat.
	 */

	// TODO MOST IMPORTANT : dans le cas ou pacman mange un fantome lorsqu'il se
	// deplace le fantome devient des yeux, alors est-ce que le pacman gère ca
	// ou est-ce que c'est le fantome, qui, en se deplaçant s'il est mangé dira
	// qu'il devient des yeux ?
	private boolean instancePacman(int x, int y, Game game) {
		switch (game.getLab()[x][y]) {
		case 'G':
			// Case ghost
			if (game.getGhost(x, y).isVunerable()) {
				game.getGhost(x, y).setVunerable(false);
				game.getGhost(x, y).isEaten();
				game.setScore(game.getScore() + (200 * (int) Math.pow(2, ((Pacman) this).eatGhost())));
				return false;
			} else {
				((Pacman) this).death();
				return true;
			}
		case 'g':
			// Case gum
			game.setScore(game.getScore() + 10);
			break;
		case 'S':
			// Case super gum
			game.setScore(game.getScore() + 50);
			((Pacman) this).eatSupergum();
			for (int i = 1; i < game.characters.length; ++i)
				((Ghost) (game.characters[i])).setVunerable(true);
			break;
		case 'C':
			// Case cherry
			game.setScore(game.getScore() + 100);
			break;
		case 's':
			// Case strawberry
			game.setScore(game.getScore() + 300);
			break;
		case 'O':
			// Case orange
			game.setScore(game.getScore() + 500);
			break;
		case 'A':
			// Case apple
			game.setScore(game.getScore() + 700);
			break;
		case 'M':
			// Case melon
			game.setScore(game.getScore() + 1000);
			break;
		case 'b':
			// Case galboss
			game.setScore(game.getScore() + 2000);
			break;
		case 'B':
			// Case bell
			game.setScore(game.getScore() + 3000);
			break;
		case 'K':
			// Case key
			game.setScore(game.getScore() + 5000);
			break;
		}
		// If none of the case above where used, than the default case (useless
		// to write it, because it's empty) is when you step on an empty space
		// or when you step on eye-form ghost.
		return false;
	}

	private void inAnyCase(int x, int y, Game game) {
		if (this instanceof Pacman)
			if (instancePacman(x, y, game))
				return;
		// TODO gérer si le fantôme mange pacman
		// TODO j'ai une idee, qui consiste à si la case suivante est le pacman
		// ne pas la remplacé mais sinon le fantome gere quelle lettre sera sur
		// la case. s'il est mangé il mettra un E a la place d'un G
		char old = game.getLab()[x][y];
		game.getLab()[x][y] = game.getLab()[position.x][position.y];
		game.getLab()[position.x][position.y] = game.getTopStack(this);
		position.x = x;
		position.y = y;
		if (!(this instanceof Pacman))
			game.setTopStack(this, old);
	}
}
