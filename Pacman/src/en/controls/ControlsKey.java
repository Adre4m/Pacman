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

	// TODO ne doit pas rester ! juste la pour tester
	private Game game;

	public ControlsKey(Game game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Point p = game.characters[0].getPosition();
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if (game.getLab()[p.x][Math.floorMod(p.y - 1, game.getLab()[0].length)] != 'X')
				game.characters[0].setDir('l');
			break;
		case KeyEvent.VK_RIGHT:
			if (game.getLab()[p.x][Math.floorMod(p.y + 1, game.getLab()[0].length)] != 'X')
				game.characters[0].setDir('r');
			break;
		case KeyEvent.VK_UP:
			if (game.getLab()[Math.floorMod(p.x - 1, game.getLab().length)][p.y] != 'X')
				game.characters[0].setDir('u');
			break;
		case KeyEvent.VK_DOWN:
			if (game.getLab()[Math.floorMod(p.x + 1, game.getLab().length)][p.y] != 'X')
				game.characters[0].setDir('d');
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
