package en.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

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
		menu.add(new JButton ("Start Game"));
		menu.add(new JButton ("HighScore"));
		menu.add(new JButton ("Options"));
		return menu;
	}
	
	

}
