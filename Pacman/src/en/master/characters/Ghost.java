package en.master.characters;

import java.awt.Point;
import java.util.Stack;

import en.master.Game;
import en.master.Timer;
import en.master.graph.Node;

public abstract class Ghost extends Characters {

	private static final int RADIUS = 8;
	protected boolean isVulnerable;
	protected boolean isFree;
	protected boolean isReturningToJail;
	protected long jailed;
	protected long vulnerable;
	protected char old;
	protected String vulnerableSprite;
	protected String eyeSprite;
	private Stack<Character> directions;
	// private int speed;

	public Ghost(String sprite) {
		super(sprite);
		vulnerableSprite = "Blue_ghost.gif";
		eyeSprite = "Ghost_eyes";
		isVulnerable = false;
		isReturningToJail = false;
		jail();
		directions = new Stack<Character>();
	}

	public Ghost(String sprite, int x, int y) {
		super(sprite, x, y);
		vulnerableSprite = "Blue_ghost.gif";
		eyeSprite = "Ghost_eyes";
		isVulnerable = false;
		isReturningToJail = false;
		directions = new Stack<Character>();
		jail();
	}

	public boolean isVulnerable() {
		return isVulnerable;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public boolean isReturningToJail() {
		return isReturningToJail;
	}

	public void setReturningToJail(boolean isReturningToJail) {
		isVulnerable = false;
		vulnerable = -1;
		this.isReturningToJail = isReturningToJail;
	}

	// TODO IA
	// trois fonctions de l'ia, la fonction dans le cas ou le fantome est mangé
	// et ou il chasse sont communes

	public void ia(Game game) {
		if (isFree) {
			Point pacman;
			if (isReturningToJail) {
				isEaten(game);
			} else if ((pacman = radius(game)) != null) {
				chased(pacman, game);
			} else {
				patrol();
			}
			move(game);
		}
	}

	private void isEaten(Game game) {
		if (directions.isEmpty()) {
			for (Node n = game.getGraph().reach(game.getJailWall(), position, this); n != null; n = n.getFather()) {
				directions.push(n.getDirection());
			}
		}
		dir = directions.pop();
		if (directions.isEmpty())
			isReturningToJail = false;
	}

	protected abstract void patrol();

	private void chased(Point pacman, Game game) {
		if (isVulnerable) {

		} else {
			//System.out.println(pacman);
			for (Node n = game.getGraph().reach(pacman, position, this); n != null; n = n.getFather()) {
				directions.push(n.getDirection());
			}
			//System.out.println(directions);
			dir = directions.pop();
		}
	}

	public char getOld() {
		return old;
	}

	public void setOld(char old) {
		this.old = old;
	}

	public char toChar() {
		if (isVulnerable)
			return 'V';
		else if (isReturningToJail)
			return 'E';
		else
			return 'G';
	}

	public long getJailed() {
		return jailed;
	}

	public void jail() {
		jailed = Timer.PRISON;
		isFree = false;
	}

	public void release() {
		jailed--;
		if (0 < jailed)
			isFree = false;
		else
			isFree = true;
	}

	public long getVulnerable() {
		return vulnerable;
	}

	public void ill() {
		vulnerable = Timer.SUPERGUM;
		this.isVulnerable = true;
	}

	public void heal() {
		vulnerable--;
		if (0 < vulnerable)
			this.isVulnerable = true;
		else
			this.isVulnerable = false;
	}

	public String getVulnerableSprite() {
		return vulnerableSprite;
	}

	public void setVulnerableSprite(String vulnerableSprite) {
		this.vulnerableSprite = vulnerableSprite;
	}

	public String getEyeSprite() {
		return eyeSprite;
	}

	public void setEyeSprite(String eyeSprite) {
		this.eyeSprite = eyeSprite;
	}

	public void reinit(Game game) {
		vulnerableSprite = "Blue_ghost.gif";
		eyeSprite = "Ghost_eyes";
		isVulnerable = false;
		isFree = false;
		isReturningToJail = false;
		jailed = Timer.PRISON;
		super.reinit(game);
	}

	// D'apres l'algorithme de tracé de cercle d'Andres
	// https://fr.wikipedia.org/wiki/Algorithme_de_trac%C3%A9_de_cercle_d%27Andres
	public Point radius(Game game) {
		for (int r = 1; r <= RADIUS; ++r) {
			int x = 0;
			int y = r;
			int d = r - 1;
			while (x <= y) {
				if ((x + position.x) < game.getLab().length && (y + position.y) < game.getLab()[0].length)
					if (game.getLab()[x + position.x][y + position.y] == 'P')
						return new Point(x + position.x, y + position.y);
				if ((y + position.x) < game.getLab().length && (x + position.y) < game.getLab()[0].length)
					if (game.getLab()[y + position.x][x + position.y] == 'P')
						return new Point(y + position.x, x + position.y);
				if (0 <= (position.x - x) && 0 <= (position.y - y))
					if (game.getLab()[position.x - x][position.y - y] == 'P')
						return new Point(position.x - x, position.y - y);
				if (0 <= (position.x - y) && 0 <= (position.y - x))
					if (game.getLab()[position.x - y][position.y - x] == 'P')
						return new Point(position.x - y, position.y - x);
				if ((x + position.x) < game.getLab().length && 0 <= (position.y - y))
					if (game.getLab()[x + position.x][position.y - y] == 'P')
						return new Point(x + position.x, position.y - y);
				if ((y + position.x) < game.getLab().length && 0 <= (position.y - x))
					if (game.getLab()[y + position.x][position.y - x] == 'P')
						return new Point(y + position.x, position.y - x);
				if (0 <= (position.x - x) && (y + position.y) < game.getLab()[0].length)
					if (game.getLab()[position.x - x][y + position.y] == 'P')
						return new Point(position.x - x, y + position.y);
				if (0 <= (position.x - y) && (x + position.y) < game.getLab()[0].length)
					if (game.getLab()[position.x - y][x + position.y] == 'P')
						return new Point(position.x - y, x + position.y);
				if (2 * x <= d) {
					d -= 2 * x + 1;
					x++;
				} else if (d < 2 * (r - y)) {
					d += 2 * y - 1;
					y--;
				} else {
					d += 2 * (y - x - 1);
					y--;
					x++;
				}
			}
		}
		return null;

	}

	/*
	 * public boolean noReturn(Point goal) { if (goal.equals(position)) return
	 * true; else switch (dir) { case 'u': return goal.x == (position.x + 1);
	 * case 'd': return goal.x == (position.x - 1); case 'l': return goal.y ==
	 * (position.y + 1); case 'r': return goal.y == (position.y - 1); default:
	 * return false; } }
	 */

}
