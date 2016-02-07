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
import javax.swing.JPanel;

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
				  System.out.println("COUCOU");
				  JFrame gameScreen = new JFrame();
				  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				  int height = (int) (screenSize.getHeight() * 0.90);
				  gameScreen.setTitle("Pacman");
				  gameScreen.setSize((4 * height) / 3, height);
				  gameScreen.setResizable(false);
				  gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				  gameScreen.setLocationRelativeTo(null);
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
