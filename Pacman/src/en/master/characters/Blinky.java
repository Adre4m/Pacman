package en.master.characters;

import en.master.Game;

public class Blinky extends Ghost {

	public Blinky() {
		super("Blinky");
		jailed = -1;
		isFree = true;
		altSprite = "_alt";
	}

	public Blinky(int x, int y) {
		super("Blinky", x, y);
		jailed = -1;
		setFree(true);
		altSprite = "_alt";
	}

	public void reinit(Game game) {
		super.reinit(game);
		jailed = -1;
		isFree = true;
		altSprite = "_alt";
	}

}
