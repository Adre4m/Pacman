package en.master.characters;

import en.master.Timer;

/**
 * 
 * @author BOURGEOIS Adrien
 *
 */
public class Clyde extends Ghost {

	public Clyde() {
		super("Clyde");
		altSprite = "";
	}

	public Clyde(int x, int y) {
		super("Clyde", x, y);
		altSprite = "";
	}

	@Override
	public void jail() {
		jailed = Timer.PRISON + 8;
		isFree = false;
	}

}
