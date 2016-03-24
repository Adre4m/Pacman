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
	private int heigh;
	private int length;

	public Graph(char[][] game) {
		vertex = new ArrayList<Node>();
		heigh = game.length;
		length = game[0].length;
		init(game);
		adjacency();
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
	}

	private void adjacency() {
		for (int i = 0; i < adj.length; ++i) {
			Node n1 = vertex.get(i);
			adj[i][i] = 0;
			for (int j = i + 1; j < adj.length; ++j) {
				Node n2 = vertex.get(j);
				if (n1.getPosition().x == n2.getPosition().x || n1.getPosition().y == n2.getPosition().y) {
					adj[i][j] = Math
							.abs((n1.getPosition().x - n2.getPosition().x) + (n1.getPosition().y - n2.getPosition().y));
					adj[j][i] = Math
							.abs((n1.getPosition().x - n2.getPosition().x) + (n1.getPosition().y - n2.getPosition().y));
				} else {
					adj[i][j] = 0;
					adj[j][i] = 0;
				}
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
			return goal.x != Math.floorMod(start.x - 1, heigh);
		case 'u':
			return goal.x != Math.floorMod(start.x + 1, heigh);
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
