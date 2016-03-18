package en.window;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author RIETZ Vincent
 *
 */
public class Case extends JPanel {
	char content; // type of image
	JLabel label; // contains the image
	String theme;
	int number;

	public Case(char content, int number) {
		this.content = content;
		this.number = number;

		URL url; // url of the image
		Image img; // image without a resize
		Image newimg; // image resized
		theme = "zelda";
		switch (content) {

		case ' ':
			this.label = new JLabel(" ");
			add(label);
			break;
		case 'X':
			setLayout(new FlowLayout(FlowLayout.CENTER));
			label = new JLabel();
			img = new ImageIcon("sprites/" + theme + "/wall.gif").getImage();
			newimg = img.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
			label.setIcon(new ImageIcon(newimg));
			add(label);

			break;
		case 'g':
			setLayout(new FlowLayout(FlowLayout.CENTER));
			label = new JLabel();
			img = new ImageIcon("sprites/" + theme + "/PacGum.gif").getImage();
			newimg = img.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
			label.setIcon(new ImageIcon(newimg));
			add(label);
			break;
		case 'S':
			setLayout(new FlowLayout(FlowLayout.CENTER));
			label = new JLabel();
			img = new ImageIcon("sprites/" + theme + "/Super pacgum.gif").getImage();
			newimg = img.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
			label = new JLabel(new ImageIcon(newimg));
			add(label);
			break;
		case 'D':
			this.label = new JLabel("D");
			break;
		case 'P':
			setLayout(new FlowLayout(FlowLayout.CENTER));
			label = new JLabel();
			img = new ImageIcon("sprites/" + theme + "/PacMan_right.gif").getImage();
			newimg = img.getScaledInstance(18, 18, Image.SCALE_DEFAULT);
			// label.setIcon(new ImageIcon(newimg));
			label = new JLabel(new ImageIcon(newimg));
			add(label);
			break;
		case 'G':
			setLayout(new FlowLayout(FlowLayout.CENTER));
			label = new JLabel();
			img = new ImageIcon("sprites/" + theme + "/Blinky_down.gif").getImage();
			newimg = img.getScaledInstance(15, 15, Image.SCALE_DEFAULT);
			label.setIcon(new ImageIcon(newimg));
			add(label);
			break;
		default:
			break;

		}

		this.setBackground(new Color(150, 160, 50));
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

	public void setLabel(JLabel l) {
		this.label = l;
	}

	public void setCase(Case c) {
		this.label = c.getLabel();
		this.content = c.getContent();
	}
}
