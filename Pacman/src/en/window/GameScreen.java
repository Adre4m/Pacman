package en.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import en.controls.ControlsMouse;
import en.master.Game;
import en.master.Stream;
import en.master.characters.Characters;

/**
 * 
 * @author RIETZ Vincent
 *
 */
public class GameScreen extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String theme;
	private JPanel grid;
	private JLabel l;
	private JLabel sco;
	private JLabel f;
	private int length;

	/**
	 * 
	 * This is the GameScreen constructor
	 * 
	 * @author RIETZ Vincent
	 * 
	 */
	public GameScreen(Game game) {
		this.setBackground(Color.BLACK);
		// theme
		switch (Stream.readOptions()[0]) {
		case 0:
			theme = "classic";
			break;
		case 1:
			theme = "sw";
			break;
		case 2:
			theme = "zelda";
			break;
		}

		// ##########Grid###########
		grid = new JPanel();
		grid.setBackground(Color.BLACK);
		length = game.getLab()[0].length;

		grid.setOpaque(true);
		grid.setLayout(new GridLayout(game.getLab().length, length));

		ControlsMouse ck = null;
		if (Stream.readOptions()[1] == 1)
			ck = new ControlsMouse(game);
		// load cases
		int number = 0; // case's numbers
		for (int i = 0; i < game.getLab().length; ++i) {
			for (int j = 0; j < game.getLab()[0].length; ++j) {
				Case c = null; // new Case(this, game, game.getLab()[i][j],
								// number);
				if (game.getLab()[i][j] == 'P')
					c = new Case(this, number, game.characters[0].sprite(), theme, game.getLab().length);
				else if (game.getLab()[i][j] == 'G')
					c = new Case(this, number, game.getGhost(i, j).sprite(), theme, game.getLab().length);
				else {
					String sprite = "sprites/" + theme;
					switch (game.getLab()[i][j]) {
					case 'g':
						sprite += "/PacGum.gif";
						break;
					case 'S':
						sprite += "/Super pacgum.gif";
						break;
					case 'C':
						sprite += "/Cherry.gif";
						break;
					case 's':
						sprite += "/Strawberry.gif";
						break;
					case 'O':
						sprite += "/Orange.gif";
						break;
					case 'A':
						sprite += "/Apple.gif";
						break;
					case 'M':
						sprite += "/Melon.gif";
						break;
					case 'b':
						sprite += "/Galboss.gif";
						break;
					case 'B':
						sprite += "/Bell.gif";
						break;
					case 'K':
						sprite += "/Key.gif";
						break;
					case 'D':
						sprite += "/door.gif";
						break;
					case 'X':
						sprite += "/wall.gif";
						break;
					}
					c = new Case(this, number, sprite, theme, game.getLab().length);
				}
				grid.add(c);
				number++;
				if (ck != null)
					c.addMouseListener(ck);
			}
		}

		grid.setVisible(true);

		this.setLayout(new BorderLayout());
		this.add(grid, BorderLayout.CENTER);
		this.add(hubLeft(), BorderLayout.WEST);
		this.add(hubRight(), BorderLayout.EAST);
	}

	/**
	 * This method will create display the game information on the left of the
	 * lab
	 * 
	 * @author GRIGNON Lindsay
	 * @return JPanel the left hub
	 */
	private JPanel hubLeft() {

		JLabel s = new JLabel("Score : ");
		Frame.labelStyleW(s);
		l = new JLabel("Lives : ");
		Frame.labelStyleW(l);

		JPanel hub = new JPanel();
		hub.setPreferredSize(new Dimension(200, 0));
		hub.setBackground(Color.BLACK);
		hub.setLayout(new BorderLayout());

		hub.add(s, BorderLayout.NORTH);
		hub.add(l, BorderLayout.SOUTH);

		return hub;
	}

	/**
	 * This method will create and display the game information on the right of
	 * the lab<br>
	 * 
	 * @author GRIGNON Lindsay
	 * 
	 * @return JPanel the right hub
	 */
	private JPanel hubRight() {

		sco = new JLabel();
		Frame.labelStyleW(sco);
		f = new JLabel(new ImageIcon());

		JPanel hub = new JPanel();
		hub.setPreferredSize(new Dimension(200, 0));
		hub.setBackground(Color.BLACK);
		hub.setLayout(new BorderLayout());

		JPanel alt = new JPanel();
		alt.setBackground(Color.BLACK);
		alt.setLayout(new BorderLayout());
		alt.add(f, BorderLayout.SOUTH);

		hub.add(sco, BorderLayout.NORTH);
		hub.add(alt, BorderLayout.EAST);

		return hub;
	}

	/**
	 * Method that the game will call to update the game's hub
	 * 
	 * @author GRIGNON Lindsay
	 * @param score
	 *            he player current score
	 * @param lives
	 *            the number of Pacman's lives
	 */
	public void updateHub(int score, int lives) {
		sco.setText("" + score);
		l.setText("Lives : " + lives);
	}

	/**
	 * This method will be called by the game to determine the fruit sprite <br>
	 * The sprite will be displayed in the hub when Pacman eat the fruit
	 * 
	 * @author GGRIGNON Lindsay
	 * @param sprite
	 * 
	 */
	public void initFruit(char sprite) {
		ImageIcon f_s = null;
		switch (sprite) {
		case 'C':
			f_s = new ImageIcon("sprites/" + theme + "/Cherry.gif");
			break;
		case 's':
			f_s = new ImageIcon("sprites/" + theme + "/Strawberry.gif");
			break;
		case 'O':
			f_s = new ImageIcon("sprites/" + theme + "/Orange.gif");
			break;
		case 'A':
			f_s = new ImageIcon("sprites/" + theme + "/Apple.gif");
			break;
		case 'M':
			f_s = new ImageIcon("sprites/" + theme + "/Melon.gif");
			break;
		case 'b':
			f_s = new ImageIcon("sprites/" + theme + "/Galboss.gif");
			break;
		case 'B':
			f_s = new ImageIcon("sprites/" + theme + "/Bell.gif");
			break;
		case 'K':
			f_s = new ImageIcon("sprites/" + theme + "/Key.gif");
			break;

		}
		f.setIcon(f_s);
	}

	private Case getCase(int x) {
		return (Case) this.grid.getComponent(x);
	}

	/**
	 * 
	 * This method move a sprite on the grid from a point to another. To get the
	 * component Case we must calculate his number. Then we retrieve the case
	 * and we swap it with the next case. If the sprite moving is the pacman, we
	 * remove the gums when he walk through them.
	 * 
	 * @author RIETZ Vincent
	 * 
	 * @param g
	 *            The game initialised
	 * @param oldP
	 *            The origin point of the sprite
	 * @param newP
	 *            The next point of the sprite
	 * 
	 * 
	 */
	public void move(Characters character, Point oldP, char oldC) {
		int numCase = (int) (oldP.y + length * oldP.x); // number of its case
		int numNextCase = (int) (character.getPosition().y + length * character.getPosition().x);
		String sprite = "sprites/" + theme;
		switch (oldC) {
		case 'g':
			sprite += "/PacGum.gif";
			break;
		case 'S':
			sprite += "/Super pacgum.gif";
			break;
		case 'C':
			sprite += "/Cherry.gif";
			break;
		case 's':
			sprite += "/Strawberry.gif";
			break;
		case 'O':
			sprite += "/Orange.gif";
			break;
		case 'A':
			sprite += "/Apple.gif";
			break;
		case 'M':
			sprite += "/Melon.gif";
			break;
		case 'b':
			sprite += "/Galboss.gif";
			break;
		case 'B':
			sprite += "/Bell.gif";
			break;
		case 'K':
			sprite += "/Key.gif";
			break;
		case 'D':
			sprite += "/door.gif";
			break;
		}
		getCase(numCase).update(sprite);
		getCase(numNextCase).update(character.sprite());
	}

	/**
	 * This method puts a fruit on a cell, we erase the cell and we put the
	 * fruit sprite in it. To determine which fruit we need to put, a switch is
	 * used in order to retrieve the good sprite.
	 * 
	 * @author RIETZ Vincent
	 * 
	 * @param position
	 *            The position of the fruit
	 * @param fruit
	 *            The type of the fruit
	 * 
	 */
	public void putFruit(Point position, char fruit) {
		int numCase = (int) (position.getX() * length + position.getY());
		String sprite = "sprites/" + theme;
		switch (fruit) {
		case 'C':
			sprite += "/Cherry.gif";
			break;
		case 's':
			sprite += "/Strawberry.gif";
			break;
		case 'O':
			sprite += "/Orange.gif";
			break;
		case 'A':
			sprite += "/Apple.gif";
			break;
		case 'M':
			sprite += "/Melon.gif";
			break;
		case 'b':
			sprite += "/Galboss.gif";
			break;
		case 'B':
			sprite += "/Bell.gif";
			break;
		case 'K':
			sprite += "/Key.gif";
			break;
		}
		getCase(numCase).update(sprite);
	}

	/**
	 * This method removes a sprite from its cell;
	 * 
	 * @author RIETZ Vincent
	 * 
	 * @param position
	 *            The position of the sprite.
	 * 
	 */
	public void removeSprite(Point position) {
		int numCase = (int) (position.getX() * length + position.getY());
		getCase(numCase).update("");
	}

	/**
	 * This method reset the labyrinth to its original state. It scans the
	 * informations of the labyrinth and add the correct informations to the
	 * cases based on the informations.
	 * 
	 * @author RIETZ Vincent
	 * 
	 * @param g
	 *            The game initialised.
	 */
	public void resetLab(Game g) {
		int numCase = 0;
		for (int i = 0; i < g.getLab().length; ++i) {
			for (int j = 0; j < g.getLab()[0].length; ++j) {
				if (g.getLab()[i][j] != 'G' && g.getLab()[i][j] != 'P' && g.getLab()[i][j] != 'X'
						&& g.getLab()[i][j] != 'W') {
					String sprite = "sprites/" + theme;
					switch (g.getLab()[i][j]) {
					case 'g':
						sprite += "/PacGum.gif";
						break;
					case 'S':
						sprite += "/Super pacgum.gif";
						break;
					case 'C':
						sprite += "/Cherry.gif";
						break;
					case 's':
						sprite += "/Strawberry.gif";
						break;
					case 'O':
						sprite += "/Orange.gif";
						break;
					case 'A':
						sprite += "/Apple.gif";
						break;
					case 'M':
						sprite += "/Melon.gif";
						break;
					case 'b':
						sprite += "/Galboss.gif";
						break;
					case 'B':
						sprite += "/Bell.gif";
						break;
					case 'K':
						sprite += "/Key.gif";
						break;
					case 'D':
						sprite += "/door.gif";
						break;
					}
					getCase(numCase).update(sprite);
				}
				numCase++;
			}
		}
	}

	public void death(Point pacman) {
		int numCase = (int) (pacman.x * length + pacman.y);
		getCase(numCase).update("sprites/" + theme + "/PacMan_death.gif");
	}

}
