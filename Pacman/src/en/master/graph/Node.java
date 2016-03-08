package en.master.graph;

import java.awt.Point;
import java.util.Comparator;

public class Node implements Comparator<Node>, Comparable<Node> {

	private Point position;
	private int cost;
	private int heuristic;
	private Node father;
	private char dir;

	public Node(Point position) {
		this.position = position;
		father = null;
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
			return 1;
		else if (arg0.heuristic == arg1.heuristic)
			return 0;
		else
			return -1;
	}

	@Override
	public int compareTo(Node other) {
		return compare(this, other);
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	public Point getPosition() {
		return position;
	}

	public int manhattan(Point goal) {
		return Math.abs(position.x - goal.x) + Math.abs(position.y + goal.y);
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	/*
	 * public void heuristic(Node goal) { heuristic = manhattan(position,
	 * goal.position); // adj[start][goal] +
	 * manhattan(vertex.get(start).getPosition(), //
	 * vertex.get(goal).getPosition()); }
	 */

	public String toString() {
		return position.toString();
	}

	public int heuristic(Node goal) {
		return manhattan(goal.position);
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
