package en.window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Frame {
	private JFrame frame;

	public Frame() {
		frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight();
		frame.setName("Pacman");
		frame.setSize((4 * height) / 3, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
