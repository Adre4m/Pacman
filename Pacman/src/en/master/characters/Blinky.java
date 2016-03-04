package en.master.characters;

import en.master.Game;

public class Blinky extends Ghost {

	public Blinky() {
		super("Blinky");
		jailed = -1;
		isFree = true;
	}

	public Blinky(int x, int y) {
		super("Blinky", x, y);
		jailed = -1;
		setFree(true);
	}

	public void reinit(Game game) {
		super.reinit(game);
		jailed = -1;
		isFree = true;
	}

	@Override
	public void patrol() {
		// TODO Auto-generated method stub

	}

}
