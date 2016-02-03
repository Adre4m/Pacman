package en.master;

public class Game {

	private char[][] lab;
	private int score;
	public Character[] characters = { new Pacman(), new Clyde(), new Inky(), new Blinky(), new Pinky() };
	public char[] stack = { ' ', ' ', ' ', ' ', ' ' };

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
		for (int i = 0; i < lab.length; ++i)
			for (int j = 0; j < lab[0].length; ++j)
				lab[i][j] = ' ';
		for (int j = 0; j < lab[0].length; ++j) {
			lab[0][j] = 'X';
			lab[lab.length - 1][j] = 'X';
		}
		for (int i = 1; i < 10; ++i) {
			lab[i][0] = 'X';
			lab[i][lab[0].length - 1] = 'X';
		}
		for (int i = 1; i < 5; ++i) {
			lab[i][lab[0].length / 2] = 'X';
			lab[i][lab[0].length / 2 - 1] = 'X';
		}
		for (int j = 3; j < 7; ++j) {
			lab[2][j] = 'X';
			lab[4][j] = 'X';
			lab[2][lab[0].length - j - 1] = 'X';
			lab[4][lab[0].length - j - 1] = 'X';
			lab[6][j] = 'X';
			lab[6][lab[0].length - j - 1] = 'X';
			lab[7][j] = 'X';
			lab[7][lab[0].length - j - 1] = 'X';
		}
		lab[3][3] = 'X';
		lab[3][6] = 'X';
		lab[3][lab[0].length - 4] = 'X';
		lab[3][lab[0].length - 7] = 'X';
		for (int j = 8; j < 13; ++j) {
			lab[2][j] = 'X';
			lab[4][j] = 'X';
			lab[2][lab[0].length - j - 1] = 'X';
			lab[4][lab[0].length - j - 1] = 'X';
		}
		lab[3][8] = 'X';
		lab[3][12] = 'X';
		lab[3][lab[0].length - 9] = 'X';
		lab[3][lab[0].length - 13] = 'X';
		for (int i = 6; i < 14; ++i) {
			lab[i][8] = 'X';
			lab[i][9] = 'X';
			lab[i][lab[0].length - 9] = 'X';
			lab[i][lab[0].length - 10] = 'X';
		}
		for (int j = 11; j < (lab[0].length / 2); ++j) {
			lab[6][j] = 'X';
			lab[7][j] = 'X';
			lab[6][lab[0].length - 1 - j] = 'X';
			lab[7][lab[0].length - 1 - j] = 'X';
		}
		for (int i = 8; i < 11; ++i) {
			lab[i][lab[0].length / 2] = 'X';
			lab[i][lab[0].length / 2 - 1] = 'X';
		}
		for (int j = 8; j < 13; ++j) {
			lab[9][j] = 'X';
			lab[10][j] = 'X';
			lab[9][lab[0].length - 1 - j] = 'X';
			lab[10][lab[0].length - 1 - j] = 'X';
		}

		int i = lab.length / 2;
		int j = lab[0].length / 2;
		lab[i][j] = 'G';
		lab[i - 3][j] = 'G';
		lab[i][j - 1] = 'G';
		lab[i][j + 1] = 'G';
		characters[1].getPosition().setLocation(i, j);
		characters[2].getPosition().setLocation(i, j + 1);
		characters[3].getPosition().setLocation(i - 3, j);
		characters[4].getPosition().setLocation(i, j - 1);
		lab[i - 3][j - 1] = 'P';
		lab[i - 3][j - 2] = 'S';
		characters[0].getPosition().setLocation(i - 3, j - 1);
		lab[i][j + 2] = 'J';
		lab[i][j - 3] = 'J';
		lab[i - 1][j - 2] = 'J';
		lab[i - 1][j + 2] = 'J';
		lab[i + 1][j] = 'J';
		lab[i + 1][j - 1] = 'J';
		lab[i + 1][j - 2] = 'J';
		lab[i + 1][j + 1] = 'J';
		lab[i + 1][j + 2] = 'J';
		lab[i - 2][j] = 'D';
		lab[i - 2][j - 1] = 'D';
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < lab.length; ++i) {
			for (int j = 0; j < lab[0].length; ++j)
				s += lab[i][j];
			s += '\n';
		}
		return s;
	}

	public char getTopStack(Character c) {
		if (c instanceof Pacman)
			return stack[0];
		else if (c instanceof Clyde)
			return stack[1];
		else if (c instanceof Inky)
			return stack[2];
		else if (c instanceof Blinky)
			return stack[3];
		else
			return stack[4];
	}

	public void setTopStack(Character c, char old) {
		if (c instanceof Pacman)
			stack[0] = old;
		else if (c instanceof Clyde)
			stack[1] = old;
		else if (c instanceof Inky)
			stack[2] = old;
		else if (c instanceof Blinky)
			stack[3] = old;
		else
			stack[4] = old;
	}

	public Ghost getGhost(int x, int y) {
		for (int i = 1; i < characters.length; ++i)
			if (characters[i].position.x == x && characters[i].position.y == y)
				return (Ghost) characters[i];
		return null;
	}

}
