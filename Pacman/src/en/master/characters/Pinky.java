package en.master.characters;

import en.master.Timer;

/**
 * 
 * @author BOURGEOIS Adrien
 *
 */
public class Pinky extends Ghost {

	public Pinky() {
		super("Pinky");
		altSprite = "";
	}

	public Pinky(int x, int y) {
		super("Pinky", x, y);
		altSprite = "";
	}

	public void jail() {
		jailed = Timer.PRISON;
		isFree = false;
	}

}
