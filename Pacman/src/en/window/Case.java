package en.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author RIETZ Vincent
 *
 */
public class Case extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private int number;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int height = (int) (screenSize.getHeight() * 0.95 * 0.96);
	private int length;

	/**
	 * 
	 * This is the Case constructor. It adds to the cell a picture or a label
	 * depending on the type of cell and set the background color.
	 * 
	 * @param g
	 *            The game screen
	 * @param number
	 *            The cell's number
	 * @param sprite
	 *            The first sprite of the cell
	 * @param theme
	 *            The theme of the game
	 * @param length
	 *            The length of the Game
	 */
	public Case(GameScreen g, int number, String sprite, String theme, int length) {
		this.number = number;
		this.length = length;
		double size = height / length;
		size -= length / 3;
		Image img = new ImageIcon(sprite).getImage();
		Image newimg = img.getScaledInstance((int) size, (int) size, Image.SCALE_DEFAULT);
		this.label = new JLabel(new ImageIcon(newimg));
		add(label);
		if (theme.equals("zelda"))
			this.setBackground(new Color(150, 160, 50));
		else
			this.setBackground(new Color(0, 0, 0));
	}

	public int getNumber() {
		return this.number;
	}

	/**
	 * Change the sprite of the cell.
	 * 
	 * @param sprite
	 *            The sprite to set.
	 */
	public void update(String sprite) {
		double size = height / length;
		size -= length / 3;
		Image img = new ImageIcon(sprite).getImage();
		Image newimg = img.getScaledInstance((int) size, (int) size, Image.SCALE_DEFAULT);
		label.setIcon(new ImageIcon(newimg));
	}

}
