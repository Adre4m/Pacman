package en.master;

import java.util.concurrent.TimeUnit;

import en.window.Frame;

public class Game {

	// TODO En cas de mort restart puis attente de 1 seconde.
	private char[][] lab;
	private int score;
	public final Character[] characters = new Character[5];
	private boolean restartNeed;
	private boolean win;
	private boolean paused;

	public Game() {
		lab = new char[28][32];
		score = 0;
		restartNeed = false;
		win = false;
		paused = false;
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
		int i = lab.length / 2;
		int j = lab[0].length / 2;
		lab[i][j - 4] = 'G';
		lab[i][j - 3] = 'G';
		lab[i][j - 2] = 'G';
		lab[i][j - 1] = 'G';
		lab[i - 1][j] = 'S';
		lab[i][j] = 'P';
		characters[0] = new Pacman(i, j);
		characters[1] = new Blinky(i, j - 1);
		characters[2] = new Clyde(i, j - 2);
		characters[3] = new Inky(i, j - 3);
		characters[4] = new Pinky(i, j - 4);
	}

	public void initTest() {
		Stream stm = new Stream(); // Initiate the stream to read file
		String grid = stm.initiateLab("labyrinths/test.txt");

		int index = 0;
		for (int i = 0; i < lab.length; ++i)
			for (int j = 0; j < lab[0].length; ++j) {
				lab[i][j] = grid.charAt(index);
				index++;
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

	private void restart() {
		System.out.println("RESTART !!!!!!");
		for (int i = 0; i < characters.length; ++i)
			characters[i].reinit(this);
		System.out.println(this);
		restartNeed = false;
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
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

	// TODO en cas de necessité liberer les fantomes
	public void play(Frame f) {
		long frame = (long) ((1f / Timer.FPS) * 1000000000);
		while (((Pacman) characters[0]).getLives() > 0 && !win) {
			long cpt = 0;
			long mvP = 1;
			long mvG = 1;
			while (cpt < Timer.FPS + 1) {
				if (!paused) {
					long begin = System.nanoTime();
					if (((cpt / mvG) == (Timer.FPS / Timer.GMVPS)) || ((cpt / mvG) == (Timer.FPS / Timer.VMVPS))) {
						mvG++;
						for (int i = 1; i < characters.length; ++i)
							((Ghost) characters[i]).ia(this);
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
					for (int i = 1; i < characters.length; ++i)
						lab[characters[i].position.x][characters[i].position.y] = characters[i].toChar();
					lab[characters[0].position.x][characters[0].position.y] = characters[0].toChar();
					System.out.println(this);
					// frame.update(game);
				} else {
					// frame.update(game);
					System.out.println(this);
				}
			}
			for (int i = 1; i < characters.length; ++i) {
				if (0 < ((Ghost) characters[i]).getVulnerable())
					((Ghost) characters[i]).setVulnerable(((Ghost) characters[i]).getVulnerable() - 1);
				if (0 < ((Ghost) characters[i]).getJailed())
					((Ghost) characters[i]).setJailed(((Ghost) characters[i]).getJailed() - 1);
			}
		}

	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public void pause() {
		while (true)
			;
	}

}
