package en.master;

public class Pacman extends Character {

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
		invulnerableSprite = "PacMan_invulnerable";
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

}
