package en.master;

public class Pacman extends Character {

	private int lives;
	private int eatenGhosts;

	public Pacman() {
		super();
		lives = 3;
		eatenGhosts = 0;
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

}
