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
import javax.swing.UIManager;

import en.master.Stream;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int) (screenSize.getHeight() * 0.95);
	
	public Frame() {

		// create frame
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//int height = (int) (screenSize.getHeight() * 0.95);
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
				GameScreen set = new GameScreen();
				button.setVisible(false);
				l.setVisible(false);
				c.setVisible(false);
				openScreen.add(set);
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
		j.setBackground(Color.BLACK);
		j.setLayout(new BorderLayout());
		j.add(name, BorderLayout.WEST);
		return j;
	}

	private void options() {

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
				l.setBackground(Color.BLACK);
				l.setForeground(Color.BLUE);
				l.setFont(new java.awt.Font("Consolas", 1, 25));
				title.add(l);

				final ImageIcon classic = new ImageIcon("sprites/classic.png");
				final ImageIcon sw = new ImageIcon("sprites/sw.png");
				final ImageIcon bh6 = new ImageIcon("sprites/bh6.png");

				final JLabel image = new JLabel();
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
		JLabel music = new JLabel("Music : ");
		labelStyleW(music);

		final JButton sound = new JButton("ON");
		sound.setBackground(Color.BLACK);
		sound.setForeground(Color.GREEN);
		sound.setFont(new java.awt.Font("Consolas", 1, 25));

		final JButton no_sound = new JButton("OFF");
		no_sound.setBackground(Color.BLACK);
		no_sound.setForeground(Color.WHITE);
		no_sound.setFont(new java.awt.Font("Consolas", 1, 25));

		JLabel l = new JLabel(" / ");
		labelStyleW(l);

		class ButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button = (JButton) event.getSource();
				if (button == sound) {
					sound.setForeground(Color.GREEN);
					no_sound.setForeground(Color.WHITE);
				} else {
					if (button == no_sound) {
						sound.setForeground(Color.WHITE);
						no_sound.setForeground(Color.RED);
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
		option.add(Box.createRigidArea(new Dimension(0, 100)));
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
				// ajouter le flux
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

	private void highscore() {
		Stream s = new Stream();

		int space = (height - 100) /20 ;
		
		final JPanel h = new JPanel();
		h.setBackground(Color.BLACK);
		h.setLayout(new BorderLayout());
		
		JPanel score = new JPanel();
		score.setBackground(Color.BLACK);

		final JLabel title = new JLabel("HighScores :");

		Box n = Box.createVerticalBox();
		Box p = Box.createVerticalBox();
		Box sc = Box.createVerticalBox();
		LinkedList<NodeScore> ns = s.readScore("score.txt");
		Iterator<NodeScore> it = ns.iterator();
		 while (it.hasNext()) {
			NodeScore courant = it.next();
			
			JLabel num = new JLabel (courant.getNum());
			labelStyleY(num);
			num.setAlignmentX(RIGHT_ALIGNMENT);
			n.add(num);
			n.add(Box.createRigidArea(new Dimension(0, space)));
			
			JLabel pseudo = new JLabel (courant.getPseudo());
			labelStyleW(pseudo);
			p.add(pseudo);
			p.add(Box.createRigidArea(new Dimension(0, space)));
			
			JLabel sco = new JLabel (courant.getScore());
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

	private void buttonStyle(JButton b) {
		b.setBackground(Color.BLACK);
		b.setForeground(Color.BLUE);
		b.setBorder(null);
		b.setFont(new java.awt.Font("Consolas", 1, 36));
	}

	private void buttonStyleMenu(JButton b) {
		b.setBackground(Color.BLACK);
		b.setForeground(Color.BLUE);
		b.setBorder(null);
		b.setFont(new java.awt.Font("Consolas", 1, 45));
	}

	private void radioStyle(JRadioButton r) {
		r.setBackground(Color.BLACK);
		r.setForeground(Color.WHITE);
		r.setFont(new java.awt.Font("Consolas", 1, 20));
	}


	private void labelStyleW(JLabel l) {
		l.setBackground(Color.BLACK);
		l.setForeground(Color.WHITE);
		l.setFont(new java.awt.Font("Consolas", 1, 30));
	}
	
	private void labelStyleY(JLabel l) {
		l.setBackground(Color.BLACK);
		l.setForeground(Color.YELLOW);
		l.setFont(new java.awt.Font("Consolas", 1, 30));
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
