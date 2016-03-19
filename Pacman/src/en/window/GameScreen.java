package en.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import en.controls.ControlsMouse;
import en.master.Game;
import en.master.characters.Characters;

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
	String theme = "classic";
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int) (screenSize.getHeight() * 0.95);
	JLabel grid;
	JLabel piclabel;
	int pacmanX = 0;
	int pacmanY = 0;

	public GameScreen() {

		this.setPreferredSize(new Dimension(4 * height / 3, height));
		this.setBounds(0, 0, 4 * height / 3, height);

		// ##########Grid###########
		// enlever l'initialisation du jeu d�s que possible
		g = new Game(); // load labyrinth
		g.init("labyrinths/labyrinth2.txt");
		this.pacmanX = g.getPacmanX();
		this.pacmanY = g.getPacmanY();
		grid = new JLabel();
		grid.setBackground(Color.BLACK);
		grid.setBounds(2 * height / 10, -height / 32, 4 * height / 3 - 4 * height / 10, height);
		grid.setOpaque(true);
		grid.setLayout(new GridLayout(32, 28));
		// Label l;
		String s = "";
		int number = 0; // case's numbers
		ControlsMouse ctrlMouse = new ControlsMouse(this); //MouseListener
		for (int i = 0; i < g.getLab().length; ++i) {
			for (int j = 0; j < g.getLab()[0].length; ++j) {
				s += g.getLab()[i][j];
				Case c = new Case(this, s.charAt(0), number);
				grid.add(c);
				number++;
				s = "";
				
				c.addMouseListener(ctrlMouse);
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

	private Case getCase(int x) {
		return (Case) this.grid.getComponent(x);

	}

	// movements
	public boolean move(int x, int y, char direction) {
		boolean didItWork = false; // Indicates if the move is successful
		int numCase = x + 28 * y; // number of its case
		int numNextCase=0;
		Image sprite=null;
		String contentCase="";
		char c = getCase(x+28*y).getContent();
		contentCase=getSprite(x+28*y);
		
		switch(direction){
		case 'r':
			if (x == 27) numNextCase = numCase - 27;
			else numNextCase = numCase + 1;
			sprite = new ImageIcon(contentCase).getImage();
			break;
		case 'l':
			if (x == 0)numNextCase = numCase + 27;
			else numNextCase = numCase - 1;
			sprite = new  ImageIcon(contentCase).getImage();
			break;
		case 'u':
			if (y == 0) numNextCase = numCase + 28 * 31;
			else numNextCase = numCase - 28;
			sprite = new ImageIcon(contentCase).getImage();
			break;
		case 'd':
			if (y == 31) numNextCase = numCase - 28 * 31;
			else numNextCase = numCase + 28;
			sprite = new ImageIcon(contentCase).getImage();
			break;
		}
		System.out.println(contentCase);
		if (getCase(numNextCase).getContent() != 'X' && getCase(numNextCase).getContent() != 'G') {
			JLabel label = new JLabel();
			Image resizedSprite = sprite.getScaledInstance(18, 18, Image.SCALE_DEFAULT);
			label = new JLabel(new ImageIcon(resizedSprite));
			
			switch(c){
			case 'P':
				getCase(numCase).removeAll();
				getCase(numCase).add(new JLabel(" "));
				getCase(numCase).setContent(' ');

				getCase(numNextCase).removeAll(); // Enlever le JLabel de la case
													// suivante
				getCase(numNextCase).add(label); // On y ajoute le nouveau JLabel
				getCase(numNextCase).setContent('P'); // Et on met � jour les
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
				break;
			case 'G':
				System.out.println("G");
				if(getCase(numNextCase).getContent()=='g'){
					getCase(numCase).removeAll();
					JLabel label2 = new JLabel();
					sprite = new ImageIcon("sprites/" + theme + "/PacGum.gif").getImage();
					resizedSprite = sprite.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
					label2.setIcon(new ImageIcon(resizedSprite));
					getCase(numCase).add(label2);
					getCase(numCase).setContent('g');

					getCase(numNextCase).removeAll(); // Enlever le JLabel de la case
														// suivante
					getCase(numNextCase).add(label); // On y ajoute le nouveau JLabel
					getCase(numNextCase).setContent('G'); // Et on met � jour les
															// attributs de la case

					getCase(numCase).revalidate();
					getCase(numCase).repaint();
					getCase(numNextCase).revalidate();
					getCase(numNextCase).repaint();
				}
				else{
					getCase(numCase).removeAll();
					getCase(numCase).add(new JLabel(" "));
					getCase(numCase).setContent(' ');

					getCase(numNextCase).removeAll(); // Enlever le JLabel de la case
														// suivante
					getCase(numNextCase).add(label); // On y ajoute le nouveau JLabel
					getCase(numNextCase).setContent('G'); // Et on met � jour les
															// attributs de la case

					getCase(numCase).revalidate();
					getCase(numCase).repaint();
					getCase(numNextCase).revalidate();
					getCase(numNextCase).repaint();
				}
				
				break;
			}
			
			
			didItWork = true;
		}

		return didItWork;
	}

	public String getSprite(int number){
		String res="";
		int x=number%28;
		int y=(number-x)/28;
		Point p = new Point(y,x);
		Characters[] cara = g.characters;
		for(int i=0; i<5; i++){
			if(cara[i].getPosition().equals(p)){
				
				res=cara[i].sprite();
			}
		}
		return res;		
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
