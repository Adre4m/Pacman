package en.master;

public class Pacman extends Character {

	private int lives;
	
	public Pacman() {
		super();
		lives = 3;
	}

	public int getLives() {
		return lives;
	}

	public void death() {
		this.lives -= 1;
	}
	
	
	
}
