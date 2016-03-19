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
import en.window.GameScreen;

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
	private int numGum = 0;
	public Graph graph;
	public int level = 1;
	public short difficulty = 0;
	public boolean ateFruit = true;
	public int pacmanX = 0;
	public int pacmanY = 0;
	short secFruit = Timer.FRUIT;

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
	public void init(String file) {
		//Stream stm = new Stream(); // Initiate the stream to read file
		String grid = Stream.initiateLab(file);
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
					this.pacmanX=j;
					this.pacmanY=i;
					break;
				default:
					lab[i][j] = grid.charAt(index);
					gums[i][j] = 0;
				}
			}
		graph = new Graph(lab);
		int[] opt = Stream.readOptions();
		difficulty = (short) opt[2];
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
				characters[i].setFolder("sprites/bh6/");
			break;
		case 3:
			for (int i = 0; i < characters.length; ++i)
				characters[i].setFolder("sprites/zelda/");
			break;
		default:
			for (int i = 0; i < characters.length; ++i)
				characters[i].setFolder("sprites/classic/");
			break;
		}
	}
	
	public int getPacmanX() {
		return pacmanX;
	}

	public int getPacmanY() {
		return pacmanY;
	}
	
	private void newLevel() {
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

	/**
	 * 
	 * @return true if the player has eaten all the gums, else false.
	 */
	public boolean win() {
		return numGum <= 0;
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
	 * @param g
	 *            is the Panel on which is displayed the game.
	 * 
	 */
	public void play(GameScreen g) {
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
							// Point p = current.getPosition();
							if (current.isVulnerable() && ((cpt / mv[i]) >= mvvps)) {
								mv[i]++;
								current.ia(this);
								// g.move(p.y, p.x, current.getDir(),
								// lab[p.x][p.y]);
							} else if (!current.isVulnerable() && (cpt / mv[i]) >= mvgpf) {
								mv[i]++;
								current.ia(this);
								// g.move(p.y, p.x, current.getDir(),
								// lab[p.x][p.y]);
							} else {
								break;
							}
						}

						if ((cpt / (mv[0])) >= (Timer.FPS / Timer.PMVPS)) {
							// Point p = characters[0].getPosition();
							mv[0]++;
							characters[0].move(this);
							// g.move(p.y, p.x, characters[0].getDir(),
							// lab[p.x][p.y]);
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
		//
	}
	
	public void checkScore(Frame f, int score) {
		LinkedList<NodeScore> l = Stream.readScore("score.txt");
		if (l.isEmpty()) {
			l.add(new NodeScore("1°",f.askPseudo() , "" + score));
		} else {
			int start = 0, end = l.size();
			while(start < end) {
				NodeScore ns =  l.get((end + start) / 2);
				if(score < Integer.parseInt(ns.getScore()))
					start = ((end + start) / 2) + 1;
				else
					end = ((end + start) / 2);
			}
			if(start < l.size() || l.size() != 10)
				l.add(start, new NodeScore(start + "°",f.askPseudo() , "" + score));
					if (l.size() > 10)
						l.removeLast();
					Stream.writeScores("score.txt", l);
		}		 
	}
	
	public void pause() {
		paused = !paused;
	}

	public void eatGum() {
		numGum--;
	}

	public Graph getGraph() {
		return graph;
	}

	public ArrayList<Point> getJailWalls() {
		return jailWalls;
	}

	private void appearFruit() {
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
