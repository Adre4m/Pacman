package en.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import en.controls.ControlsMouse;
import en.master.Game;

/**
 * 
 * @author RIETZ Vincent
 *
 */
public class GameScreen extends JLayeredPane implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Game g;
	String theme = "zelda";
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int) (screenSize.getHeight() * 0.95);
	JLabel grid;
	JLabel piclabel;
	int pacmanX = 14;
	

	int pacmanY = 19;

	public GameScreen() {

		this.setPreferredSize(new Dimension(4 * height / 3, height));
		this.setBounds(0, 0, 4 * height / 3, height);

		// Image de fond
		BufferedImage before = null;
		int w = 0;
		int h = 0;
		ImageIcon after = null; // resized image
		try {
			before = ImageIO.read(new File("sprites/bh6.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		after = new ImageIcon(before.getScaledInstance(4 * height / 3 - 4 * height / 10, height, Image.SCALE_DEFAULT));
		w = 4 * height / 3 - 4 * height / 10;
		h = height;

		piclabel = new JLabel(after);
		piclabel.setBounds(2 * height / 10, 0, w, h);
		piclabel.setOpaque(true);

		// ##########Grid###########
		// enlever l'initialisation du jeu dès que possible
		g = new Game(); // load labyrinth
		g.init("labyrinths/labyrinth2.txt");
		grid = new JLabel(/* after */);
		grid.setBackground(Color.BLACK);
		grid.setBounds(2 * height / 10, -height / 32, 4 * height / 3 - 4 * height / 10, height);
		grid.setOpaque(true);
		grid.setLayout(new GridLayout(32, 28));
		// Label l;
		String s = "";
		int number = 0; // case's numbers
		ControlsMouse ctrlMouse = new ControlsMouse(this);
		for (int i = 0; i < g.getLab().length; ++i) {
			for (int j = 0; j < g.getLab()[0].length; ++j) {
				s += g.getLab()[i][j];
				Case c = new Case(s.charAt(0), number);
				c.addMouseListener(ctrlMouse);
				grid.add(c);
				number++;
				s = "";
			}
		}

		grid.setVisible(true);
		this.add(grid, new Integer(1), 0);
		
		addKeyListener(this);

	}
	
	public int getPacmanX() {
		return pacmanX;
	}

	public int getPacmanY() {
		return pacmanY;
	}

	public Case getCase(int x) {
		return (Case) this.grid.getComponent(x);

	}

	// movements
	public boolean move(int x, int y, char direction) {
		boolean didItWork = false; // Indicates if the move is successful
		int numCase = y * 28 + x; // number of its case
		int numNextCase=0;
		Image sprite=null;
		switch(direction){
		case 'r':
			if (x == 27)
				numNextCase = numCase - 27;
			else
				numNextCase = numCase + 1;
			sprite = new ImageIcon("sprites/" + theme + "/PacMan_right.gif").getImage();
			break;
		case 'l':
			if (x == 0)
				numNextCase = numCase + 27;
			else
				numNextCase = numCase - 1;
			sprite = new ImageIcon("sprites/" + theme + "/PacMan_left.gif").getImage();
			break;
		case 'u':
			if (y == 0)
				numNextCase = numCase + 28 * 31;
			else
				numNextCase = numCase - 28;
			sprite = new ImageIcon("sprites/" + theme + "/PacMan_up.gif").getImage();
			break;
		case 'd':
			if (y == 31)
				numNextCase = numCase - 28 * 31;
			else
				numNextCase = numCase + 28;
			sprite = new ImageIcon("sprites/" + theme + "/PacMan_down.gif").getImage();
			break;
		}
		
		if (getCase(numNextCase).getContent() != 'X' && getCase(numNextCase).getContent() != 'G') {
			JLabel label = new JLabel();
			Image img = new ImageIcon("sprites/" + theme + "/PacMan_right.gif").getImage();
			Image resizedSprite = sprite.getScaledInstance(18, 18, Image.SCALE_DEFAULT);
			label = new JLabel(new ImageIcon(resizedSprite));

			getCase(numCase).removeAll();
			getCase(numCase).add(new JLabel(" "));
			getCase(numCase).setContent(' ');

			getCase(numNextCase).removeAll(); // Enlever le JLabel de la case
												// suivante
			getCase(numNextCase).add(label); // On y ajoute le nouveau JLabel
			getCase(numNextCase).setContent('P'); // Et on met à jour les
													// attributs de la case

			getCase(numCase).revalidate();
			getCase(numCase).repaint();
			getCase(numNextCase).revalidate();
			getCase(numNextCase).repaint();
			switch(direction){
			case 'r':
				if (x != 27)
					pacmanX++;
				else
					pacmanX = 0;
				break;
			case 'l':
				if (x != 0)
					pacmanX--;
				else
					pacmanX = 27;
				break;
			case 'u':
				if (y != 0)
					pacmanY--;
				else
					pacmanY = 31;
				break;
			case 'd':
				if (y != 31)
					pacmanY++;
				else
					pacmanY = 0;
				break;
			}
			
			didItWork = true;
		}

		return didItWork;
	}

	
	

	// ############# Keylisteners ###############
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//			moveRight(pacmanX, pacmanY);
			move(pacmanX,pacmanY,'r');
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//			moveLeft(pacmanX, pacmanY);
			move(pacmanX,pacmanY,'l');
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
//			moveUp(pacmanX, pacmanY);
			move(pacmanX,pacmanY,'u');
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//			moveDown(pacmanX, pacmanY);
			move(pacmanX,pacmanY,'d');

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}


}
