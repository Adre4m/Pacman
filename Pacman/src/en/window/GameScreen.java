package en.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import en.master.Game;

public class GameScreen extends JLayeredPane{
	Game g;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int) (screenSize.getHeight() * 0.95);
	JPanel grid;
	JLabel piclabel;
	
	public GameScreen(){
		
		this.setPreferredSize(new Dimension(4 * height /3, height));
	    this.setBounds(0, 0, 4 * height /3, height);
	 
	    //##########Grid###########
	    // enlever l'initialisation du jeu dès que possible
		g = new Game(); // load labyrinth
		g.initTest();
		grid = new JPanel();
		grid.setBackground(Color.BLACK);
	    grid.setBounds(2*height/10, -15, 4 * height /3 - 4*height/10, height);
	    grid.setOpaque(true);
	    grid.setLayout(new GridLayout(32,28));
	    
		String s = "";
		for (int i = 0; i < g.getLab().length; ++i) {
			for (int j = 0; j < g.getLab()[0].length; ++j) {
				s += g.getLab()[i][j];
				grid.add(new Label(s));
				s = "";
			}
		}
		//######################
	    
	    //Image de fond
	    BufferedImage before = null;
	    int w = 0;
	    int h = 0;
	    Image after = null; //resized image
		try {
			before = ImageIO.read(new File("sprites/bh6.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		after=before.getScaledInstance(4 * height /3 - 4*height/10, height, Image.SCALE_DEFAULT);
		w = 4 * height /3 - 4*height/10;
	    h = height;

	    piclabel = new JLabel(new ImageIcon(after));
	    piclabel.setBounds(2*height/10, 0, w, h);
	    piclabel.setOpaque(true);
	    this.add(grid, new Integer(0), 0);
	    this.add(piclabel, new Integer(1), 0);
	
	}
}
