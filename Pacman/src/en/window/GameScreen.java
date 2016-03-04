package en.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
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
	Game g = new Game(); // load labyrinth
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int) (screenSize.getHeight() * 0.95);
	
	public GameScreen(){
		
		this.setPreferredSize(new Dimension(4 * height /3, height));
	    this.setBounds(0, 0, 4 * height /3, height);
	 
	    //##########Grid###########
	    // enlever l'initialisation du jeu des que possible
		
		g.initTest();
		
		JPanel grid = new JPanel();
		grid.setBackground(Color.BLACK);
	    grid.setBounds(0, 0, 4 * height /3 - 150, height - 50);
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
	    
	    
	    BufferedImage sprite = null;
		try {
			sprite = ImageIO.read(new File("sprites/bh6.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel piclabel = new JLabel(new ImageIcon(sprite));
	
	    piclabel.setBounds(100, 100, 500, 575);
	    piclabel.setOpaque(true);
	    this.add(grid, new Integer(0), 0);
	    this.add(piclabel, new Integer(1), 0);
	
	}
}
