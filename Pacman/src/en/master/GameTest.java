package en.master;

import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	Game game;

	@Before
	public void setUp() throws Exception {
		game = new Game();
		game.init();
	}

	@Test
	public void test() throws InterruptedException {
		// assertTrue(true);
		assertTrue(game.characters[0].getPosition()
				.equals(new Point((game.getLab().length / 2) + 6, game.getLab()[0].length / 2)));
		System.out.println(game);
		
		game.characters[0].move('u', game.getLab());
		assertTrue(game.characters[0].getPosition()
				.equals(new Point((game.getLab().length / 2) + 5, game.getLab()[0].length / 2)));
		System.out.println(game);		
		game.characters[0].move('u', game.getLab());
		game.characters[0].move('u', game.getLab());
		game.characters[0].move('u', game.getLab());
		game.characters[0].move('u', game.getLab());
		game.characters[0].move('u', game.getLab());
		assertTrue(game.characters[0].getPosition()
				.equals(new Point((game.getLab().length / 2) + 2, game.getLab()[0].length / 2)));
		System.out.println(game);		
	}

}
