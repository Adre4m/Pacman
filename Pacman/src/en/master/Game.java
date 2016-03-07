package en.master;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import en.master.characters.Blinky;
import en.master.characters.Characters;
import en.master.characters.Clyde;
import en.master.characters.Ghost;
import en.master.characters.Inky;
import en.master.characters.Pacman;
import en.master.characters.Pinky;
import en.master.graph.Graph;
import en.window.Frame;

public class Game {

	// TODO En cas de mort restart puis attente de 1 seconde.
	private char[][] lab;
	private int score;
	public final Characters[] characters = new Characters[5];
	private boolean restartNeed;
	private boolean paused;
	private ArrayList<Point> doors;
	private ArrayList<Point> jailWalls;
	private int numGum;
	private Graph graph;

	public Game() {
		lab = new char[32][28];
		score = 0;
		restartNeed = false;
		paused = false;
		numGum = 0;
		doors = new ArrayList<Point>();
		jailWalls = new ArrayList<Point>();
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

	public void initTest(String file) {
		Stream stm = new Stream(); // Initiate the stream to read file
		String grid = stm.initiateLab(file);
		grid = grid.replaceAll("[\n\r]", "");
		int index = 0;
		for (int i = 0; i < lab.length; ++i)
			for (int j = 0; j < lab[0].length; ++j, index++) {

				switch (grid.charAt(index)) {
				case '1':
					characters[1] = new Blinky(i, j);
					lab[i][j] = 'G';
					break;
				case '2':
					characters[2] = new Clyde(i, j);
					lab[i][j] = 'G';
					jailWalls.add(new Point(i, j));
					break;
				case '3':
					characters[3] = new Inky(i, j);
					lab[i][j] = 'G';
					jailWalls.add(new Point(i, j));
					break;
				case '4':
					characters[4] = new Pinky(i, j);
					lab[i][j] = 'G';
					break;
				case 'D':
					doors.add(new Point(i, j));
					lab[i][j] = 'D';
					break;
				case 'g':
				case 'S':
					numGum++;
					lab[i][j] = grid.charAt(index);
					break;
				case 'P':
					characters[0] = new Pacman(i, j);
					lab[i][j] = 'P';
					break;
				default:
					lab[i][j] = grid.charAt(index);
				}
			}
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < lab.length; ++i) {
			for (int j = 0; j < lab[0].length; ++j)
				s += lab[i][j];
			s += "\n";
		}
		s += "Score : " + score + "\n";
		return s;
	}

	public Ghost getGhost(int x, int y) {
		for (int i = 1; i < characters.length; ++i)
			if (characters[i].getPosition().x == x && characters[i].getPosition().y == y)
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

	public boolean win() {
		return numGum <= 0;
	}

	public void play(Frame f) {
		long frame = (long) ((1f / Timer.FPS) * 1000000000);
		while (((Pacman) characters[0]).getLives() > 0 && !win()) {
			long cpt = 0;
			long[] mv = { 1, 1, 1, 1, 1 };
			while (cpt <= Timer.FPS && !win()) {
				if (!paused) {
					long begin = System.nanoTime();
					for (int i = 1; i < characters.length; ++i) {
						Ghost current = (Ghost) characters[i];
						if (current.isVulnerable() && ((cpt / mv[i]) >= (Timer.FPS / Timer.VMVPS))) {
							mv[i]++;
							current.ia(this);
						} else if (!current.isVulnerable() && (cpt / mv[i]) >= (Timer.FPS / Timer.GMVPS)) {
							mv[i]++;
							current.ia(this);
						}
					}

					if ((cpt / (mv[0])) >= (Timer.FPS / Timer.PMVPS)) {
						mv[0]++;
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
						lab[characters[i].getPosition().x][characters[i].getPosition().y] = characters[i].toChar();
					lab[characters[0].getPosition().x][characters[0].getPosition().y] = characters[0].toChar();
					// System.out.println(this);
					// f.update(game);
				} else {
					// System.out.println(this);
					// f.update(game);
				}
			}
			for (int i = 1; i < characters.length; ++i) {
				((Ghost) characters[i]).heal();
				((Ghost) characters[i]).release();
			}
		}

	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public void eatGum() {
		numGum--;
	}

	public Graph getGraph() {
		return graph;
	}

	public ArrayList<Point> getJailWall() {
		return jailWalls;
	}

	public void setJailWalls(ArrayList<Point> jailWalls) {
		this.jailWalls = jailWalls;
	}

}
