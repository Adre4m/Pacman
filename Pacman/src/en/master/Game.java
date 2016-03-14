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

	// TODO g�rer l'apparition de fruit
	private char[][] lab;
	private short[][] gums;
	private int score;
	public final Characters[] characters = new Characters[5];
	private boolean restartNeed;
	private boolean paused;
	private ArrayList<Point> doors;
	private ArrayList<Point> jailWalls;
	private int numGum;
	public Graph graph;
	public int level = 1;
	public short difficulty = 0;
	public boolean ateFruit = true;
	short secFruit = Timer.FRUIT;

	public Game() {
		lab = new char[32][28];
		gums = new short[32][28];
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

	public void init(String file) {
		Stream stm = new Stream(); // Initiate the stream to read file
		String grid = stm.initiateLab(file);
		grid = grid.replaceAll("[\n\r]", "");
		int index = 0;
		for (int i = 0; i < lab.length; ++i)
			for (int j = 0; j < lab[0].length; ++j, index++) {

				switch (grid.charAt(index)) {
				case '1':
					characters[1] = new Blinky(i, j);
					((Ghost) characters[1]).setPatrolStart(new Point(1, lab[0].length - 2));
					lab[i][j] = 'G';
					gums[i][j] = 0;
					break;
				case '2':
					characters[2] = new Clyde(i, j);
					((Ghost) characters[2]).setPatrolStart(new Point(lab.length - 2, 1));
					lab[i][j] = 'G';
					jailWalls.add(new Point(i, j));
					gums[i][j] = 0;
					break;
				case '3':
					characters[3] = new Inky(i, j);
					((Ghost) characters[3]).setPatrolStart(new Point(lab.length - 2, lab[0].length - 2));
					lab[i][j] = 'G';
					jailWalls.add(new Point(i, j));
					gums[i][j] = 0;
					break;
				case '4':
					characters[4] = new Pinky(i, j);
					((Ghost) characters[4]).setPatrolStart(new Point(1, 1));
					lab[i][j] = 'G';
					gums[i][j] = 0;
					break;
				case 'D':
					doors.add(new Point(i, j));
					lab[i][j] = 'D';
					gums[i][j] = 0;
					break;
				case 'g':
				case 'S':
					numGum++;
					lab[i][j] = grid.charAt(index);
					gums[i][j] = (short) ((grid.charAt(index) == 'g') ? 1 : 2);
					break;
				case 'P':
					characters[0] = new Pacman(i, j);
					lab[i][j] = 'P';
					gums[i][j] = 0;
					break;
				default:
					lab[i][j] = grid.charAt(index);
					gums[i][j] = 0;
				}
			}
		graph = new Graph(lab);
	}

	public void newLevel() {
		level++;
		for (int i = 0; i < characters.length; ++i)
			characters[i].reinit(this);
		for (int i = 0; i < lab.length; ++i) {
			for (int j = 0; j < lab[0].length; ++j)
				if (gums[i][j] == 1) {
					lab[i][j] = 'g';
					numGum++;
				} else if (gums[i][j] == 2) {
					lab[i][j] = 'S';
					numGum++;
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
		int mvgpf = (Timer.FPS / Timer.GMVPS) + (level * difficulty);
		int mvvps = (Timer.FPS / Timer.VMVPS) + (level * difficulty);
		while (((Pacman) characters[0]).getLives() > 0) {
			while (!win()) {
				long cpt = 0;
				long[] mv = { 1, 1, 1, 1, 1 };
				if (secFruit <= 0 && ateFruit)
					appearFruit();
				else if (secFruit <= 0 && !ateFruit) {
					lab[14][14] = ' ';
					ateFruit = true;
				}
				while (cpt <= Timer.FPS && !win()) {
					if (!paused) {
						long begin = System.nanoTime();
						for (int i = 1; i < characters.length; ++i) {
							Ghost current = (Ghost) characters[i];
							if (current.isVulnerable() && ((cpt / mv[i]) >= mvvps)) {
								mv[i]++;
								current.ia(this);
							} else if (!current.isVulnerable() && (cpt / mv[i]) >= mvgpf) {
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
						System.out.println(this);
						// f.update(game);
					} else {
						System.out.println(this);
						// f.update(game);
					}
				}
				for (int i = 1; i < characters.length; ++i) {
					((Ghost) characters[i]).heal();
					((Ghost) characters[i]).release();
				}
				secFruit--;
			}
			if (win()) {
				newLevel();
				System.out.println(this);
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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

	public void appearFruit() {
		int prob = (int) (Math.random() * 101);
		if (50 <= prob && prob <= 100) {
			ateFruit = false;
			secFruit = Timer.FRUIT;
			int fruit = level % 8;
			switch (fruit) {
			case 1:
				lab[14][14] = 'C';
				break;
			case 2:
				lab[14][14] = 's';
				break;
			case 3:
				lab[14][14] = 'O';
				break;
			case 4:
				lab[14][14] = 'A';
				break;
			case 5:
				lab[14][14] = 'M';
				break;
			case 6:
				lab[14][14] = 'b';
				break;
			case 7:
				lab[14][14] = 'B';
				break;
			case 0:
				lab[14][14] = 'K';
				break;
			}
		}
	}

}
