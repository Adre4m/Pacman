package en.master;

public class Game {

	private char[][] lab;
	private int score;
	public Character[] characters = { new Pacman(), new Clyde(), new Inky(), new Blinky(), new Pinky() };

	public Game() {
		lab = new char[28][32];
		score = 0;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public char[][] getLab() {
		return lab;
	}

	public void setLab(char[][] lab) {
		this.lab = lab;
	}

	public void init() {
		for(int i = 0; i < lab.length; ++i)
			for(int j = 0; j < lab[0].length; ++j)
				lab[i][j] = ' ';
		for(int j = 0; j < lab[0].length; ++j) {
			lab[0][j] = 'X';
			lab[lab.length - 1][j] = 'X';
		}
		int i = lab.length / 2;
		int j = lab[0].length / 2;
		lab[i][j] = 'G';
		lab[i - 2][j] = 'G';
		lab[i][j - 1] = 'G';
		lab[i][j + 1] = 'G';
		characters[1].getPosition().setLocation(i, j);
		characters[2].getPosition().setLocation(i, j + 1);
		characters[3].getPosition().setLocation(i - 2, j);
		characters[4].getPosition().setLocation(i, j - 1);
		characters[0].getPosition().setLocation(i + 6, j);
		lab[i][j + 2] = 'J';
		lab[i][j - 2] = 'J';
		lab[i - 1][j] = 'D';
		lab[i - 1][j - 1] = 'J';
		lab[i - 1][j - 2] = 'J';
		lab[i - 1][j + 1] = 'J';
		lab[i - 1][j + 2] = 'J';
		lab[i + 1][j] = 'J';
		lab[i + 1][j - 1] = 'J';
		lab[i + 1][j - 2] = 'J';
		lab[i + 1][j + 1] = 'J';
		lab[i + 1][j + 2] = 'J';
		lab[i + 6][j] = 'P';
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i < lab.length; ++i) {
			for(int j = 0; j < lab[0].length; ++j)
				s += lab[i][j];
			s += '\n';
		}
		return s;
	}

}
