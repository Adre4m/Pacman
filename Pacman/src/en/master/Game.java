package en.master;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class Game {

	private char[][] lab;
	private int score;
	public Character[] characters = { new Pacman(), new Clyde(), new Inky(), new Blinky(), new Pinky() };
	private boolean restartNeed;
	private boolean win;

	public Game() {
		lab = new char[28][32];
		score = 0;
		restartNeed = false;
		win = false;
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

	}

	public void initTest() {
		InputStream is = null;
		int a;
		char c;

		try {
			// new input stream created
			is = new FileInputStream("labyrinths/test.txt");

			String s = "";
			boolean b = true;
			// reads till the end of the stream
			while ((a = is.read()) != -1) {
				// converts integer to character
				c = (char) a;
				if (c != '\n') // avoid line breaks
					s += c;
				else {
					s = s.substring(0, s.length() - 1); // There is a space
														// which has nothing to
														// do here
				}

			}
			int index = 0;
			for (int i = 0; i < lab.length; ++i)
				for (int j = 0; j < lab[0].length; ++j) {
					lab[i][j] = s.charAt(index);
					index++;
				}
			// int i = lab.length / 2;
			// int j = lab[0].length / 2;
			// lab[i][j - 4] = 'G';
			// lab[i][j - 3] = 'G';
			// lab[i][j - 2] = 'G';
			// lab[i][j - 1] = 'G';
			// lab[i - 1][j] = 'S';
			// lab[i][j] = 'P';
			// characters[0].position.setLocation(i, j);
			// characters[1].position.setLocation(i, j - 1);
			// characters[2].position.setLocation(i, j - 2);
			// characters[3].position.setLocation(i, j - 3);
			// characters[4].position.setLocation(i, j - 4);
		} catch (Exception e) {

			// if any I/O error occurs
			e.printStackTrace();
		} finally {

			// releases system resources associated with this stream
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < lab.length; ++i) {
			for (int j = 0; j < lab[0].length; ++j)
				s += lab[i][j];
			s += '\n';
		}
		s += "Score : " + score + '\n';
		return s;
	}

	public Ghost getGhost(int x, int y) {
		for (int i = 1; i < characters.length; ++i)
			if (characters[i].position.x == x && characters[i].position.y == y)
				return (Ghost) characters[i];
		return null;
	}

	public void restart() {
		System.out.println("RESTART !!!!!!");
	}

	public boolean isRestartNeed() {
		return restartNeed;
	}

	public void setRestartNeed(boolean restartNeed) {
		this.restartNeed = restartNeed;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	// TODO en cas d'entré clavier changer la direction du personnage
	// TODO en cas de necessité liberer les fantomes, et gerer cas supergum
	public void play() {
		long frame = (long) ((1f / Timer.FPS) * 1000000000);
		while (((Pacman) characters[0]).getLives() > 0 && !win) {
			long cpt = 0;
			long mvP = 1;
			long mvG = 1;
			while (cpt < Timer.FPS + 1) {
				long begin = System.nanoTime();
				if (((cpt / mvG) == (Timer.FPS / Timer.GMVPS)) || ((cpt / mvG) == (Timer.FPS / Timer.VMVPS))) {
					mvG++;
					for (int i = 1; i < characters.length; ++i)
						((Ghost) characters[i]).ia();
				}
				if ((cpt / (mvP)) == (Timer.FPS / Timer.PMVPS)) {
					mvP++;
					characters[0].move(this);
				}
				if (restartNeed) {
					restart();
				}
				long current = System.nanoTime();
				long fps = 0;
				long time = (current - begin);
				if (time <= frame) {
					try {
						TimeUnit.NANOSECONDS.sleep(frame - time);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					fps = 1;
				} else {
					fps = 1;
					while (time > fps * frame)
						++fps;
				}

				cpt += fps;
				// frame.update(game);
				System.out.println(this);
			}
		}

	}

}
