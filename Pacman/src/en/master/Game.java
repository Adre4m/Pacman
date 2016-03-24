package en.master;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
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

//TODO state fruit : if eaten notify frame

/**
 * 
 * @author BOURGEOIS Adrien
 * @version 1
 */
public class Game {

	private char[][] lab = new char[32][28];
	private short[][] gums = new short[32][28];
	private int score = 0;
	public final Characters[] characters = new Characters[5];
	private boolean restartNeed = false;
	private boolean paused = false;
	private ArrayList<Point> doors = new ArrayList<Point>();
	private ArrayList<Point> jailWalls = new ArrayList<Point>();
	public Graph graph;
	public int level = 1;
	public short difficulty = 0;
	public boolean appearedFruit = false;
	public boolean ateFruit = false;
	private short secFruit = Timer.FRUIT;
	private Point fruitSpawn;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public char[][] getLab() {
		return lab;
	}

	/**
	 * 
	 * This method call the initiation of Stream with file. All characters are
	 * generated with their own places. The walls of the prison, where a ghost
	 * shall return to when eaten, are based on the locations of the Ghost n°2
	 * and n°3.<br>
	 * <br>
	 * 
	 * The method also call {@link en.master.Stream#readOptions()} to know which
	 * theme is chosen and the difficulty.
	 * 
	 * @param file
	 *            File to be read
	 */
	public void init() {
		String grid = Stream.initiateLab("labyrinths/labyrinth" + ((int) (Math.random() * 2)) + ".txt");
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
					lab[i][j] = grid.charAt(index);
					gums[i][j] = (short) ((grid.charAt(index) == 'g') ? 1 : 2);
					break;
				case 'F':
					lab[i][j] = ' ';
					fruitSpawn = new Point(i, j);
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
		int[] opt = Stream.readOptions();
		difficulty = (short) opt[2];
		if (difficulty == 0) {
			((Pacman) characters[0]).easyLives();
		}
		switch (opt[0]) {
		case 0:
			for (int i = 0; i < characters.length; ++i)
				characters[i].setFolder("sprites/classic/");
			break;
		case 1:
			for (int i = 0; i < characters.length; ++i)
				characters[i].setFolder("sprites/sw/");
			break;
		case 2:
			for (int i = 0; i < characters.length; ++i)
				characters[i].setFolder("sprites/zelda/");
			break;
		default:
			for (int i = 0; i < characters.length; ++i)
				characters[i].setFolder("sprites/classic/");
			break;
		}
		System.out.println(this);
	}

	private void newLevel(Frame f) {
		lab[fruitSpawn.x][fruitSpawn.y] = ' ';
		appearedFruit = false;
		f.set.removeSprite(fruitSpawn);
		ateFruit = false;
		level++;
		for (int i = 0; i < characters.length; ++i) {
			Point p = new Point(characters[i].getPosition());
			characters[i].reinit(this);
			f.set.move(characters[i], p, ' ');
		}
		for (int i = 0; i < lab.length; ++i) {
			for (int j = 0; j < lab[0].length; ++j)
				if (gums[i][j] == 1) {
					lab[i][j] = 'g';
				} else if (gums[i][j] == 2) {
					lab[i][j] = 'S';
				}
		}
		System.out.println(this);
		f.set.resetLab(this);
		f.set.initFruit(getFruit());
	}

	/**
	 * @deprecated
	 * 
	 * 			This method was usefull untill the game was playable with the
	 *             {@link en.window.Frame}
	 */
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

	/**
	 * 
	 * @param x
	 *            ordinate point
	 * @param y
	 *            abscissa point
	 * @return The Ghost at position (x, y)
	 */
	public Ghost getGhost(int x, int y) {
		for (int i = 1; i < characters.length; ++i)
			if (characters[i].getPosition().x == x && characters[i].getPosition().y == y)
				return (Ghost) characters[i];
		return null;
	}

	private void restart(Frame f) {
		f.set.death(characters[0].getPosition());
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < characters.length; ++i) {
			Point p = new Point(characters[i].getPosition());
			characters[i].reinit(this);
			f.set.move(characters[i], p, ' ');
		}
		restartNeed = false;
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void setRestartNeed(boolean restartNeed) {
		this.restartNeed = restartNeed;
	}

	/**
	 * 
	 * @return true if the player has eaten all the gums, else false.
	 */
	public boolean win() {
		for (int i = 1; i < characters.length; ++i)
			if (((Ghost) characters[i]).getOld() == 'g' || ((Ghost) characters[i]).getOld() == 'S')
				return false;
		for (int i = 0; i < lab.length; ++i)
			for (int j = 0; j < lab[0].length; ++j)
				if (lab[i][j] == 'g' || lab[i][j] == 'S')
					return false;
		return true;
	}

	/**
	 * 
	 * First of all, this method retrieve essential data for
	 * {@link en.master.Timer} like : <br>
	 * <ul>
	 * <li>{@link en.master.Timer#FPS} the time to wait between each frame</li>
	 * <li>{@link en.master.Timer#GMVPS} how many times a ghost moves per second
	 * </li>
	 * <li>{@link en.master.Timer#VMVPS} how many times a vulnerable ghost moves
	 * per second</li>
	 * </ul>
	 * <br>
	 * Then it loops until the player dies. Inside this loop there is two other
	 * loops : one handle the levels and the other the movements and the frame.
	 * <br>
	 * 
	 * @param f
	 *            is the Panel on which is displayed the game.
	 * 
	 */
	public void play(Frame f) {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		f.set.initFruit(getFruit());
		f.set.updateHub(score, ((Pacman) characters[0]).getLives());
		long frame = (long) ((1f / Timer.FPS) * 1000000000);
		int mvgpf = (Timer.FPS / (Timer.GMVPS + (level * difficulty)));
		System.out.println(mvgpf);
		int mvvps = (Timer.FPS / (Timer.VMVPS + (level * difficulty)));
		while (((Pacman) characters[0]).getLives() > 0) {
			while (((Pacman) characters[0]).getLives() > 0 && !win()) {
				long cpt = 0;
				long[] mv = { 1, 1, 1, 1, 1 };
				if (secFruit <= 0 && !appearedFruit)
					appearFruit(f);
				else if ((secFruit <= 0 && appearedFruit) || ateFruit) {
					lab[fruitSpawn.x][fruitSpawn.y] = ' ';
					appearedFruit = false;
					f.set.removeSprite(fruitSpawn);
					ateFruit = false;
				}
				while (cpt <= Timer.FPS && !win()) {
					if (!paused) {
						long begin = System.nanoTime();
						for (int i = 1; i < characters.length; ++i) {
							Ghost current = (Ghost) characters[i];
							Point p = new Point(current.getPosition());
							if (current.isVulnerable() && ((cpt / mv[i]) >= mvvps)) {
								mv[i]++;
								current.ia(this);
								f.set.move(current, p, lab[p.x][p.y]);
							} else if (!current.isVulnerable() && (cpt / mv[i]) >= mvgpf) {
								mv[i]++;
								current.ia(this);
								f.set.move(current, p, lab[p.x][p.y]);
							} else {
								break;
							}
						}

						if ((cpt / (mv[0])) >= (Timer.FPS / Timer.PMVPS)) {
							Point p = new Point(characters[0].getPosition());
							mv[0]++;
							characters[0].move(this);
							f.set.move(characters[0], p, lab[p.x][p.y]);
						}
						if (restartNeed) {
							restart(f);
							cpt = 0;
							for (int i = 0; i < mv.length; ++i)
								mv[i] = 1;
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
						if (((Pacman) characters[0]).getLives() > 0)
							f.set.updateHub(score, ((Pacman) characters[0]).getLives() - 1);
					}
				}
				for (int i = 1; i < characters.length; ++i) {
					((Ghost) characters[i]).heal();
					((Ghost) characters[i]).release();
				}
				((Pacman) characters[0]).ill();
				secFruit--;
			}
			if (win()) {
				newLevel(f);
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		f.gameStarted = false;
		checkScore(f, getScore());
		f.resetFrame();
	}

	private void checkScore(Frame f, int score) {
		LinkedList<NodeScore> l = Stream.readScore("score.txt");
		if (l.isEmpty()) {
			l.add(new NodeScore("1°", f.askPseudo(), "" + score));
		} else {
			int start = 0, end = l.size();
			while (start < end) {
				NodeScore ns = l.get((end + start) / 2);
				if (score < Integer.parseInt(ns.getScore()))
					start = ((end + start) / 2) + 1;
				else
					end = ((end + start) / 2);
			}
			if (start < l.size() || l.size() != 10)
				l.add(start, new NodeScore(start + "°", f.askPseudo(), "" + score));
			if (l.size() > 10)
				l.removeLast();
			Stream.writeScores("score.txt", l);
		}
	}

	public void pause() {
		paused = !paused;
	}

	public ArrayList<Point> getJailWalls() {
		return jailWalls;
	}

	private void appearFruit(Frame f) {
		int prob = (int) (Math.random() * 101);
		if (50 <= prob && prob <= 100) {
			appearedFruit = true;
			secFruit = Timer.FRUIT;
			lab[fruitSpawn.x][fruitSpawn.y] = getFruit();
			f.set.putFruit(fruitSpawn, getFruit());
		}
	}

	private char getFruit() {
		int fruit = level % 8;
		switch (fruit) {
		case 1:
			return 'C';
		case 2:
			return 's';
		case 3:
			return 'O';
		case 4:
			return 'A';
		case 5:
			return 'M';
		case 6:
			return 'b';
		case 7:
			return 'B';
		case 0:
			return 'K';
		default:
			return 'C';
		}

	}

}
