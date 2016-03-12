package en.master.graph;

import java.awt.Point;
import java.util.Comparator;

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

	public int manhattan(Point goal) {
		return Math.abs(position.x - goal.x) + Math.abs(position.y - goal.y);
	}

	public String toString() {
		return position.toString();
	}

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
