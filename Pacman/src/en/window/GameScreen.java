package en.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import en.master.Game;
import en.master.Stream;
import en.master.characters.Characters;

/**
 * 
 * @author RIETZ Vincent
 *
 */
public class GameScreen extends JLayeredPane /*implements KeyListener*/{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Game g;
	String theme;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int) (screenSize.getHeight() * 0.95);
	JLabel grid;
	JLabel piclabel;
	
	/**
	 * 
	 * This is the GameScreen constructor
	 * 
	 * @author RIETZ Vincent
	 * 
	 */
	public GameScreen() {

		this.setPreferredSize(new Dimension(4 * height / 3, height));
		this.setBounds(0, 0, 4 * height / 3, height);
		
		//theme
		switch(Stream.readOptions()[0]){
		case 0: theme = "classic"; 		
			break;
		case 1: theme = "sw"; 		
			break;
		case 3: theme = "zelda"; 		
			break;
		}
		
		// ##########Grid###########
		// enlever l'initialisation du jeu dès que possible
		g = new Game(); // load labyrinth
		g.init("labyrinths/labyrinth.txt");
		grid = new JLabel();
		grid.setBackground(Color.BLACK);
		
		//set size
		grid.setBounds(2 * height / 10, -height / 32, 4 * height / 3 - 4 * height / 10, height);
		grid.setOpaque(true);
		grid.setLayout(new GridLayout(32, 28));
		
		//load cases
		String s = "";
		int number = 0; // case's numbers
//		ControlsMouse ctrlMouse = new ControlsMouse(this); //MouseListener
		for (int i = 0; i < g.getLab().length; ++i) {
			for (int j = 0; j < g.getLab()[0].length; ++j) {
				s += g.getLab()[i][j];
				Case c = new Case(this, s.charAt(0), number);
				grid.add(c);
				number++;
				s = "";
				
//				c.addMouseListener(ctrlMouse);
			}
		}

		grid.setVisible(true);
		this.add(grid, new Integer(1), 0);
		
//		addKeyListener(this);

	}

	private Case getCase(int x) {
		return (Case) this.grid.getComponent(x);

	}

	// movements
	/**
	 * 
	 * This method move a sprite on the grid from a point to another.
	 * To get the component Case we must calculate his number. Then we retrieve the case and we 
	 * swap it with the next case.
	 * If the sprite moving is the pacman, we remove the gums when he walk through them.
	 * 
	 * @author RIETZ Vincent
	 * 
	 * @param g
	 *            The game initialised
	 * @param oldP
	 * 			  The origin point of the sprite
	 * @param newP
	 * 			  The next point of the sprite
	 * 			
	 * 
	 */
	public void move(Game g, Point oldP, Point newP) {
		int numCase = (int) (oldP.getY() + 28 * oldP.getX()); // number of its case
		int numNextCase= (int) (newP.getY() + 28 * newP.getX());
		Image sprite=null;
		String contentCase=""; 
		char c = getCase(numCase).getContent(); 
		contentCase = getSprite(numCase); //to know which sprite we have to load
		sprite = new ImageIcon(contentCase).getImage(); //load the sprite

		JLabel label = new JLabel();
		Image resizedSprite = sprite.getScaledInstance(18, 18, Image.SCALE_DEFAULT);
		label = new JLabel(new ImageIcon(resizedSprite));
		
		switch(c){
		case 'P':
			getCase(numCase).removeAll();
			getCase(numCase).add(new JLabel(" "));
			getCase(numCase).setContent(' ');

			getCase(numNextCase).removeAll(); // Remove JLabel on the next case
			getCase(numNextCase).add(label); // We add the new JLabel
			getCase(numNextCase).setContent('P'); // We update the informations on the case

			getCase(numCase).revalidate();
			getCase(numCase).repaint();
			getCase(numNextCase).revalidate();
			getCase(numNextCase).repaint();
			break;
		case 'G':
			if(getCase(numNextCase).getContent()=='g'){
				getCase(numCase).removeAll();
				JLabel label2 = new JLabel();
				sprite = new ImageIcon("sprites/" + theme + "/PacGum.gif").getImage();
				resizedSprite = sprite.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
				label2.setIcon(new ImageIcon(resizedSprite));
				getCase(numCase).add(label2);
				getCase(numCase).setContent('g');

				getCase(numNextCase).removeAll(); // Remove JLabel on the next case
				getCase(numNextCase).add(label); // We add the new JLabel
				getCase(numNextCase).setContent('G'); // We update the informations on the case

				getCase(numCase).revalidate();
				getCase(numCase).repaint();
				getCase(numNextCase).revalidate();
				getCase(numNextCase).repaint();
			}
			else{
				getCase(numCase).removeAll();
				getCase(numCase).add(new JLabel(" "));
				getCase(numCase).setContent(' ');

				getCase(numNextCase).removeAll(); // Remove JLabel on the next case
				getCase(numNextCase).add(label); // We add the new JLabel
				getCase(numNextCase).setContent('G'); // We update the informations on the case
														

				getCase(numCase).revalidate();
				getCase(numCase).repaint();
				getCase(numNextCase).revalidate();
				getCase(numNextCase).repaint();
			}
			
			break;
		}
			
	}
	
	/**
	 * This method puts a fruit on a cell, we erase the cell and we put the fruit sprite in it.
	 * To determine which fruit we need to put, a switch is used in order to retrieve the good sprite.
	 * 
	 * @author RIETZ Vincent
	 * 
	 * @param position
	 * 				The position of the fruit
	 * @param fruit
	 * 				The type of the fruit
	 * 			
	 */
	public void putFruit(Point position, char fruit){
		int numCase = (int) (position.getX() * 28 + position.getY());
		Image sprite = null;
		switch(fruit){
		case 'C': sprite = new ImageIcon("sprites/" + theme + "/Cherry.gif").getImage();
			break;
		case 's': sprite = new ImageIcon("sprites/" + theme + "/Strawberry.gif").getImage();
			break;
		case 'O': sprite = new ImageIcon("sprites/" + theme + "/Orange.gif").getImage();
			break;
		case 'A': sprite = new ImageIcon("sprites/" + theme + "/Apple.gif").getImage();
			break;
		case 'M': sprite = new ImageIcon("sprites/" + theme + "/Melon.gif").getImage();
			break;
		case 'b': sprite = new ImageIcon("sprites/" + theme + "/Galboss.gif").getImage();
			break;
		case 'B': sprite = new ImageIcon("sprites/" + theme + "/Bell.gif").getImage();
			break;
		case 'K': sprite = new ImageIcon("sprites/" + theme + "/Key.gif").getImage();
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
	 * This method removes a sprite from  its cell;
	 * 
	 * @author RIETZ Vincent
	 * 
	 * @param position
	 * 				The position of the sprite.
	 * 
	 */
	public void removeSprite(Point position){
		int numCase = (int) (position.getX() * 28 + position.getY());
		getCase(numCase).removeAll();
		getCase(numCase).add(new JLabel(" "));
		getCase(numCase).setContent(' ');
		getCase(numCase).revalidate();
		getCase(numCase).repaint();
	}
	
	/**
	 * This method retrieves the sprite that matches with the cell number.
	 * The point of the cell is calculated in order to compare it with the characters coordinates.
	 * When it finds a match, it retrieves the sprite.
	 * 
	 * @author RIETZ Vincent
	 * 
	 * @param number
	 * 				The number of the cell.
	 * 
	 */
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
	
	/**
	 * This method reset the labyrinth to its original state.
	 * It scans the informations of the labyrinth and add the correct informations to the cases
	 * based on the informations.
	 * 
	 * @author RIETZ Vincent
	 * 
	 * @param g
	 * 				The game initialised.
	 */
	public void resetLab(Game g){
		int numCase = 0; // case's numbers
		String s="";
		JLabel label = null;
		Image img = null;
		for (int i = 0; i < g.getLab().length; ++i) {
			for (int j = 0; j < g.getLab()[0].length; ++j) {
				s += g.getLab()[i][j];
				
				switch(s.charAt(0)){
				case ' ':
					label = new JLabel(" ");
					img=null;
					break;
				case 'D':
					label = new JLabel();
					img = new ImageIcon("sprites/" + theme + "/wall.gif").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
					
					break;
				case 'X':
					label = new JLabel();
					img = new ImageIcon("sprites/" + theme + "/wall.gif").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);

					break;
				case 'g':
					label = new JLabel();
					img = new ImageIcon("sprites/" + theme + "/PacGum.gif").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
					break;
				case 'S':
					label = new JLabel();
					img = new ImageIcon("sprites/" + theme + "/Super pacgum.gif").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
					break;
				case 'P':
					label = new JLabel();
					img = new ImageIcon("sprites/" + theme + "/PacMan_right.gif").getImage().getScaledInstance(18, 18, Image.SCALE_DEFAULT);
					break;
				case 'G':
					label = new JLabel();
					img = new ImageIcon(getSprite(numCase)).getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT);
					break;
				default:
					break;
				}
				
				if(!(img==null))label.setIcon(new ImageIcon(img));
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
	

	// ############# Keylisteners ###############
//	@Override
//	public void keyPressed(KeyEvent e) {
//		System.out.println("paf");
//		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//			move(pacmanX,pacmanY,'r');
//
//			
//		}
//		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//			move(pacmanX,pacmanY,'l');
//		}
//
//		if (e.getKeyCode() == KeyEvent.VK_UP) {
//			move(pacmanX,pacmanY,'u');
//		}
//		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//			move(pacmanX,pacmanY,'d');
//
//		}
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void keyTyped(KeyEvent e) {
//		// TODO Auto-generated method stub
//
//	}


}
