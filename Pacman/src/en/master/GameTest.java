package en.master;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		/*
		 * assertTrue(game.characters[0].getPosition() .equals(new
		 * Point((game.getLab().length / 2) + 6, game.getLab()[0].length / 2)));
		 */
		//System.out.println(game);
		assertEquals(((Pacman) game.characters[0]).getLives(), 3);

		assertTrue(!((Ghost) game.characters[3]).isVunerable());

		game.characters[0].move('l', game);
		game.characters[0].move('r', game);
		game.characters[0].move('r', game);
		System.out.println(game);
		assertEquals(((Pacman) game.characters[0]).getLives(), 3);
		assertTrue(((Ghost) game.characters[3]).isVunerable());

	}

}
