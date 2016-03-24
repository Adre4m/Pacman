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
		if (e.getButton() == MouseEvent.BUTTON3)
			game.pause();
		else {
			Case c = (Case) e.getSource();
			int position = c.getNumber();
			int posY = position % 28;
			int posX = (position - posY) / 28;
			Point p = game.characters[0].getPosition();
			if (Math.abs(posX - p.x) < Math.abs(posY - p.y)) {
				if (p.y < posY) {
					game.characters[0].nextDir = 'r';
				} else {
					game.characters[0].nextDir = 'l';
				}
			} else {
				if (p.x < posX) {
					game.characters[0].nextDir = 'd';
				} else {
					game.characters[0].nextDir = 'u';
				}
			}
		}
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
