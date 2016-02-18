package en.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

import en.master.Game;

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

		JPanel background = new JPanel();
		background.setBackground(Color.BLACK);
		background.setLayout(new BorderLayout());
		background.add(copyright(), BorderLayout.SOUTH);
		background.add(menu());
		
		this.setContentPane(background);
		this.setVisible(true);
		while (true);
	}

	private JPanel menu() {

		final JPanel l = logo();

		JPanel button = new JPanel();
		button.setBackground(Color.BLACK);
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

		opt.addActionListener(new ActionListener() { // Open game
			public void actionPerformed(ActionEvent e) {
				start.setVisible(false);
				score.setVisible(false);
				opt.setVisible(false);
				l.setVisible(false);
				menu.add(options());
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
		//openScreen.add(copyright(), BorderLayout.SOUTH);

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
		// enlever l'initialisation du jeu des que possible
		Game g = new Game(); // load labyrinth
		g.initTest();
		JPanel set = new JPanel();

		set.setBackground(Color.BLACK);

		// GridLayout f = new GridLayout(g.getLab().length,
		// g.getLab()[0].length);
		GridLayout f = new GridLayout(32, 28);
		set.setLayout(f);

		String s = "";
		for (int i = 0; i < g.getLab().length; ++i) {
			for (int j = 0; j < g.getLab()[0].length; ++j) {
				s += g.getLab()[i][j];
				set.add(new Label(s));
				s = "";
			}
		}

		return set;
	}

	private JPanel options() {

		final JPanel o = new JPanel();
		o.setBackground(Color.BLACK);
		o.setLayout(new BorderLayout());

		final JLabel title = new JLabel("Options :");

		final JButton theme = new JButton("Change Game's Theme");
		theme.setBackground(Color.BLACK);
		theme.setForeground(Color.WHITE);
		theme.setBorder(null);
		theme.setFont(new java.awt.Font("Consolas", 1, 24));

		theme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final JRadioButton r1 = new JRadioButton("Classic", true);
				final JRadioButton r2 = new JRadioButton("Star Wars", false);
				final JRadioButton r3 = new JRadioButton("Big Hero 6", false);
				radioStyle(r1);
				radioStyle(r2);
				radioStyle(r3);
				Box choice = Box.createVerticalBox();
				choice.add(r1);
				choice.add(r2);
				choice.add(r3);

				JLabel l = new JLabel("Choose a Theme");
				labelStyleB(l);

				JPanel t = new JPanel();
				t.setBackground(Color.BLACK);
				t.setLayout(new BorderLayout());
				t.add(l, BorderLayout.NORTH);
				t.add(choice, BorderLayout.WEST);

				// change pop-up background color
				UIManager.put("OptionPane.background", Color.BLACK);
				UIManager.put("Panel.background", Color.BLACK);

				JOptionPane
						.showConfirmDialog(null, t, "",
								JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.PLAIN_MESSAGE);
			}
		});

		theme.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				theme.setForeground(Color.YELLOW);
				;
			}

			public void mouseExited(MouseEvent e) {
				theme.setForeground(Color.WHITE);
				;
			}
		});

		JLabel controls = new JLabel ("Controls");
		labelStyleW(controls);
		
		final JRadioButton keyboard = new JRadioButton("Keyboard", true);
		final JRadioButton mouse = new JRadioButton("Mouse", false);
		radioStyle(keyboard);
		radioStyle(mouse);
		Box c = Box.createVerticalBox();
		c.add(keyboard);
		c.add(mouse);		
		
		JLabel dif = new JLabel ("Difficulty");
		labelStyleW(dif);
		
		final JRadioButton easy = new JRadioButton("Easy", true);
		final JRadioButton normal = new JRadioButton("Normal", false);
		final JRadioButton hard = new JRadioButton("Hard", false);
		radioStyle(easy);
		radioStyle(normal);
		radioStyle(hard);
		Box d = Box.createVerticalBox();
		d.add(easy);
		d.add(normal);
		d.add(hard);
		
		JLabel music = new JLabel("Music");
		labelStyleW(music);
		
		//inserer 2 image en ligne
		
		final Box option = Box.createVerticalBox();
		option.add(theme);
		option.add(controls);
		option.add(c);
		option.add(dif);
		option.add(d);
		option.add(music);
		
		final JButton back = new JButton("Back");
		buttonStyle(back);
	
		back.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				title.setVisible(false);
				option.setVisible(false);
				back.setVisible(false);
				o.add(menu());
			}
		});
		
		o.add(frameTitle(title), BorderLayout.NORTH);
		o.add(option, BorderLayout.WEST);
		o.add(back, BorderLayout.SOUTH);
		return o;
	}

	private void radioStyle(JRadioButton r) {
		r.setBackground(Color.BLACK);
		r.setForeground(Color.WHITE);
		r.setFont(new java.awt.Font("Consolas", 1, 20));
	}

	private void labelStyleB(JLabel l) {
		l.setBackground(Color.BLACK);
		l.setForeground(Color.BLUE);
		l.setFont(new java.awt.Font("Consolas", 1, 25));
	}

	private void labelStyleW(JLabel l) {
		l.setBackground(Color.BLACK);
		l.setForeground(Color.WHITE);
		l.setFont(new java.awt.Font("Consolas", 1, 25));
	}
	
	private JPanel frameTitle(JLabel l) {
		JPanel t = new JPanel();
		t.setBackground(Color.BLACK);
		t.setLayout(new BorderLayout());
		l.setBackground(Color.BLACK);
		l.setForeground(Color.BLUE);
		l.setFont(new java.awt.Font("Consolas", 1, 36));
		t.add(l, BorderLayout.WEST);
		return t;
	}
}
