package en.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import en.master.Game;

public class GameScreen extends JLayeredPane implements KeyListener{
	Game g;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int) (screenSize.getHeight() * 0.95);
	JLabel grid;
	JLabel piclabel;
	int pacmanX=19;
	int pacmanY=14;
	
	public GameScreen(){
		
		this.setPreferredSize(new Dimension(4 * height /3, height));
	    this.setBounds(0, 0, 4 * height /3, height);
	    
	  //Image de fond
	    BufferedImage before = null;
	    int w = 0;
	    int h = 0;
	    ImageIcon after = null; //resized image
		try {
			before = ImageIO.read(new File("sprites/bh6.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		after=new ImageIcon(before.getScaledInstance(4 * height /3 - 4*height/10, height, Image.SCALE_DEFAULT));
		w = 4 * height /3 - 4*height/10;
	    h = height;
	    
	    piclabel = new JLabel(after);
	    piclabel.setBounds(2*height/10, 0, w, h);
	    piclabel.setOpaque(true);
	    
	    //##########Grid###########
	    // enlever l'initialisation du jeu dès que possible
		g = new Game(); // load labyrinth
		g.initTest("labyrinths/labyrinth2.txt");
		grid = new JLabel(/*after*/);
		grid.setBackground(Color.BLACK);
	    grid.setBounds(2*height/10, -height/32, 4 * height /3 - 4*height/10, height);
	    grid.setOpaque(true);
	    grid.setLayout(new GridLayout(32,28));
	    Label l;
		String s = "";
		for (int i = 0; i < g.getLab().length; ++i) {
			for (int j = 0; j < g.getLab()[0].length; ++j) {
				s += g.getLab()[i][j];
				Case c = new Case(s.charAt(0));
				grid.add(c);
				s = "";
			}
		}
	    
	    grid.setVisible(true);
	    this.add(grid, new Integer(1), 0);
	   
	    addKeyListener(this);
	
	}
	
	public Case getCase(int x){
		return (Case) this.grid.getComponent(x);
		
	}
	
	public void moveRight(int x, int y){
		int numCase = x*28+y;
		
		JLabel label = new JLabel();
		Image img = new ImageIcon("sprites/classic/PacMan_right.gif").getImage();
		Image newimg = img.getScaledInstance(18, 18, Image.SCALE_DEFAULT);
		label = new JLabel(new ImageIcon(newimg));
		JLabel l = getCase(numCase+1).getLabel();

		getCase(numCase+1).removeAll(); //Enlever le JLabel de la case suivante
		getCase(numCase+1).add(label); // On y ajoute le nouveau JLabel
		getCase(numCase+1).setContent('P'); // Et on met à jour les attributs de la case
		
		getCase(numCase).removeAll();
		getCase(numCase).add(new JLabel(" "));
		getCase(numCase).setContent(' ');
		
		getCase(numCase).revalidate();
		getCase(numCase).repaint();
		getCase(numCase+1).revalidate();
		getCase(numCase+1).repaint();
	}
	
	public void moveLeft(int x, int y){
		int numCase = x*28+y;
		JLabel label = new JLabel();
		Image img = new ImageIcon("sprites/classic/PacMan_left.gif").getImage();
		Image newimg = img.getScaledInstance(18, 18, Image.SCALE_DEFAULT);
		label = new JLabel(new ImageIcon(newimg));
		JLabel l = getCase(numCase+1).getLabel();
		
		getCase(numCase).removeAll();
		getCase(numCase).add(new JLabel(" "));
		getCase(numCase).setContent(' ');

		getCase(numCase-1).removeAll();
		getCase(numCase-1).add(label);
		getCase(numCase-1).setContent('P');

		getCase(numCase).revalidate();
		getCase(numCase).repaint();
		getCase(numCase-1).revalidate();
		getCase(numCase-1).repaint();
	}
	
	public void moveUp(int x, int y){
		int numCase = x*28+y;
		JLabel label = new JLabel();
		Image img = new ImageIcon("sprites/classic/PacMan_up.gif").getImage();
		Image newimg = img.getScaledInstance(18, 18, Image.SCALE_DEFAULT);
		label = new JLabel(new ImageIcon(newimg));
		JLabel l = getCase(numCase+1).getLabel();
		
		getCase(numCase).removeAll();
		getCase(numCase).add(new JLabel(" "));
		getCase(numCase).setContent(' ');

		getCase(numCase-28).removeAll();
		getCase(numCase-28).add(label);
		getCase(numCase-28).setContent('P');

		getCase(numCase).revalidate();
		getCase(numCase).repaint();
		getCase(numCase-28).revalidate();
		getCase(numCase-28).repaint();
	}
	
	public void moveDown(int x, int y){
		int numCase = x*28+y;
		JLabel label = new JLabel();
		Image img = new ImageIcon("sprites/classic/PacMan_down.gif").getImage();
		Image newimg = img.getScaledInstance(18, 18, Image.SCALE_DEFAULT);
		label = new JLabel(new ImageIcon(newimg));
		JLabel l = getCase(numCase+1).getLabel();
		
		getCase(numCase).removeAll();
		getCase(numCase).add(new JLabel(" "));
		getCase(numCase).setContent(' ');

		getCase(numCase+28).removeAll();
		getCase(numCase+28).add(label);
		getCase(numCase+28).setContent('P');

		getCase(numCase).revalidate();
		getCase(numCase).repaint();
		getCase(numCase+28).revalidate();
		getCase(numCase+28).repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveRight(pacmanX,pacmanY);
            pacmanY++;            
        }
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveLeft(pacmanX,pacmanY);
            pacmanY--;
        }
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
            moveUp(pacmanX,pacmanY);
            pacmanX--;
            
        }
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moveDown(pacmanX,pacmanY);
            pacmanX++;
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
