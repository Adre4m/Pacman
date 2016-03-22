package en.master.characters;

import en.master.Game;

/**
 * 
 * @author BOURGEOIS Adrien
 *
 */
public class Blinky extends Ghost {

	public Blinky() {
		super("Blinky");
		altSprite = "_alt";
	}

	public Blinky(int x, int y) {
		super("Blinky", x, y);
		altSprite = "_alt";
	}

	public void reinit(Game game) {
		super.reinit(game);
		altSprite = "_alt";
	}

	@Override
	public void jail() {
		jailed = 0;
		isFree = true;
	}

}
