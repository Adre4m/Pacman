package en.master;

public class Pacman extends Character {

	private int killcount;
	
	public Pacman() {
		super();
		killcount = 3;
	}

	public int getKillcount() {
		return killcount;
	}

	public void setKillcount(int killcount) {
		this.killcount = killcount;
	}
	
}
