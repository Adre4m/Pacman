package en.controls;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import en.master.Game;
import en.window.Case;

public class ControlsMouse implements MouseListener {
	Game game;

	public ControlsMouse(Game game) {
		this.game = game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Case c = (Case) e.getSource();
		int position = c.getNumber();
		int posX = position % 28;
		int posY = (position - posX) / 28;
		Point p = game.characters[0].getPosition();
		if (Math.abs(posX - p.x) >= Math.abs(posY - p.y)) {
			if (game.getLab()[p.x][Math.floorMod(p.y + 1, game.getLab()[0].length)] != 'X')
				game.characters[0].setDir('r');
		} else if (p.x > posX) {
			if (game.getLab()[p.x][Math.floorMod(p.y - 1, game.getLab()[0].length)] != 'X')
				game.characters[0].setDir('l');
		} else if (Math.abs(posY - p.y) > Math.abs(posX - p.x)) {
			if (game.getLab()[Math.floorMod(p.x + 1, game.getLab().length)][p.y] != 'X')
				game.characters[0].setDir('d');
		} else if (p.y > posY)
			if (game.getLab()[Math.floorMod(p.x - 1, game.getLab().length)][p.y] != 'X')
				game.characters[0].setDir('u');

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
