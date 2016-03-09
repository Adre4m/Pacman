package en.master.graph;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {

	Graph g;

	@Before
	public void setUp() throws Exception {
		char[][] game = { { ' ', ' ', ' ', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', ' ', ' ', ' ' },
				{ 'X', 'X', ' ', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', ' ', 'X', 'X' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ 'X', 'X', 'X', ' ', ' ', 'X', 'X', 'D', 'D', 'X', 'X', ' ', ' ', 'X', 'X', 'X' },
				{ 'X', 'X', 'X', ' ', ' ', 'X', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X', 'X' },
				{ 'X', 'X', 'X', ' ', ' ', 'X', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X', 'X' },
				{ ' ', ' ', ' ', ' ', ' ', 'X', 'X', 'X', 'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', 'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X', 'X', 'X', ' ' },
				{ ' ', 'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X', 'X', 'X', ' ' },
				{ ' ', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X', 'X', ' ', ' ' }, };
		g = new Graph(game);
	}

	@Test
	public void testInit() {
		assertEquals(g.getAdj().length, 104);
		assertEquals(g.getVertex().size(), 104);
		assertEquals(g.getVertex().get(0).getPosition().x, 0);
		assertEquals(g.getVertex().get(0).getPosition().y, 0);
	}

	@Test
	public void testReach() {
		Point start = new Point(2, 8);
		Point goal = new Point(10, 8);
		Stack<Character> moves = g.reach(start, goal, 'u');
		System.out.println(moves);
	}

}
