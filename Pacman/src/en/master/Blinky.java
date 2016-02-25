package en.master;

public class Blinky extends Ghost {

	public Blinky() {
		super("Blinky");
		setJailed(0);
	}

	public Blinky(int x, int y) {
		super("Blinky", x, y);
		setJailed(0);
	}

	@Override
	public void patrol() {
		// TODO Auto-generated method stub

	}

}
