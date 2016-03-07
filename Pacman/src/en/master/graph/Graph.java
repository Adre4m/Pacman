package en.master.graph;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

public class Graph {

	private int adj[][];
	private ArrayList<Point> vertex;

	public Graph(char[][] game) {
		vertex = new ArrayList<Point>();
		init(game);
		adjancy();
	}

	private void init(char[][] game) {
		int numv = 0;
		for (int i = 0; i < game.length; i++)
			for (int j = 0; j < game[0].length; ++j)
				if (game[i][j] != 'X') {
					vertex.add(new Point(i, j));
					numv++;
				}
		adj = new int[numv][numv];
	}

	private void adjancy() {
		for (int i = 0; i < adj.length; ++i) {
			Point p1 = vertex.get(i);
			adj[i][i] = 0;
			for (int j = 0; j < adj.length; ++j) {
				if (j != i) {
					Point p2 = vertex.get(j);
					if (p1.x == p2.x || p1.y == p2.y)
						adj[i][j] = Math.abs((p1.x - p2.x) + (p1.y - p2.y));
				}
			}
		}
	}

	public Stack<Point> reach(Point start, Point goal) {
		int index = 0, end = 0;
		for (int i = 0; i < vertex.size(); ++i) {
			if (vertex.get(i).equals(start))
				index = i;
			if (vertex.get(i).equals(goal))
				end = i;
		}
		return reach(index, end);
	}

	private Stack<Point> reach(int index, int goal) {
		return null;
	}

	private int manhattan(Point start, Point goal) {
		return Math.abs(start.x - goal.x) + Math.abs(start.y + goal.y);
	}

	private int heuristic(int start, int goal) {
		return adj[start][goal] + manhattan(vertex.get(start), vertex.get(goal));
	}

}
