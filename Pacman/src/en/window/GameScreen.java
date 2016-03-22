package en.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int) (screenSize.getHeight() * 0.95);
	JPanel grid;
	JLabel piclabel;
	JLabel l;
	JLabel sco;
	JLabel f;

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
		case 3:
			theme = "zelda";
			break;
		}

		// ##########Grid###########
		grid = new JPanel();
		grid.setBackground(Color.BLACK);

		grid.setOpaque(true);
		grid.setLayout(new GridLayout(32, 28));

		// load cases
		String s = "";
		int number = 0; // case's numbers
		for (int i = 0; i < game.getLab().length; ++i) {
			for (int j = 0; j < game.getLab()[0].length; ++j) {
				s += game.getLab()[i][j];
				Case c = new Case(this, game, s.charAt(0), number);
				grid.add(c);
				number++;
				s = "";
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
		int numCase = (int) (oldP.y + 28 * oldP.x); // number of its case
		int numNextCase = (int) (character.getPosition().y + 28 * character.getPosition().x);
		getCase(numNextCase).update(character.sprite());
		String sprite ="";
		switch(oldC) {
		case 'g':
			sprite = "sprites/" + theme + "/PacGum.gif";
			break;
		case 'S':
			sprite = "sprites/" + theme + "/Super pacgum.gif";
			break;
		case 'C':
			sprite = "sprites/" + theme + "/Cherry.gif";
			break;
		case 's':
			sprite = "sprites/" + theme + "/Strawberry.gif";
			break;
		case 'O':
			sprite = "sprites/" + theme + "/Orange.gif";
			break;
		case 'A':
			sprite = "sprites/" + theme + "/Apple.gif";
			break;
		case 'M':
			sprite = "sprites/" + theme + "/Melon.gif";
			break;
		case 'b':
			sprite = "sprites/" + theme + "/Galboss.gif";
			break;
		case 'B':
			sprite = "sprites/" + theme + "/Bell.gif";
			break;
		case 'K':
			sprite = "sprites/" + theme + "/Key.gif";
			break;
		}
		getCase(numCase).update(sprite);
		/*Image sprite = null;
		String contentCase = character.sprite(); // to know which sprite we have to
												// load
		sprite = new ImageIcon(contentCase).getImage(); // load the sprite

		JLabel label = new JLabel();
		Image resizedSprite = sprite.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
		label = new JLabel(new ImageIcon(resizedSprite));
		getCase(numCase).removeAll();
		getCase(numNextCase).removeAll();
		JLabel label2 = new JLabel();
		switch(oldC) {
		case 'g':
			sprite = new ImageIcon("sprites/" + theme + "/PacGum.gif").getImage();
			resizedSprite = sprite.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label2.setIcon(new ImageIcon(resizedSprite));
			getCase(numCase).add(label2);
			getCase(numCase).setContent(oldC);
			break;
		case 'S':
			sprite = new ImageIcon("sprites/" + theme + "/Super pacgum.gif").getImage();
			resizedSprite = sprite.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label2.setIcon(new ImageIcon(resizedSprite));
			getCase(numCase).add(label2);
			getCase(numCase).setContent(oldC);
			break;
		case 'C':
			sprite = new ImageIcon("sprites/" + theme + "/Cherry.gif").getImage();
			resizedSprite = sprite.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label2.setIcon(new ImageIcon(resizedSprite));
			getCase(numCase).add(label2);
			getCase(numCase).setContent(oldC);
			break;
		case 's':
			sprite = new ImageIcon("sprites/" + theme + "/Strawberry.gif").getImage();
			resizedSprite = sprite.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label2.setIcon(new ImageIcon(resizedSprite));
			getCase(numCase).add(label2);
			getCase(numCase).setContent(oldC);
			break;
		case 'O':
			sprite = new ImageIcon("sprites/" + theme + "/Orange.gif").getImage();
			resizedSprite = sprite.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label2.setIcon(new ImageIcon(resizedSprite));
			getCase(numCase).add(label2);
			getCase(numCase).setContent(oldC);
			break;
		case 'A':
			sprite = new ImageIcon("sprites/" + theme + "/Apple.gif").getImage();
			resizedSprite = sprite.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label2.setIcon(new ImageIcon(resizedSprite));
			getCase(numCase).add(label2);
			getCase(numCase).setContent(oldC);
			break;
		case 'M':
			sprite = new ImageIcon("sprites/" + theme + "/Melon.gif").getImage();
			resizedSprite = sprite.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label2.setIcon(new ImageIcon(resizedSprite));
			getCase(numCase).add(label2);
			getCase(numCase).setContent(oldC);
			break;
		case 'b':
			sprite = new ImageIcon("sprites/" + theme + "/Galboss.gif").getImage();
			resizedSprite = sprite.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label2.setIcon(new ImageIcon(resizedSprite));
			getCase(numCase).add(label2);
			getCase(numCase).setContent(oldC);
			break;
		case 'B':
			sprite = new ImageIcon("sprites/" + theme + "/Bell.gif").getImage();
			resizedSprite = sprite.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label2.setIcon(new ImageIcon(resizedSprite));
			getCase(numCase).add(label2);
			getCase(numCase).setContent(oldC);
			break;
		case 'K':
			sprite = new ImageIcon("sprites/" + theme + "/Key.gif").getImage();
			resizedSprite = sprite.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label2.setIcon(new ImageIcon(resizedSprite));
			getCase(numCase).add(label2);
			getCase(numCase).setContent(oldC);
			break;
		}
		getCase(numNextCase).setContent(character.toChar());
		getCase(numNextCase).add(label);
		getCase(numCase).revalidate();
		getCase(numCase).repaint();
		getCase(numNextCase).revalidate();
		getCase(numNextCase).repaint();*/
		/*if (oldC == 'g') {
			getCase(numNextCase).removeAll();
			JLabel label2 = new JLabel();
			sprite = new ImageIcon("sprites/" + theme + "/PacGum.gif").getImage();
			resizedSprite = sprite.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label2.setIcon(new ImageIcon(resizedSprite));
			getCase(numCase).add(label2);
			getCase(numCase).setContent('g'); // Remove JLabel on the next
												// case
			getCase(numNextCase).add(label); // We add the new JLabel
													// We update the
													// informations on the
													// case

			getCase(numCase).revalidate();
			getCase(numCase).repaint();
			getCase(numNextCase).revalidate();
			getCase(numNextCase).repaint();
		} else {
			getCase(numNextCase).removeAll();
			getCase(numCase).add(new JLabel(" "));
			getCase(numCase).setContent(' ');

			getCase(numNextCase).removeAll(); // Remove JLabel on the next
												// case
			getCase(numNextCase).add(label); // We add the new JLabel
			// We update the
													// informations on the
													// case

			getCase(numCase).revalidate();
			getCase(numCase).repaint();
			getCase(numNextCase).revalidate();
			getCase(numNextCase).repaint();
		}*/

		/*switch (c) {
		case 'P':
			getCase(numCase).removeAll();
			getCase(numCase).add(new JLabel(" "));
			getCase(numCase).setContent(' ');

			getCase(numNextCase).removeAll(); // Remove JLabel on the next case
			getCase(numNextCase).add(label); // We add the new JLabel
			getCase(numNextCase).setContent('P'); // We update the informations
													// on the case

			getCase(numCase).revalidate();
			getCase(numCase).repaint();
			getCase(numNextCase).revalidate();
			getCase(numNextCase).repaint();
			break;
		case 'G':

			break;
		}*/
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
		int numCase = (int) (position.getX() * 28 + position.getY());
		Image sprite = null;
		switch (fruit) {
		case 'C':
			sprite = new ImageIcon("sprites/" + theme + "/Cherry.gif").getImage();
			break;
		case 's':
			sprite = new ImageIcon("sprites/" + theme + "/Strawberry.gif").getImage();
			break;
		case 'O':
			sprite = new ImageIcon("sprites/" + theme + "/Orange.gif").getImage();
			break;
		case 'A':
			sprite = new ImageIcon("sprites/" + theme + "/Apple.gif").getImage();
			break;
		case 'M':
			sprite = new ImageIcon("sprites/" + theme + "/Melon.gif").getImage();
			break;
		case 'b':
			sprite = new ImageIcon("sprites/" + theme + "/Galboss.gif").getImage();
			break;
		case 'B':
			sprite = new ImageIcon("sprites/" + theme + "/Bell.gif").getImage();
			break;
		case 'K':
			sprite = new ImageIcon("sprites/" + theme + "/Key.gif").getImage();
			break;
		}
		Image resizedSprite = sprite.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		getCase(numCase).removeAll();
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(resizedSprite));
		getCase(numCase).add(label);
		getCase(numCase).setContent(fruit);

		getCase(numCase).revalidate();
		getCase(numCase).repaint();
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
		int numCase = (int) (position.getX() * 28 + position.getY());
		getCase(numCase).removeAll();
		getCase(numCase).add(new JLabel(" "));
		getCase(numCase).setContent(' ');
		getCase(numCase).revalidate();
		getCase(numCase).repaint();
	}

	/**
	 * This method retrieves the sprite that matches with the cell number. The
	 * point of the cell is calculated in order to compare it with the
	 * characters coordinates. When it finds a match, it retrieves the sprite.
	 * 
	 * @author RIETZ Vincent
	 * 
	 * @param number
	 *            The number of the cell.
	 * 
	 */
	public String getSprite(Game game, int number) {
		String res = "";
		int x = number % 28;
		int y = (number - x) / 28;
		Point p = new Point(y, x);
		Characters[] cara = game.characters;
		for (int i = 0; i < 5; i++) {
			if (cara[i].getPosition().equals(p)) {

				res = cara[i].sprite();
			}
		}
		return res;
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
		int numCase = 0; // case's numbers
		String s = "";
		JLabel label = null;
		Image img = null;
		for (int i = 0; i < g.getLab().length; ++i) {
			for (int j = 0; j < g.getLab()[0].length; ++j) {
				s += g.getLab()[i][j];

				switch (s.charAt(0)) {
				case ' ':
					label = new JLabel(" ");
					img = null;
					break;
				case 'D':
					label = new JLabel();
					img = new ImageIcon("sprites/" + theme + "/wall.gif").getImage().getScaledInstance(20, 20,
							Image.SCALE_DEFAULT);

					break;
				case 'X':
					label = new JLabel();
					img = new ImageIcon("sprites/" + theme + "/wall.gif").getImage().getScaledInstance(20, 20,
							Image.SCALE_DEFAULT);

					break;
				case 'g':
					label = new JLabel();
					img = new ImageIcon("sprites/" + theme + "/PacGum.gif").getImage().getScaledInstance(20, 20,
							Image.SCALE_DEFAULT);
					break;
				case 'S':
					label = new JLabel();
					img = new ImageIcon("sprites/" + theme + "/Super pacgum.gif").getImage().getScaledInstance(20, 20,
							Image.SCALE_DEFAULT);
					break;
				case 'P':
					label = new JLabel();
					img = new ImageIcon("sprites/" + theme + "/PacMan_right.gif").getImage().getScaledInstance(18, 18,
							Image.SCALE_DEFAULT);
					break;
				case 'G':
					label = new JLabel();
					img = new ImageIcon(getSprite(g, numCase)).getImage().getScaledInstance(15, 15,
							Image.SCALE_DEFAULT);
					break;
				default:
					break;
				}

				if (!(img == null))
					label.setIcon(new ImageIcon(img));
				getCase(numCase).removeAll();
				getCase(numCase).setContent(s.charAt(0));
				getCase(numCase).add(label);
				getCase(numCase).revalidate();
				getCase(numCase).repaint();
				numCase++;
				s = "";

			}
		}
	}

}
