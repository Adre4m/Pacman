package en.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) (screenSize.getHeight() * 0.90);
		this.setTitle("Pacman");
		this.setSize((4 * height) / 3, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		background = new JPanel();
		background.setBackground(Color.BLACK);
		
		FlowLayout f = new FlowLayout();
		f.setAlignment(f.CENTER);
		background.setLayout(f);
		background.add(menu());
		
		this.setContentPane(background);
		this.setVisible(true);
		while(true);
	}
	
	private Box menu () {
		Box menu = Box.createVerticalBox();
		JButton start = new JButton ("Start Game");
		JButton score = new JButton ("Highscore");
		JButton opt = new JButton ("Options");
		buttonStyle(start);
		buttonStyle(score);
		buttonStyle(opt);
		start.addActionListener(new ActionListener(){ //Open game
			public void actionPerformed (ActionEvent e) {
				  JFrame gameScreen = new JFrame(); //New game screen
				  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				  int height = (int) (screenSize.getHeight() * 0.90);
				  gameScreen.setTitle("Pacman party");
				  gameScreen.setSize((4 * height) / 3, height);
				  gameScreen.setResizable(false);
				  gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				  gameScreen.setLocationRelativeTo(null);
				  
				  Game g = new Game(); //load labyrinth
				  g.init();
				  JPanel set = new JPanel();
				  JLabel labyrinth = new JLabel(); //temporary
				  
				  set.setBackground(Color.BLACK);
				  FlowLayout f = new FlowLayout();
				  f.setAlignment(f.CENTER);
				  set.setLayout(f);
				  
				  String s = "<html>";
				  for (int i = 0; i < g.getLab().length; ++i) {
					  for (int j = 0; j < g.getLab()[0].length; ++j)
						  s += g.getLab()[i][j];
					  s += "<br>";
				  }
				  s+="</html>";
				  System.out.println(s);
				  labyrinth.setText(s);
				  labyrinth.setForeground(Color.BLUE);
				  
				  set.add(labyrinth);
				  gameScreen.add(set);
				  gameScreen.setContentPane(set);
				  gameScreen.setVisible(true);
				  
		    	}
		});
		menu.add(start);
		menu.add(score);
		menu.add(opt);
		return menu;
	}
	
	private void buttonStyle (JButton b){
		b.setBackground(Color.BLACK);
		b.setForeground(Color.BLUE);
		b.setBorder(null);
		b.setFont(new java.awt.Font("Consolas",1,36));
	}

}
