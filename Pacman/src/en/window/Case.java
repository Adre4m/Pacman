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
	private JLabel label; // contains the image
	private int number;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int height = (int) (screenSize.getHeight() * 0.95);

	/**
	 * 
	 * This is the Case constructor. It adds to the cell a picture or a label
	 * depending on the type of cell and set the background color.
	 * 
	 * @author RIETZ Vincent
	 * 
	 * @param g
	 *            The game screen
	 * @param content
	 *            The type of cell
	 * @param number
	 *            The cell's number
	 * 
	 */
	public Case(GameScreen g, int number, String sprite, String theme) {
		this.number = number;

		Image img = new ImageIcon(sprite).getImage();
		Image newimg = img.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
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

	public JLabel getLabel() {
		return this.label;
	}

	public void update(String sprite) {
		Image img = new ImageIcon(sprite).getImage();
		Image newimg = img.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
		label.setIcon(new ImageIcon(newimg));
	}

}
