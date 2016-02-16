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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import en.master.Pacman;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

		this.setContentPane(menu());
		this.setVisible(true);
		while (true)
			;
	}

	private JPanel menu() {

		final JPanel l = logo();

		JPanel button = new JPanel();
		button.setBackground(null);
		FlowLayout f = new FlowLayout();
		f.setAlignment(f.CENTER);
		button.setLayout(f);
		final Box menu = Box.createVerticalBox();
		final JButton start = new JButton("Start Game");
		final JButton score = new JButton("Highscores");
		final JButton opt = new JButton("Options");
		buttonStyle(start);
		buttonStyle(score);
		buttonStyle(opt);

		start.addActionListener(new ActionListener() { // Open game
			public void actionPerformed(ActionEvent e) {
				JPanel set = gameScreen();
				start.setVisible(false);
				score.setVisible(false);
				opt.setVisible(false);
				l.setVisible(false);
				menu.add(set);
			}
		});

		start.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				start.setForeground(Color.YELLOW);
				;
			}

			public void mouseExited(MouseEvent e) {
				start.setForeground(Color.BLUE);
				;
			}
		});

		score.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				score.setForeground(Color.YELLOW);
				;
			}

			public void mouseExited(MouseEvent e) {
				score.setForeground(Color.BLUE);
				;
			}
		});

		opt.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				opt.setForeground(Color.YELLOW);
				;
			}

			public void mouseExited(MouseEvent e) {
				opt.setForeground(Color.BLUE);
				;
			}
		});

		menu.add(start);
		menu.add(score);
		menu.add(opt);
		button.add(menu);

		JPanel openScreen = new JPanel();
		openScreen.setBackground(Color.BLACK);
		openScreen.setLayout(new BorderLayout());
		openScreen.add(l, BorderLayout.NORTH);
		openScreen.add(button, BorderLayout.CENTER);
		openScreen.add(copyright(), BorderLayout.SOUTH);

		return openScreen;
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

	private JPanel gameScreen() {
		Game g = new Game(); // load labyrinth
		g.init();
		JPanel set = new JPanel();

		set.setBackground(Color.BLACK);

		GridLayout f = new GridLayout(g.getLab().length, g.getLab()[0].length);
		set.setLayout(f);

		String s = "";
		for (int i = 0; i < g.getLab().length; ++i) {
			for (int j = 0; j < g.getLab()[0].length; ++j) {
				s += g.getLab()[i][j];
				 set.add(new Button(s));
				s = "";
			}
		}
		return set;
	}
	
}
