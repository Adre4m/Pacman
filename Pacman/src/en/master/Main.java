package en.master;

import en.controls.ControlsKey;
import en.controls.ControlsMouse;
import en.window.Frame;

public class Main {

	public static void main(String[] args) {
		final Frame f = new Frame();
		do {
			System.out.print("");
			if (f.gameStarted) {
				Game game = new Game();
				game.init("labyrinths/labyrinth.txt");
				f.initGameScreen(game);
				switch (Stream.readOptions()[1]) {
				case 0:
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							f.addKeyListener(new ControlsKey(game));
						}
					});
					break;
				case 1:
					f.addMouseListener(new ControlsMouse(game));
					break;
				default:
					new ControlsKey(game);
				}
				game.play(f);
				f.gameStarted = false;
				// f.menu();
			} else
				continue;
		} while (true);

	}
}
