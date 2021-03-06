package en.master.graph;

import java.awt.Point;
import java.util.Comparator;

/**
 * 
 * @author BOURGEOIS Adrien
 *
 */
public class Node implements Comparator<Node>, Comparable<Node> {

	private Point position;
	private int heuristic;
	private char dir;

	public Node(Point position) {
		this.position = position;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		Node other = (Node) o;
		return position.x == other.position.x && position.y == other.position.y;
	}

	@Override
	public int compare(Node arg0, Node arg1) {
		if (arg0.heuristic < arg1.heuristic)
			return -1;
		else if (arg0.heuristic == arg1.heuristic)
			return 0;
		else
			return 1;
	}

	@Override
	public int compareTo(Node other) {
		return compare(this, other);
	}

	public Point getPosition() {
		return position;
	}

	private int manhattan(Point goal) {
		return Math.abs(position.x - goal.x) + Math.abs(position.y - goal.y);
	}

	/**
	 * The heuristic is calculate with the Manhattan distance, and the cost to
	 * reach the current node.
	 * 
	 * @param cost
	 *            the cost to reach the current node.
	 * @param goal
	 *            where to go.
	 * @return the heuristic from the current node to the goal.
	 */
	public int heuristic(int cost, Node goal) {
		heuristic = cost + manhattan(goal.position);
		return heuristic;
	}

	public char getDir() {
		return dir;
	}

	public void setDir(char dir) {
		this.dir = dir;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

}
