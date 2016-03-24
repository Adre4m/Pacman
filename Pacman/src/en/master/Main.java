package en.master;

import en.window.Frame;

public class Main {

	public static void main(String[] args) {
		final Frame f = new Frame();
		do {
			System.out.print("");
			if (f.gameStarted) {
				Game game = new Game();
				game.init("labyrinths/labyrinth1.txt");
				f.initGameScreen(game);
				game.play(f);
			} else
				continue;
		} while (true);

	}
}
