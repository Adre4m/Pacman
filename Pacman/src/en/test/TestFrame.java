package en.test;

import en.window.Frame;

public class TestFrame {
	public static void main(String[] args) {
		Frame f = new Frame();
		System.out.println(f.getFrame().getSize().getHeight());
		System.out.println(f.getFrame().getSize().getWidth());
	}
}
