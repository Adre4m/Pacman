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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
	private JPanel menu = new JPanel();
	private JPanel game = new JPanel();
	private JPanel highscore = new JPanel();
	private JPanel options = new JPanel();

	public Frame() {

		// create frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) (screenSize.getHeight() * 0.95);
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
				JLayeredPane set = gameScreen();
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

	private JLayeredPane gameScreen() {
	  
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) (screenSize.getHeight() * 0.95);
		
		JLayeredPane lpane = new JLayeredPane(); //Root panel
		lpane.setPreferredSize(new Dimension(4 * height /3, height));
        lpane.setBounds(0, 0, 4 * height /3, height);

        //##########Grid###########
        // enlever l'initialisation du jeu des que possible
		Game g = new Game(); // load labyrinth
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
        lpane.add(grid, new Integer(0), 0);
        lpane.add(piclabel, new Integer(1), 0);

		
		
		return lpane;
	}

	private JPanel options() {

		// Jpanel final qui sera retourner
		final JPanel o = new JPanel();
		o.setBackground(Color.BLACK);

		// titre du panel
		final JLabel title = new JLabel("Options :");

		// changement de theme
		final JButton theme = new JButton("Change Game's Theme");
		theme.setBackground(Color.BLACK);
		theme.setForeground(Color.YELLOW);
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
				choice.add(Box.createRigidArea(new Dimension(0, 225)));
				choice.add(r1);
				choice.add(r2);
				choice.add(r3);
				
				ButtonGroup th = new ButtonGroup();
		        th.add(r1);
		        th.add(r2);
		        th.add(r3);
		       
				JPanel title = new JPanel();
				title.setBackground(Color.BLACK);
				JLabel l = new JLabel("Choose a Theme");
				labelStyleB(l);
				title.add(l);

				final ImageIcon classic = new ImageIcon("sprites/classic.png");
				final ImageIcon sw = new ImageIcon("sprites/sw.png");
				final ImageIcon bh6 = new ImageIcon("sprites/bh6.png");
				
				final JLabel image = new JLabel ();
				image.setIcon(classic);
				
				class RadioButtonActionListener implements ActionListener {
					@Override
					public void actionPerformed(ActionEvent event) {
						JRadioButton button = (JRadioButton) event.getSource();
						if (button == r1) {
							image.setIcon(classic);
						} else if (button == r2) {
							image.setIcon(sw);
						} else if (button == r3) {
							image.setIcon(bh6);
						}
					}
				}
				
				RadioButtonActionListener actionListener = new RadioButtonActionListener();
				r1.addActionListener(actionListener);
				r2.addActionListener(actionListener);
				r3.addActionListener(actionListener);
							
				JPanel t = new JPanel();
				t.setBackground(Color.BLACK);
				t.setLayout(new BorderLayout());
				t.add(title, BorderLayout.NORTH);
				t.add(choice, BorderLayout.WEST);
				t.add(image, BorderLayout.CENTER);
				
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
				theme.setForeground(Color.BLUE);
				;
			}

			public void mouseExited(MouseEvent e) {
				theme.setForeground(Color.YELLOW);
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
		final JRadioButton easy = new JRadioButton("Easy", false);
		final JRadioButton normal = new JRadioButton("Normal", true);
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

		// musique final
		Box m = Box.createHorizontalBox();
		m.add(m1);
		m.add(m2);
		ButtonGroup so = new ButtonGroup();
        so.add(sound);
        so.add(noSound);

		final Box option = Box.createVerticalBox();
		option.add(Box.createRigidArea(new Dimension(0, 50)));
		theme.setAlignmentX(CENTER_ALIGNMENT);
		option.add(theme);
		option.add(Box.createRigidArea(new Dimension(0, 50)));
		controls.setAlignmentX(CENTER_ALIGNMENT);
		c.setAlignmentX(CENTER_ALIGNMENT);
		option.add(controls);
		option.add(c);
		option.add(Box.createRigidArea(new Dimension(0, 50)));
		difficulty.setAlignmentX(CENTER_ALIGNMENT);
		d.setAlignmentX(CENTER_ALIGNMENT);
		option.add(difficulty);
		option.add(d);
		option.add(Box.createRigidArea(new Dimension(0, 50)));
		music.setAlignmentX(CENTER_ALIGNMENT);
		m.setAlignmentX(CENTER_ALIGNMENT);
		option.add(music);
		option.add(m);
		option.add(Box.createRigidArea(new Dimension(0, 50)));

		// bouton retour
		final JButton back = new JButton("Back");
		buttonStyle(back);
		
		final JButton apply = new JButton ("Apply");
		buttonStyle(apply);

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				title.setVisible(false);
				option.setVisible(false);
				back.setVisible(false);
				apply.setVisible(false);
				o.add(menu());
			}
		});

		/*apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ajouter le flux
			}
		});*/
		
		Box button = Box.createHorizontalBox();
		button.add(apply);
		button.add(Box.createRigidArea(new Dimension(100, 0)));
		button.add(back);
		
		o.setLayout(new BorderLayout());
		o.add(titleStyle(title), BorderLayout.NORTH);
		o.add(option, BorderLayout.CENTER);
		o.add(button, BorderLayout.SOUTH);
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
