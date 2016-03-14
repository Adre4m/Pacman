package en.master.graph;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import en.master.Game;

public class GraphTest {

	Graph g;
	final Game game = new Game();

	@Before
	public void setUp() throws Exception {
		game.init("labyrinths/test2.txt");
		g = new Graph(game.getLab());
	}

	@Test
	public void testInit() {
		assertEquals(g.getVertex().get(0).getPosition().x, 0);
		assertEquals(g.getVertex().get(0).getPosition().y, 13);
		try {
			PrintStream ps = new PrintStream("res.txt");
			for (int i = 0; i < g.getAdj().length; ++i) {
				ps.print(
						"(" + g.getVertex().get(i).getPosition().x + ", " + g.getVertex().get(i).getPosition().y + ")");
				ps.print("[");
				for (int j = i + 1; j < g.getAdj().length; ++j) {
					if (g.getAdj()[i][j] == 1) {
						ps.print("-" + "(" + g.getVertex().get(j).getPosition().x + ", "
								+ g.getVertex().get(j).getPosition().y + ")" + "-");
					}
				}
				ps.println("]");
			}
			ps.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReach() {
		Point start = new Point(7, 14);
		Point goal = new Point(7, 26);
		Stack<Character> moves = g.reach(start, goal, 'u');
		System.out.println(moves);
		System.out.println(moves.size());
	}

}
