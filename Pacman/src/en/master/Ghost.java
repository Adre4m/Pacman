package en.master;

import java.awt.Point;

public abstract class Ghost extends Character {

	private static final int RADIUS = 8;
	protected boolean isVulnerable;
	protected boolean isFree;
	protected boolean isReturningToJail;
	protected long jailed;
	protected long vulnerable;
	protected char old;
	protected String vulnerableSprite;
	protected String eyeSprite;
	// private int speed;

	public Ghost(String sprite) {
		super(sprite);
		vulnerableSprite = "Blue_ghost.gif";
		eyeSprite = "Ghost_eyes";
		isVulnerable = false;
		isFree = false;
		isReturningToJail = false;
		jailed = Timer.PRISON;
	}

	public Ghost(String sprite, int x, int y) {
		super(sprite, x, y);
		vulnerableSprite = "Blue_ghost.gif";
		eyeSprite = "Ghost_eyes";
		isVulnerable = false;
		isFree = false;
		isReturningToJail = false;
		jailed = Timer.PRISON;
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
		if (jailed <= 0)
			isFree = true;
		if (isFree) {
			Point pacman;
			System.out.println("Is" + ((isVulnerable) ? " " : " not ") + "vulnerable");
			if (isReturningToJail) {
				System.out.println("Is returning to jail");
				isEaten();
			} else if ((pacman = radius(game)) != null) {
				System.out.println("Is chasing and is" + ((isVulnerable) ? " " : " not ") + "vulnerable");
				chased(pacman);
			} else {
				System.out.println("Is patroling and is" + ((isVulnerable) ? " " : " not ") + "vulnerable");
				patrol();
			}
			move(game);
		}
	}

	private void isEaten() {
		System.out.println("a complete");
	}

	protected abstract void patrol();

	private void chased(Point pacman) {
		System.out.println("Pacman found at : " + pacman);
		if (isVulnerable)
			System.out.println("Ghost should runaway from Pacman");
		else
			System.out.println("Ghost should chase Pacman");
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

	public void setJailed(long jailed) {
		this.jailed = jailed;
		if (0 < jailed)
			this.isFree = true;
		else
			this.isFree = false;
	}

	public long getVulnerable() {
		return vulnerable;
	}

	public void setVulnerable(long vulnerable) {
		this.vulnerable = vulnerable;
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

	public Point radius(Game game) {
		System.out.println("Looking for pacman and is" + ((isVulnerable) ? " " : " not ") + "vulnerable");
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

}
