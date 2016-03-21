package en.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

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
import javax.swing.JTextField;
import javax.swing.UIManager;

import en.master.NodeScore;
import en.master.Stream;

/**
 * 
 * @author GRIGNON Lindsay
 *
 */
public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;

	// final Game g;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int) (screenSize.getHeight() * 0.95);

	public GameScreen set;

	/**
	 * 
	 * This is the Frame constructor
	 * 
	 * @author GRIGON Lindsay
	 * 
	 */
	public Frame() {

		// create frame
		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// int height = (int) (screenSize.getHeight() * 0.95);
		this.setTitle("Pacman");
		this.setSize((4 * height) / 3, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// change frame's icon
		try {
			this.setIconImage(ImageIO.read(new File("sprites/classic/PacMan_right.gif")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// musique de demarage
		/*
		 * URL url = Frame.class.getResource("Immortals - FOB.wav"); final
		 * AudioClip clip = Applet.newAudioClip(url);
		 * 
		 * // pour l'exécuter au moment ou la fenêtre s'ouvre
		 * this.addWindowListener(new WindowAdapter() {
		 * 
		 * @Override public void windowOpened(WindowEvent e) { clip.play(); }
		 * });
		 */

		menu();

		this.setVisible(true);
		while (true)
			;
	}

	/**
	 * 
	 * This method create the Game's menu with a gif logo and 3 buttons : Start,
	 * HighSCore, Options
	 * 
	 * @author GRIGNON Lindsay
	 * 
	 */
	private void menu() {

		final JPanel l = logo(); 

		final JPanel openScreen = new JPanel();
		openScreen.setBackground(Color.BLACK);

		final JPanel button = new JPanel();
		button.setBackground(Color.BLACK);
		button.setLayout(new FlowLayout(FlowLayout.CENTER));
		Box menu = Box.createVerticalBox();
		final JButton start = new JButton("Start Game");
		final JButton score = new JButton("Highscores");
		final JButton opt = new JButton("Options");
		buttonStyleMenu(start);
		buttonStyleMenu(score);
		buttonStyleMenu(opt);
		final JPanel c = copyright();

		start.addActionListener(new ActionListener() { // Open game
			public void actionPerformed(ActionEvent e) {
				// g.init("labyrinth0.txt"); /*("labyrinth" + (int)
				// (Math.random() * 5) + ".txt")*/
				//TODO reflechir a comment update hub
				set = new GameScreen();
				button.setVisible(false);
				l.setVisible(false);
				c.setVisible(false);
				openScreen.add(set);
				openScreen.add(hub(0, 3, false, 'C'));
				set.setFocusable(true);
				set.requestFocusInWindow();
			}
		});

		score.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setVisible(false);
				l.setVisible(false);
				highscore();
			}
		});

		opt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setVisible(false);
				l.setVisible(false);
				options();
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

		menu.add(Box.createRigidArea(new Dimension(0, 40)));
		menu.add(start);
		menu.add(Box.createRigidArea(new Dimension(0, 40)));
		menu.add(score);
		menu.add(Box.createRigidArea(new Dimension(0, 40)));
		menu.add(opt);
		button.add(menu);

		openScreen.setLayout(new BorderLayout());
		openScreen.add(l, BorderLayout.NORTH);
		openScreen.add(button, BorderLayout.CENTER);
		openScreen.add(c, BorderLayout.SOUTH);

		this.setContentPane(openScreen);
	}

	/**
	 * 
	 * Create the logo for the menu, redimention it and return it
	 * 
	 * @author GRIGNON Lindsay
	 * 
	 * @return JPanel The JPanel contains the imageIcon
	 * 
	 */
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

	/**
	 * 
	 * Create a panel which contains the copyright
	 * 
	 * @author GRIGNON Lindsay
	 * @return JPanel The Panel contains the label with the copyright
	 */
	private JPanel copyright() {
		JLabel name = new JLabel("Done by BOURGEOIS Adrien, GRIGNON Lindsay, RIETZ Vincent");
		name.setForeground(Color.WHITE);
		JPanel j = new JPanel();
		j.setBackground(Color.BLACK);
		j.setLayout(new BorderLayout());
		j.add(name, BorderLayout.WEST);
		return j;
	}

	/**
	 * 
	 * Create the panel which contains the options
	 * 
	 * @author GRIGNON Lindsay
	 */
	private void options() {

		int[] config = Stream.readOptions();

		// Jpanel final
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

				// determined which button will be selected
				boolean sw = false;
				boolean classic = false;
				boolean zelda = false;
				if (config[0] == 0) {
					classic = true;
				} else if (config[0] == 1) {
					sw = true;
				} else if (config[0] == 2) {
					zelda = true;
				}

				// pop-up
				final JRadioButton r1 = new JRadioButton("Classic", classic);
				final JRadioButton r2 = new JRadioButton("Star Wars", sw);
				final JRadioButton r3 = new JRadioButton("Zelda", zelda);
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
				l.setBackground(Color.BLACK);
				l.setForeground(Color.BLUE);
				l.setFont(new java.awt.Font("Consolas", 1, 25));
				title.add(l);

				final ImageIcon classic_img = new ImageIcon("sprites/classic.png");
				final ImageIcon sw_img = new ImageIcon("sprites/sw.png");
				final ImageIcon zelda_img = new ImageIcon("sprites/zelda.png");

				// determined which image is set at the beginning
				final JLabel image = new JLabel();
				if (classic)
					image.setIcon(classic_img);
				else if (sw)
					image.setIcon(sw_img);
				else if (zelda)
					image.setIcon(zelda_img);

				class RadioButtonActionListener implements ActionListener {
					@Override
					public void actionPerformed(ActionEvent event) {
						JRadioButton button = (JRadioButton) event.getSource();
						if (button == r1)
							image.setIcon(classic_img);
						else if (button == r2)
							image.setIcon(sw_img);
						else if (button == r3)
							image.setIcon(zelda_img);
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

				Object[] but = { "Ok", "Cancel" };
				int ch = JOptionPane.showOptionDialog(null, t, "", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, but, null);

				if (ch == 0) { // OK
					if (r1.isSelected())
						config[0] = 0;
					else if (r2.isSelected())
						config[0] = 1;
					else if (r3.isSelected())
						config[0] = 2;
				}
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

		boolean k = false;
		boolean m = false;
		if (config[1] == 0) {
			k = true;
		} else if (config[1] == 1) {
			m = true;
		}

		JLabel controls = new JLabel("Controls");
		labelStyleW(controls);
		final JRadioButton keyboard = new JRadioButton("Keyboard", k);
		final JRadioButton mouse = new JRadioButton("Mouse", m);
		radioStyle(keyboard);
		radioStyle(mouse);
		Box c = Box.createVerticalBox();
		c.add(keyboard);
		c.add(mouse);
		ButtonGroup ctrl = new ButtonGroup();
		ctrl.add(keyboard);
		ctrl.add(mouse);

		// difficulte

		boolean e = false;
		boolean n = false;
		boolean h = false;

		if (config[2] == 0) {
			e = true;
		} else if (config[2] == 1) {
			n = true;
		} else if (config[2] == 2) {
			h = true;
		}

		JLabel difficulty = new JLabel("Difficulty");
		labelStyleW(difficulty);
		final JRadioButton easy = new JRadioButton("Easy", e);
		final JRadioButton normal = new JRadioButton("Normal", n);
		final JRadioButton hard = new JRadioButton("Hard", h);
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

		boolean mus = false;
		if (config[3] == 0)
			mus = true;

		JLabel music = new JLabel("Music : ");
		labelStyleW(music);

		final JButton sound = new JButton("ON");
		sound.setBackground(Color.BLACK);
		sound.setFont(new java.awt.Font("Consolas", 1, 25));

		final JButton no_sound = new JButton("OFF");
		no_sound.setBackground(Color.BLACK);
		no_sound.setFont(new java.awt.Font("Consolas", 1, 25));

		if (mus) {
			sound.setForeground(Color.GREEN);
			no_sound.setForeground(Color.WHITE);
		} else {
			sound.setForeground(Color.WHITE);
			no_sound.setForeground(Color.RED);
		}

		JLabel l = new JLabel(" / ");
		labelStyleW(l);

		class ButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button = (JButton) event.getSource();
				if (button == sound) {
					sound.setForeground(Color.GREEN);
					no_sound.setForeground(Color.WHITE);
					config[3] = 0;
				} else {
					if (button == no_sound) {
						sound.setForeground(Color.WHITE);
						no_sound.setForeground(Color.RED);
						config[3] = 1;
					}
				}
			}
		}

		ButtonActionListener bal = new ButtonActionListener();
		sound.addActionListener(bal);
		no_sound.addActionListener(bal);

		JPanel s = new JPanel();
		s.setBackground(Color.BLACK);
		s.add(music);
		s.add(sound);
		s.add(l);
		s.add(no_sound);

		// option final
		final Box option = Box.createVerticalBox();
		option.add(Box.createRigidArea(new Dimension(0, height / 10)));
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
		option.add(s);

		// bouton retour
		JButton back = new JButton("Cancel");
		buttonStyle(back);

		JButton apply = new JButton("Confirm");
		buttonStyle(apply);

		final Box button = Box.createHorizontalBox();
		button.add(apply);
		button.add(Box.createRigidArea(new Dimension(100, 0)));
		button.add(back);

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				title.setVisible(false);
				option.setVisible(false);
				button.setVisible(false);
				menu();
			}
		});

		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (keyboard.isSelected())
					config[1] = 0;
				else if (mouse.isSelected())
					config[1] = 1;

				if (easy.isSelected())
					config[2] = 0;
				else if (normal.isSelected())
					config[2] = 1;
				else if (hard.isSelected())
					config[2] = 2;

				Stream.writeOptions(config);
				title.setVisible(false);
				option.setVisible(false);
				button.setVisible(false);
				menu();
			}
		});

		option.add(button);

		o.setLayout(new BorderLayout());
		o.add(titleStyle(title), BorderLayout.NORTH);
		o.add(option, BorderLayout.CENTER);
		o.add(copyright(), BorderLayout.SOUTH);

		this.setContentPane(o);
	}

	/**
	 * 
	 * Create the panel which display the highscores
	 * 
	 * @author GRIGNON Lindsay
	 */
	private void highscore() {

		int space = (height - 520) / 9;

		final JPanel h = new JPanel();
		h.setBackground(Color.BLACK);
		h.setLayout(new BorderLayout());

		JPanel score = new JPanel();
		score.setBackground(Color.BLACK);

		final JLabel title = new JLabel("HighScores :");

		Box n = Box.createVerticalBox();
		Box p = Box.createVerticalBox();
		Box sc = Box.createVerticalBox();
		LinkedList<NodeScore> ns = Stream.readScore("score.txt");
		if (ns.isEmpty()) {
			JLabel no_s = new JLabel("No highscore yet");
			labelStyleW(no_s);
			score.add(no_s);

		} else {
			Iterator<NodeScore> it = ns.iterator();
			while (it.hasNext()) {
				NodeScore courant = it.next();

				JLabel num = new JLabel(courant.getNum());
				labelStyleY(num);
				num.setAlignmentX(RIGHT_ALIGNMENT);
				n.add(num);
				n.add(Box.createRigidArea(new Dimension(0, space)));

				JLabel pseudo = new JLabel(courant.getPseudo());
				labelStyleW(pseudo);
				p.add(pseudo);
				p.add(Box.createRigidArea(new Dimension(0, space)));

				JLabel sco = new JLabel(courant.getScore());
				labelStyleW(sco);
				sco.setAlignmentX(RIGHT_ALIGNMENT);
				sc.add(sco);
				sc.add(Box.createRigidArea(new Dimension(0, space)));

			}
			Box scoreFinal = Box.createHorizontalBox();
			scoreFinal.add(n);
			scoreFinal.add(Box.createRigidArea(new Dimension(100, 0)));
			scoreFinal.add(p);
			scoreFinal.add(Box.createRigidArea(new Dimension(250, 0)));
			scoreFinal.add(sc);
			score.add(scoreFinal);
		}

		final JButton back = new JButton("Back");
		buttonStyle(back);

		h.add(titleStyle(title), BorderLayout.NORTH);
		h.add(copyright(), BorderLayout.SOUTH);

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				title.setVisible(false);
				back.setVisible(false);
				menu();
			}

		});

		JPanel b = new JPanel();
		b.setBackground(Color.BLACK);
		b.setLayout(new BorderLayout());

		b.add(score);
		// le bouton fait tout le south layout
		b.add(back, BorderLayout.SOUTH);

		h.add(b);

		this.setContentPane(h);

	}

	/**
	 * 
	 * Will ask the player a name and then adds it to the list, only 10 scores
	 * allowed, and then call the score writer
	 * 
	 * @author GRIGNON Lindsay
	 * 
	 * @return String The pseudo
	 */
	public String askPseudo() {

		Box sco = Box.createVerticalBox();

		JLabel n = new JLabel("Enter your name : ");
		n.setBackground(Color.BLACK);
		n.setForeground(Color.WHITE);
		n.setFont(new java.awt.Font("Consolas", 0, 20));

		JLabel nos = new JLabel("(No space)");
		nos.setBackground(Color.BLACK);
		nos.setForeground(Color.WHITE);
		nos.setFont(new java.awt.Font("Consolas", 0, 20));

		JTextField name = new JTextField();

		n.setAlignmentX(CENTER_ALIGNMENT);
		sco.add(n);
		nos.setAlignmentX(CENTER_ALIGNMENT);
		sco.add(nos);
		sco.add(Box.createRigidArea(new Dimension(00, 10)));
		sco.add(name);

		UIManager.put("OptionPane.background", Color.BLACK);
		UIManager.put("Panel.background", Color.BLACK);

		do {
			JOptionPane.showMessageDialog(null, sco, "You have a highscore !", JOptionPane.PLAIN_MESSAGE);
		} while (name.getText().contains(" ") || name.getText().equals(""));

		return name.getText();
	}

	private JPanel hub(int score, int lives, boolean fruit, char sprite) {

		JLabel s = new JLabel("Score : " + score);
		labelStyleW(s);
		JLabel l = new JLabel("Lives : " + lives);
		labelStyleW(l);
		JLabel f = null;

		if (fruit) {
			String theme = "";
			switch (Stream.readOptions()[0]) {
			case 0:
				theme = "classic";
				break;
			case 1:
				theme = "sw";
				break;
			case 3:
				theme = "zelda";
				break;
			}
			ImageIcon f_s = null;
			switch (sprite) {
			case 'C':
				f_s = new ImageIcon("sprites/" + theme + "/Cherry.gif");
				break;
			case 's':
				f_s = new ImageIcon("sprites/" + theme + "/Strawberry.gif");
				break;
			case 'O':
				f_s = new ImageIcon("sprites/" + theme + "/Orange.gif");
				break;
			case 'A':
				f_s = new ImageIcon("sprites/" + theme + "/Apple.gif");
				break;
			case 'M':
				f_s = new ImageIcon("sprites/" + theme + "/Melon.gif");
				break;
			case 'b':
				f_s = new ImageIcon("sprites/" + theme + "/Galboss.gif");
				break;
			case 'B':
				f_s = new ImageIcon("sprites/" + theme + "/Bell.gif");
				break;
			case 'K':
				f_s = new ImageIcon("sprites/" + theme + "/Key.gif");
				break;

			}
			f = new JLabel(f_s);
		}
		
		
		JPanel hub = new JPanel ();
		hub.setBackground(Color.BLACK);
		hub.setLayout(new BorderLayout());
		
		hub.add(s, BorderLayout.NORTH);
		hub.add(l, BorderLayout.SOUTH);
		
		if (f != null) {
			JPanel fAlt = new JPanel();
			fAlt.setBackground(Color.BLACK);
			fAlt.setLayout(new BorderLayout());
			fAlt.add(f, BorderLayout.SOUTH);
			
			hub.add(fAlt, BorderLayout.EAST);
		}
		
		return hub;

	}

	/**
	 * 
	 * Stylized the basic button
	 * 
	 * @author GRIGNON Lindsay
	 * @param b
	 *            The button you want to style
	 */
	private void buttonStyle(JButton b) {
		b.setBackground(Color.BLACK);
		b.setForeground(Color.BLUE);
		b.setBorder(null);
		b.setFont(new java.awt.Font("Consolas", 1, 36));
	}

	/**
	 * 
	 * Stylized the menu button
	 * 
	 * @author GRIGNON Lindsay
	 * @param b
	 *            The button you want to style
	 */
	private void buttonStyleMenu(JButton b) {
		b.setBackground(Color.BLACK);
		b.setForeground(Color.BLUE);
		b.setBorder(null);
		b.setFont(new java.awt.Font("Consolas", 1, 45));
	}

	/**
	 * 
	 * Stylized the radio button
	 * 
	 * @author GRIGNON Lindsay
	 * @param r
	 *            The radio button you want to style
	 */
	private void radioStyle(JRadioButton r) {
		r.setBackground(Color.BLACK);
		r.setForeground(Color.WHITE);
		r.setFont(new java.awt.Font("Consolas", 1, 20));
	}

	/**
	 * 
	 * Stylized the label with white writing
	 * 
	 * @author GRIGNON Lindsay
	 * @param l
	 *            The label you want to style
	 */
	private static void labelStyleW(JLabel l) {
		l.setBackground(Color.BLACK);
		l.setForeground(Color.WHITE);
		l.setFont(new java.awt.Font("Consolas", 1, 30));
	}

	/**
	 * 
	 * Stylized the label with yellow writing
	 * 
	 * @author GRIGNON Lindsay
	 * @param l
	 *            The label you want to style
	 */
	private void labelStyleY(JLabel l) {
		l.setBackground(Color.BLACK);
		l.setForeground(Color.YELLOW);
		l.setFont(new java.awt.Font("Consolas", 1, 30));
	}

	/**
	 * 
	 * Will create a Panel with a Label title and return it
	 * 
	 * @author GRIGNON Lindsay
	 * @param l
	 *            The label you want to style
	 * @return JPanel The panel contain the title
	 */
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
