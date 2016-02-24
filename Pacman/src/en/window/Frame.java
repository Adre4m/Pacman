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
import javax.swing.ButtonGroup;
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
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int) (screenSize.getHeight() * 0.95);
	private JLabel labelImage = new JLabel();

	public Frame() {

		// create frame
		this.setTitle("Pacman");
		this.setSize((4 * height) / 3, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// change frame's icon
		try {
			this.setIconImage(ImageIO.read(new File(
					"sprites/classic/PacMan_right.gif")));
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
		while (true)
			;
	}

	private JPanel menu() {

		final JPanel l = logo();

		JPanel button = new JPanel();
		button.setBackground(Color.BLACK);
		button.setLayout(new FlowLayout(FlowLayout.CENTER));
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

			}

			public void mouseExited(MouseEvent e) {
				start.setForeground(Color.BLUE);

			}
		});

		score.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				score.setForeground(Color.YELLOW);

			}

			public void mouseExited(MouseEvent e) {
				score.setForeground(Color.BLUE);

			}
		});

		opt.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				opt.setForeground(Color.YELLOW);

			}

			public void mouseExited(MouseEvent e) {
				opt.setForeground(Color.BLUE);

			}
		});

		menu.add(start);
		menu.add(score);
		menu.add(opt);
		button.add(menu);

		JPanel openScreen = new JPanel();
		openScreen.setLayout(new BorderLayout());
		openScreen.add(l, BorderLayout.NORTH);
		openScreen.add(button, BorderLayout.CENTER);
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
		logo.setBackground(Color.BLACK);
		logo.setLayout(new FlowLayout(FlowLayout.CENTER));
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

		// Jpanel final qui sera retourner
		final JPanel o = new JPanel();
		// o.setPreferredSize(new Dimension((4 * height) / 3, height - 50));
		o.setBackground(Color.BLACK);

		// titre du panel
		final JLabel title = new JLabel("Options :");

		// changement de theme
		final JButton theme = new JButton("Change Game's Theme");
		theme.setBackground(Color.BLACK);
		theme.setForeground(Color.WHITE);
		theme.setFont(new java.awt.Font("Consolas", 1, 24));

		theme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// pop-up
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
				
				ButtonGroup th = new ButtonGroup();
		        th.add(r1);
		        th.add(r2);
		        th.add(r3);
		        
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

		// control
		JLabel controls = new JLabel("Controls");
		labelStyleW(controls);
		final JRadioButton keyboard = new JRadioButton("Keyboard", true);
		final JRadioButton mouse = new JRadioButton("Mouse", false);
		radioStyle(keyboard);
		radioStyle(mouse);
		Box c = Box.createVerticalBox();
		c.add(keyboard);
		c.add(mouse);
		ButtonGroup ctrl = new ButtonGroup();
        ctrl.add(keyboard);
        ctrl.add(mouse);

		// difficulte
		JLabel difficulty = new JLabel("Difficulty");
		labelStyleW(difficulty);
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
		ButtonGroup dif = new ButtonGroup();
        dif.add(easy);
        dif.add(normal);
        dif.add(hard);

		// musique
		JLabel music = new JLabel("Music");
		labelStyleW(music);

		// son
		final JRadioButton sound = new JRadioButton("", true);
		radioStyle(sound);
		JLabel image = new JLabel();
		ImageIcon l = new ImageIcon("sprites/sound.png");
		image.setIcon(l);
		Box s = Box.createHorizontalBox();
		s.add(sound);
		s.add(image);
		JPanel m1 = new JPanel();
		m1.setBackground(Color.BLACK);
		m1.add(s);

		// pas de son
		final JRadioButton noSound = new JRadioButton("", true);
		radioStyle(noSound);
		JLabel image1 = new JLabel();
		ImageIcon l1 = new ImageIcon("sprites/No_sound.png");
		image1.setIcon(l1);
		Box ns = Box.createHorizontalBox();
		ns.add(noSound);
		ns.add(image1);
		JPanel m2 = new JPanel();
		m2.setBackground(Color.BLACK);
		m2.add(ns);

		// jpanel musique final
		JPanel m = new JPanel();
		m.setBackground(Color.BLACK);
		m.setLayout(new BorderLayout());
		m.add(music, BorderLayout.NORTH);
		m.add(m1, BorderLayout.WEST);
		m.add(m2, BorderLayout.CENTER);
		ButtonGroup so = new ButtonGroup();
        so.add(sound);
        so.add(noSound);

		final Box option = Box.createVerticalBox();
		option.add(theme);
		option.add(controls);
		option.add(c);
		option.add(difficulty);
		option.add(d);
		option.add(m);

		// bouton retour
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

		o.add(titleStyle(title));
		o.add(option);
		o.add(back);
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

	private JPanel titleStyle(JLabel l) {
		JPanel t = new JPanel();
		t.setBackground(Color.BLACK);
		l.setBackground(Color.BLACK);
		l.setForeground(Color.BLUE);
		l.setFont(new java.awt.Font("Consolas", 1, 36));
		t.add(l);
		return t;
	}
}
