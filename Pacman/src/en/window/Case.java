package en.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import en.master.Game;
import en.master.Stream;

/**
 * 
 * @author RIETZ Vincent
 *
 */
public class Case extends JPanel {
	private static final long serialVersionUID = 1L;
	private char content; // type of image
	private JLabel label; // contains the image
	private String theme;
	private int number;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int) (screenSize.getHeight() * 0.95);

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
	public Case(GameScreen g, Game game, char content, int number) {
		this.content = content;
		this.number = number;

		Image img; // image without a resize
		Image newimg; // image resized
		// theme
		switch (Stream.readOptions()[0]) {
		case 0:
			theme = "classic";
			break;
		case 1:
			theme = "sw";
			break;
		case 2:
			theme = "zelda";
			break;
		default:
			theme = "classic";
			break;
		}

		switch (content) {

		case ' ':
			this.label = new JLabel(new ImageIcon());
			add(label);
			break;
		case 'D':
			setLayout(new FlowLayout(FlowLayout.CENTER));
			label = new JLabel();
			img = new ImageIcon("sprites/" + theme + "/door.gif").getImage();
			newimg = img.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label.setIcon(new ImageIcon(newimg));
			add(label);
			break;
		case 'X':
			setLayout(new FlowLayout(FlowLayout.CENTER));
			label = new JLabel();
			img = new ImageIcon("sprites/" + theme + "/wall.gif").getImage();
			newimg = img.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label.setIcon(new ImageIcon(newimg));
			add(label);

			break;
		case 'g':
			setLayout(new FlowLayout(FlowLayout.CENTER));
			label = new JLabel();
			img = new ImageIcon("sprites/" + theme + "/PacGum.gif").getImage();
			newimg = img.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label.setIcon(new ImageIcon(newimg));
			add(label);
			break;
		case 'S':
			setLayout(new FlowLayout(FlowLayout.CENTER));
			label = new JLabel();
			img = new ImageIcon("sprites/" + theme + "/Super pacgum.gif").getImage();
			newimg = img.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label = new JLabel(new ImageIcon(newimg));
			add(label);
			break;
		case 'P':
			setLayout(new FlowLayout(FlowLayout.CENTER));
			label = new JLabel();
			img = new ImageIcon("sprites/" + theme + "/PacMan_right.gif").getImage();
			newimg = img.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label = new JLabel(new ImageIcon(newimg));
			add(label);
			break;
		case 'G':
			setLayout(new FlowLayout(FlowLayout.CENTER));
			label = new JLabel();
			img = new ImageIcon(g.getSprite(game, this.number)).getImage();
			newimg = img.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
			label.setIcon(new ImageIcon(newimg));
			add(label);
			break;
		default:
			break;

		}
		if (theme.equals("zelda"))
			this.setBackground(new Color(150, 160, 50));
		else
			this.setBackground(new Color(0, 0, 0));
	}

	public char getContent() {
		return this.content;
	}

	public int getNumber() {
		return this.number;
	}

	public void setContent(char c) {
		this.content = c;
	}

	public JLabel getLabel() {
		return this.label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public void setCase(Case c) {
		this.label = c.getLabel();
		this.content = c.getContent();
	}

	public void update(String sprite) {
		Image img = new ImageIcon(sprite).getImage();
		Image newimg = img.getScaledInstance((int) (height * 0.024), (int) (height * 0.024), Image.SCALE_DEFAULT);
		label.setIcon(new ImageIcon(newimg));
	}

}
