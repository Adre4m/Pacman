package en.window;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import en.master.Game;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel background;

	public Frame() {

		// create frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) (screenSize.getHeight() * 0.90);
		this.setTitle("Pacman");
		this.setSize((4 * height) / 3, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// change frame's icon
		try {
			this.setIconImage(ImageIO
					.read(new File("sprites/PacMan_right.gif")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create frame background
		background = new JPanel();
		background.setBackground(Color.BLACK);
		background.setLayout(new BorderLayout());
		background.add(logo(), BorderLayout.NORTH);
		background.add(menu(), BorderLayout.CENTER);
		background.add(copyright(), BorderLayout.SOUTH);

		this.setContentPane(background);
		this.setVisible(true);
		while(true);
	}

	private JPanel menu() {
		JPanel button = new JPanel();
		button.setBackground(null);
		FlowLayout f = new FlowLayout();
		f.setAlignment(f.CENTER);
		button.setLayout(f);
		final Box menu = Box.createVerticalBox();
		final JButton start = new JButton("Start Game");
		final JButton score = new JButton("Highscore");
		final JButton opt = new JButton("Options");
		buttonStyle(start);
		buttonStyle(score);
		buttonStyle(opt);
		
		start.addActionListener(new ActionListener(){ //Open game
			   public void actionPerformed (ActionEvent e) {
				  JPanel set = gameScreen();
			      start.setVisible(false);
			      score.setVisible(false);
			      opt.setVisible(false);
			      menu.add(set);
			       }
			  });
		
		menu.add(start);
		menu.add(score);
		menu.add(opt);
		button.add(menu);
		return button;
	}

	private void buttonStyle(JButton b) {
		b.setBackground(Color.BLACK);
		b.setForeground(Color.BLUE);
		b.setBorder(null);
		b.setFont(new java.awt.Font("Consolas", 1, 36));
	}

	private JPanel logo() {
		JPanel logo = new JPanel();
		logo.setBackground(null);
		FlowLayout f = new FlowLayout();
		f.setAlignment(f.CENTER);
		logo.setLayout(f);
		JLabel logoImage = new JLabel();
		ImageIcon l = new ImageIcon("sprites/Logo.gif");
		Image img = l.getImage();
		Image newimg = img.getScaledInstance(700, 350, Image.SCALE_DEFAULT);
		logoImage.setIcon(new ImageIcon(newimg));
		logo.add(logoImage);
		return logo;
	}
	
	private JPanel gameScreen(){
		Game g = new Game(); //load labyrinth
	      g.init();
	      JPanel set = new JPanel();
	      
	      set.setBackground(Color.BLACK);
	      GridLayout f = new GridLayout(g.getLab().length, g.getLab()[0].length);
	      set.setLayout(f);
	      
	      String s="";
	      for (int i = 0; i < g.getLab().length; ++i) {
	       for (int j = 0; j < g.getLab()[0].length; ++j){
	    	   s+=g.getLab()[i][j];
	    	   set.add(new Button(s));
	    	   s="";
	       }
	      }

		return set;
	}
	
	private JPanel copyright() {
		JLabel name = new JLabel(
				"Done by BOURGEOIS Adrien, GRIGNON Lindsay, RIETZ Vincent");
		name.setForeground(Color.WHITE);
		JPanel j = new JPanel();
		j.setBackground(null);
		j.setLayout(new BorderLayout());
		j.add(name, BorderLayout.WEST);
		return j;
	}
}
