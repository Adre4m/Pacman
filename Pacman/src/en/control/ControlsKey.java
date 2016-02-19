package en.control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ControlsKey implements KeyListener, ActionListener {

	static final String newline = System.getProperty("line.separator");

	// TODO ne doit pas rester ! juste la pour tester
	class QuickFrame extends JFrame {

		private static final long serialVersionUID = 1L;

		JTextArea displayArea;
		JTextField typingArea;
		private ControlsKey test;

		public QuickFrame(ControlsKey test) {
			this.setTitle("Test KeyListner");
			this.setSize(200, 200);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.test = test;
			this.addComponentsToPane();
			this.pack();
			this.setVisible(true);
		}

		private void addComponentsToPane() {
			// TODO Auto-generated method stub
			JButton button = new JButton("Clear");
			button.addActionListener(test);
			typingArea = new JTextField(20);
			typingArea.addKeyListener(test);
			displayArea = new JTextArea();
			displayArea.setEditable(false);
			JScrollPane scrollPane = new JScrollPane(displayArea);
			scrollPane.setPreferredSize(new Dimension(375, 125));

			getContentPane().add(typingArea, BorderLayout.PAGE_START);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			getContentPane().add(button, BorderLayout.PAGE_END);
		}
	}

	QuickFrame qf = new QuickFrame(this);

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			displayInfo(e, "Go to left (KEY PRESSED)");
			break;
		case KeyEvent.VK_RIGHT:
			displayInfo(e, "Go to right (KEY PRESSED)");
			break;
		case KeyEvent.VK_UP:
			displayInfo(e, "Go to up (KEY PRESSED)");
			break;
		case KeyEvent.VK_DOWN:
			displayInfo(e, "Go to down (KEY PRESSED)");
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			displayInfo(e, "Go to left (KEY RELEASED)");
			break;
		case KeyEvent.VK_RIGHT:
			displayInfo(e, "Go to right (KEY RELEASED)");
			break;
		case KeyEvent.VK_UP:
			displayInfo(e, "Go to up (KEY RELEASED)");
			break;
		case KeyEvent.VK_DOWN:
			displayInfo(e, "Go to down (KEY RELEASED)");
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			displayInfo(e, "Go to left (KEY TYPED)");
			break;
		case KeyEvent.VK_RIGHT:
			displayInfo(e, "Go to right (KEY TYPED)");
			break;
		case KeyEvent.VK_UP:
			displayInfo(e, "Go to up (KEY TYPED)");
			break;
		case KeyEvent.VK_DOWN:
			displayInfo(e, "Go to down (KEY TYPED)");
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		qf.displayArea.setText("");
		qf.typingArea.setText("");
		qf.typingArea.requestFocusInWindow();
	}

	private void displayInfo(KeyEvent e, String keyStatus) {

		// You should only rely on the key char if the event
		// is a key typed event.
		int id = e.getID();
		String keyString;
		if (id == KeyEvent.KEY_TYPED) {
			char c = e.getKeyChar();
			keyString = "key character = '" + c + "'";
		} else {
			int keyCode = e.getKeyCode();
			keyString = "key code = " + keyCode + " (" + KeyEvent.getKeyText(keyCode) + ")";
		}

		int modifiersEx = e.getModifiersEx();
		String modString = "extended modifiers = " + modifiersEx;
		String tmpString = KeyEvent.getModifiersExText(modifiersEx);
		if (tmpString.length() > 0) {
			modString += " (" + tmpString + ")";
		} else {
			modString += " (no extended modifiers)";
		}

		String actionString = "action key? ";
		if (e.isActionKey()) {
			actionString += "YES";
		} else {
			actionString += "NO";
		}

		String locationString = "key location: ";
		int location = e.getKeyLocation();
		if (location == KeyEvent.KEY_LOCATION_STANDARD) {
			locationString += "standard";
		} else if (location == KeyEvent.KEY_LOCATION_LEFT) {
			locationString += "left";
		} else if (location == KeyEvent.KEY_LOCATION_RIGHT) {
			locationString += "right";
		} else if (location == KeyEvent.KEY_LOCATION_NUMPAD) {
			locationString += "numpad";
		} else { // (location == KeyEvent.KEY_LOCATION_UNKNOWN)
			locationString += "unknown";
		}

		qf.displayArea.append(keyStatus + newline + "    " + keyString + newline + "    " + modString + newline + "    "
				+ actionString + newline + "    " + locationString + newline);
		qf.displayArea.setCaretPosition(qf.displayArea.getDocument().getLength());
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ControlsKey();
			}
		});
	}

}
