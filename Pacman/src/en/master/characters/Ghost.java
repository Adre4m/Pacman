package en.master.characters;

import java.awt.Point;
import java.util.Stack;

import en.master.Game;
import en.master.Timer;

/**
 * 
 * @author BOURGEOIS Adrien
 *
 */
public abstract class Ghost extends Characters {

	private static final int MAXRADIUS = 7;
	protected boolean isVulnerable;
	protected boolean isFree;
	protected boolean isReturningToJail;
	protected long jailed;
	protected long vulnerable;
	protected char old;
	protected String altSprite;
	private Stack<Character> directions;
	protected Point patrolStart;

	public Ghost(String sprite) {
		super(sprite);
		isVulnerable = false;
		isReturningToJail = false;
		jail();
	}

	public Ghost(String sprite, int x, int y) {
		super(sprite, x, y);
		isVulnerable = false;
		isReturningToJail = false;
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

	/**
	 * According on the state of the current ghost the ia will chose between
	 * three different behaviors:
	 * <ul>
	 * <li>If the ghost is eatean then it will return to the jail.</li>
	 * <li>If the ghost is seeing Pacman then it will or chase him or flee him.
	 * </li>
	 * <li>In all other cases, the ghost will return to its patrol point and
	 * start its patrol</li>
	 * </ul>
	 * 
	 * @param game
	 *            where to set the changes
	 * 
	 * @see <a href=
	 *      "https://fr.wikipedia.org/wiki/Algorithme_de_trac%C3%A9_de_cercle_d%27Andres"
	 *      target="_blank"> Drawing circle algorithm by Andres</a>
	 */
	public void ia(Game game) {
		if (isFree) {
			Point pacman;
			if (isReturningToJail) {
				isEaten(game);
			} else if ((pacman = radius(game)) != null) {
				chased(pacman, game);
			} else {
				patrol(game);
			}
			move(game);
		}
	}

	private void isEaten(Game game) {
		if (directions == null || directions.isEmpty()) {
			Stack<Character> directions1 = game.graph.reach(position, game.getJailWalls().get(0), dir);
			Stack<Character> directions2 = game.graph.reach(position, game.getJailWalls().get(1), dir);
			directions = (directions1.size() <= directions2.size()) ? directions1 : directions2;
		}
		dir = directions.pop();
		if (directions.isEmpty())
			isReturningToJail = false;
	}

	private void patrol(Game game) {
		if (position.x == patrolStart.x && position.y == patrolStart.y) {
			directions.clear();
			switch (dir) {
			case 'u':
			case 'd':
				if (position.y == 1)
					dir = 'r';
				else
					dir = 'l';
				break;
			case 'r':
			case 'l':
				if (position.x == 1)
					dir = 'd';
				else
					dir = 'u';
				break;
			}
		} else {
			if (directions == null || directions.isEmpty())
				directions = game.graph.reach(position, patrolStart, dir);
			dir = directions.pop();
		}
	}

	private void chased(Point pacman, Game game) {
		if (isVulnerable)
			opposite(game, pacman);
		directions = game.graph.reach(position, pacman, dir);
		dir = directions.pop();
		directions.clear();
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

	public abstract void jail();

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

	/**
	 * When Pacman eat a super pacgum the ghosts are ill, this method add a time
	 * of vulnerability.
	 */
	public void ill() {
		vulnerable = Timer.SUPERGUM;
		this.isVulnerable = true;
	}

	/**
	 * When the ghost is no longer vulnerable.
	 */
	public void heal() {
		vulnerable--;
		if (0 < vulnerable)
			this.isVulnerable = true;
		else
			this.isVulnerable = false;
	}

	public String getVulnerableSprite() {
		return altSprite;
	}

	public void setVulnerableSprite(String vulnerableSprite) {
		this.altSprite = vulnerableSprite;
	}

	public void reinit(Game game) {
		super.reinit(game);
		isVulnerable = false;
		isReturningToJail = false;
		jail();
	}

	private Point radius(Game game) {
		for (int r = 1; r <= (MAXRADIUS + (game.level * game.difficulty)); ++r) {
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

	private void opposite(Game game, Point p) {
		p.x = Math.floorMod(-p.x, game.getLab().length);
		p.y = Math.floorMod(-p.y, game.getLab()[0].length);
		if (game.getLab()[p.x][p.y] == 'X') {
			while (game.getLab()[p.x][p.y] == 'X') {
				p.x = Math.floorMod(p.x - 1, game.getLab().length);
				p.y = Math.floorMod(p.y - 1, game.getLab().length);
			}
		}
	}

	public void setPatrolStart(Point patrolStart) {
		this.patrolStart = patrolStart;
	}

	@Override
	public String sprite() {
		String s = folder;
		if (isReturningToJail)
			s += "Ghost_eye";
		else if (isVulnerable && 3 < vulnerable)
			s += "Blue_ghost";
		else if (isVulnerable && vulnerable <= 3)
			s += "Blue_ghost_blinking";
		else
			return super.sprite();

		switch (dir) {
		case 'u':
			s += "_up";
			break;
		case 'd':
			s += "_down";
			break;
		case 'r':
			s += "_right";
			break;
		case 'l':
			s += "_left";
			break;
		}
		return s + altSprite + ".gif";
	}

}
