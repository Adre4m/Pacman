package en.controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import en.window.Case;
import en.window.GameScreen;

public class ControlsMouse implements MouseListener {
	private int coordX, coordY;
	GameScreen g;
	public ControlsMouse(GameScreen g){
		this.g = g;
		//this.coordX = g.getPacmanX();
		//this.coordY = g.getPacmanY();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		boolean didItWork = false;
		Case c = (Case) e.getSource();
		int position = c.getNumber(); // to see where we have clicked
		// int pacmanPos = pacmanY * 28 + pacmanX;
		int posX = position % 28;
		int posY = (position - posX) / 28;
		System.out.println(posX + " " + posY);
		if (Math.abs(posX - coordX) >= Math.abs(posY - coordY)) {
			if (posX > coordX){
				//didItWork = g.move(coordX, coordY, 'r');
				if (coordX != 27)
					coordX++;
				else
					coordX = 0;
			}

			else if (coordX > posX){
				//didItWork = g.move(coordX, coordY, 'l');
				if(didItWork){
					if (coordX != 0)
						coordX--;
					else
						coordX = 27;
				}
			}

		} else if (Math.abs(posY - coordY) > Math.abs(posX - coordX)) {
			if (posY > coordY){
			//	didItWork = g.move(coordX, coordY, 'd');
				if(didItWork){
					if (coordY != 31)
						coordY++;
					else
						coordY = 0;
				}
			}

			else if (coordY > posY){
				//didItWork = g.move(coordX, coordY, 'u');
				if(didItWork){
					if (coordY !=0)
						coordY--;
					else
						coordY = 31;
				}
				
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
