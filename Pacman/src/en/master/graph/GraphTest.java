package en.master.graph;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import en.master.Game;

public class GraphTest {

	Graph g;
	final Game game = new Game();

	@Before
	public void setUp() throws Exception {
		game.initTest("labyrinths/labyrinth2.txt");
		g = new Graph(game.getLab());
	}

	@Test
	public void testInit() {
		assertEquals(g.getVertex().get(0).getPosition().x, 0);
		assertEquals(g.getVertex().get(0).getPosition().y, 13);
	}

	@Test
	public void testReach() {
		Point start = new Point(1, 14);
		Point goal = new Point(30, 14);
		Stack<Character> moves = g.reach(start, goal, 'u');
		System.out.println(moves);
		start = new Point(1, 14);
		goal = new Point(30, 14);
		moves = g.reach(start, goal, 'u');
		System.out.println(moves);
	}

}
