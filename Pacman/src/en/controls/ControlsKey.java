package en.controls;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import en.master.Game;

public class ControlsKey implements KeyListener {

	// static final String newline = System.getProperty("line.separator");

	// TODO ne doit pas rester ! juste la pour tester
	class QuickFrame extends JFrame {

		private static final long serialVersionUID = 1L;

		JTextArea displayArea;
		JTextField typingArea;
		private ControlsKey test;

		public QuickFrame(ControlsKey test) {
			this.setTitle("Test KeyListner");
			this.setSize(200, 200);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.test = test;
			this.addComponentsToPane();
			this.pack();
			this.setVisible(true);
		}

		private void addComponentsToPane() {
			typingArea = new JTextField(20);
			typingArea.addKeyListener(test);
			getContentPane().add(typingArea, BorderLayout.PAGE_START);
		}
	}

	QuickFrame qf = new QuickFrame(this);
	private Game game;

	public ControlsKey(Game game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			game.characters[0].setDir('l');
			break;
		case KeyEvent.VK_RIGHT:
			game.characters[0].setDir('r');
			break;
		case KeyEvent.VK_UP:
			game.characters[0].setDir('u');
			break;
		case KeyEvent.VK_DOWN:
			game.characters[0].setDir('d');
			break;
		case KeyEvent.VK_ESCAPE:
			if (game.isPaused()) {
				game.setPaused(false);
			} else {
				game.setPaused(true);
			}
			break;
		default:
			return;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static void main(String[] args) {
		final Game test = new Game();
		test.initTest("labyrinths/labyrinth2.txt");
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ControlsKey(test);
			}
		});
		test.play(null);
	}

}
