package en.controls;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import en.master.Game;

/**
 * 
 * @author BOURGEOIS Adrien
 *
 */
public class ControlsKey implements KeyListener {

	private Game game;

	public ControlsKey(Game game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			game.characters[0].nextDir = 'l';
			break;
		case KeyEvent.VK_RIGHT:
			game.characters[0].nextDir = 'r';
			break;
		case KeyEvent.VK_UP:
			game.characters[0].nextDir = 'u';
			break;
		case KeyEvent.VK_DOWN:
			game.characters[0].nextDir = 'd';
			break;
		case KeyEvent.VK_ESCAPE:
			game.pause();
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

}
