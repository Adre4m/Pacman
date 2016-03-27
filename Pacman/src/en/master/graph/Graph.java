package en.master.graph;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 
 * @author BOURGEOIS Adrien
 *
 */
public class Graph {

	private int adj[][];
	private ArrayList<Node> vertex;
	private int height;
	private int length;

	public Graph(char[][] game) {
		vertex = new ArrayList<Node>();
		height = game.length;
		length = game[0].length;
		init(game);
		adjacency();
		int numLigne = 0;
		for (int i = 0; i < adj.length; ++i) {
			System.out.print(numLigne + " - ");
			for (int j = i + 1; j < adj.length; ++j)
				if (adj[i][j] != 0)
					System.out.print(adj[i][j]);
			System.out.println();
			numLigne++;
		}
		System.out.println(adj.length);
	}

	private void init(char[][] game) {
		int numv = 0;
		for (int i = 0; i < game.length; i++)
			for (int j = 0; j < game[0].length; ++j)
				if (game[i][j] != 'X' && game[i][j] != 'W') {
					vertex.add(new Node(new Point(i, j)));
					numv++;
				}
		adj = new int[numv][numv];
		for (int i = 0; i < numv; ++i)
			for (int j = 0; j < numv; ++j)
				adj[i][j] = 0;
	}

	private void adjacency() {
		for (int i = 0; i < adj.length; ++i) {
			Node n1 = vertex.get(i);
			Node p1 = new Node(new Point(Math.floorMod(n1.getPosition().x - 1, height), n1.getPosition().y));
			Node p2 = new Node(new Point(Math.floorMod(n1.getPosition().x + 1, height), n1.getPosition().y));
			Node p3 = new Node(new Point(n1.getPosition().x, Math.floorMod(n1.getPosition().y - 1, length)));
			Node p4 = new Node(new Point(n1.getPosition().x, Math.floorMod(n1.getPosition().y + 1, length)));
			if (vertex.contains(p1)) {
				adj[i][vertex.indexOf(p1)] = 1;
				adj[vertex.indexOf(p1)][i] = 1;
			}
			if (vertex.contains(p2)) {
				adj[i][vertex.indexOf(p2)] = 1;
				adj[vertex.indexOf(p2)][i] = 1;
			}
			if (vertex.contains(p3)) {
				adj[i][vertex.indexOf(p3)] = 1;
				adj[vertex.indexOf(p3)][i] = 1;
			}
			if (vertex.contains(p4)) {
				adj[i][vertex.indexOf(p4)] = 1;
				adj[vertex.indexOf(p4)][i] = 1;
			}
		}
	}

	/**
	 * @see <a href=
	 *      "https://en.wikipedia.org/wiki/A*_search_algorithm#Pseudocode"
	 *      target="_blank">A* search algorithm</a>
	 * @param start
	 *            from where calculate the path.
	 * @param goal
	 *            where to go
	 * @param dir
	 *            in which direction the ghost is heading.
	 * @return the path in a {@literal Stack<Character>} to provides the ghost
	 *         what directions he should take.
	 */
	public Stack<Character> reach(Point start, Point goal, char dir) {
		return reach(vertex.get(vertex.indexOf(new Node(start))), vertex.get(vertex.indexOf(new Node(goal))), dir);
	}

	private Stack<Character> reach(Node start, Node goal, char dir) {
		Map<Node, Integer> cost = new HashMap<Node, Integer>();
		cost.put(start, 0);
		Map<Node, Integer> heuristic = new HashMap<Node, Integer>();
		heuristic.put(start, start.heuristic(0, goal));
		LinkedList<Node> closedSet = new LinkedList<Node>();
		PriorityQueue<Node> openSet = new PriorityQueue<Node>();
		Map<Node, Node> cameFrom = new HashMap<Node, Node>();
		start.setDir(dir);
		openSet.add(start);
		while (!openSet.isEmpty()) {
			Node current = openSet.poll();
			if (current.equals(goal)) {
				return path(cameFrom, start, goal, dir);
			}
			closedSet.add(current);
			int index = vertex.indexOf(current);
			for (int i = 0; i < adj.length; ++i) {
				if (1 == adj[index][i]) {
					Node neighbour = vertex.get(i);
					if (noReturn(current.getPosition(), neighbour.getPosition(), current.getDir())) {
						if (closedSet.contains(neighbour))
							continue;
						if (!openSet.contains(neighbour))
							openSet.add(neighbour);
						else if (getQueue(openSet, neighbour).compareTo(neighbour) == 1)
							continue;
						cameFrom.put(neighbour, current);
						cost.put(neighbour, cost.get(current) + adj[index][i]);
						heuristic.put(neighbour, neighbour.heuristic(cost.get(neighbour), goal));
						neighbour.setDir(findDir(current, neighbour));
						cameFrom.put(neighbour, current);
					}
				}
			}
		}
		return null;
	}

	private boolean noReturn(Point start, Point goal, char dir) {
		switch (dir) {
		case 'd':
			return goal.x != Math.floorMod(start.x - 1, height);
		case 'u':
			return goal.x != Math.floorMod(start.x + 1, height);
		case 'r':
			return goal.y != Math.floorMod(start.y - 1, length);
		case 'l':
			return goal.y != Math.floorMod(start.y + 1, length);
		}
		return false;
	}

	public int[][] getAdj() {
		return adj;
	}

	public ArrayList<Node> getVertex() {
		return vertex;
	}

	private Node getQueue(PriorityQueue<Node> list, Node n) {
		Iterator<Node> it = list.iterator();
		while (it.hasNext()) {
			Node other = it.next();
			if (n.equals(other))
				return other;
		}
		return null;
	}

	private Stack<Character> path(Map<Node, Node> cameFrom, Node start, Node goal, char dir) {
		Stack<Character> moves = new Stack<Character>();
		Node current = goal;
		while (!current.equals(start)) {
			moves.push(current.getDir());
			current = cameFrom.get(current);
		}
		return moves;
	}

	private char findDir(Node current, Node neighbour) {
		if (current.getPosition().x == neighbour.getPosition().x)
			return (current.getPosition().y == neighbour.getPosition().y - 1) ? 'r' : 'l';
		else
			return (current.getPosition().x == neighbour.getPosition().x - 1) ? 'd' : 'u';
	}

	public String toString() {
		return "Graph containing : " + adj.length + " nodes.";
	}

}
