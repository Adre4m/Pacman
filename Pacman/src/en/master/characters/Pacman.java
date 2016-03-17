package en.master.characters;

public class Pacman extends Characters {

	private int lives;
	private int eatenGhosts;
	private long invulnerable;
	private String invulnerableSprite;
	private boolean isInvunerable;

	public Pacman() {
		super("PacMan");
		lives = 3;
		eatenGhosts = 0;
		invulnerable = 0;
		invulnerableSprite = "_invu";
		isInvunerable = false;
		dir = 'l';
	}

	public Pacman(int x, int y) {
		super("PacMan", x, y);
		lives = 3;
		eatenGhosts = 0;
		invulnerable = 0;
		invulnerableSprite = "_invu";
		isInvunerable = false;
	}

	public int getLives() {
		return lives;
	}

	public void death() {
		this.lives -= 1;
	}

	public int eatGhost() {
		return this.eatenGhosts++;
	}

	public void eatSupergum() {
		this.eatenGhosts = 0;
	}

	public char toChar() {
		return 'P';
	}

	public int getEatenGhosts() {
		return eatenGhosts;
	}

	public void setEatenGhosts(int eatenGhosts) {
		this.eatenGhosts = eatenGhosts;
	}

	public boolean isInvunerable() {
		return isInvunerable;
	}

	public void setInvunerable(boolean isInvunerable) {
		this.isInvunerable = isInvunerable;
	}

	public long getInvulnerable() {
		return invulnerable;
	}

	public void setInvulnerable(long invulnerable) {
		this.invulnerable = invulnerable;
		if (0 < invulnerable)
			this.isInvunerable = true;
		else
			this.isInvunerable = false;
	}

	public String getInvulnerableSprite() {
		return invulnerableSprite;
	}

	public void setInvulnerableSprite(String invulnerableSprite) {
		this.invulnerableSprite = invulnerableSprite;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	@Override
	public String sprite() {
		if (!isInvunerable)
			return super.sprite();
		String s = folder + sprite;
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
		return s + invulnerableSprite + ".gif";
	}

}
