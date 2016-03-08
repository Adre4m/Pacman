package en.master.graph;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import en.master.characters.Blinky;
import en.master.characters.Ghost;

public class GraphTest {

	Graph g;

	@Before
	public void setUp() throws Exception {
		char[][] game = { 
				{ 'X', 'X', ' ', 'X', 'X' }, 
				{ 'X', ' ', ' ', ' ', 'X' }, 
				{ 'X', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', 'X' }, 
				{ 'X', 'X', ' ', 'X', 'X' } };
		g = new Graph(game);
	}

	@Test
	public void testInit() {
		assertEquals(g.getAdj().length, 11);
		assertEquals(g.getVertex().size(), 11);
		assertEquals(g.getVertex().get(0).getPosition().x, 0);
		assertEquals(g.getVertex().get(0).getPosition().y, 2);
	}

	@Test
	public void testAdjancy() {
		int[][] test = { { 0, 0, 1, 0, 0, 2, 0, 0, 3, 0, 4 }, { 0, 0, 1, 2, 1, 0, 0, 2, 0, 0, 0 },
				{ 1, 1, 0, 1, 0, 1, 0, 0, 2, 0, 3 }, { 0, 2, 1, 0, 0, 0, 1, 0, 0, 2, 0 },
				{ 0, 1, 0, 0, 0, 1, 2, 1, 0, 0, 0 }, { 2, 0, 1, 0, 1, 0, 1, 0, 1, 0, 2 },
				{ 0, 0, 0, 1, 2, 1, 0, 0, 0, 1, 0 }, { 0, 2, 0, 0, 1, 0, 0, 0, 1, 2, 0 },
				{ 3, 0, 2, 0, 0, 1, 0, 1, 0, 1, 1 }, { 0, 0, 0, 2, 0, 0, 1, 2, 1, 0, 0 },
				{ 4, 0, 3, 0, 0, 2, 0, 0, 1, 0, 0 }, };
		for (int i = 0; i < test.length; ++i)
			for (int j = 0; j < test.length; ++j)
				assertEquals(g.getAdj()[i][j], test[i][j]);
	}

	@Test
	public void testReach() {
		Point start = new Point(1, 1);
		Point goal = new Point(3, 3);
		Ghost ghost = new Blinky();
		Stack<Character> moves = g.reach(start, goal, ghost);
		Stack<Character> test = new Stack<Character>();
		test.push('d');
		test.push('d');
		test.push('r');
		test.push('r');
		assertEquals(test, moves);
	}

}
