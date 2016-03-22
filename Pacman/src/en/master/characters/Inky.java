package en.master.characters;

import en.master.Timer;

/**
 * 
 * @author BOURGEOIS Adrien
 *
 */
public class Inky extends Ghost {

	public Inky() {
		super("Inky");
		altSprite = "";
	}

	public Inky(int x, int y) {
		super("Inky", x, y);
		altSprite = "";
	}

	@Override
	public void jail() {
		jailed = Timer.PRISON + 4;
		isFree = false;
	}

}
