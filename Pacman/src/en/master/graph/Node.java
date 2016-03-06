package en.master.graph;

import java.awt.Point;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Node implements Comparable<Node>, Comparator<Node> {

	private Node father;
	private int cost;
	private Point pos;
	private Set<Node> arcs;
	private Point goal;

	public Node() {
		father = null;
		cost = 0;
		arcs = new TreeSet<Node>();
		pos = new Point(0, 0);
	}

	public Node(Point pos) {
		father = null;
		cost = 0;
		arcs = new TreeSet<Node>();
		this.pos = pos;
	}

	public Node(Node father, Point pos) {
		if (father != null) {
			this.father = father;
			cost = this.father.cost + 1;
		} else
			cost = 0;
		arcs = new TreeSet<Node>();
		this.pos = pos;
	}

	private int md() {
		return Math.abs(pos.x - goal.x) + Math.abs(pos.y - goal.y);
	}

	public int h() {
		return cost + md();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Node) {
			Node n = (Node) obj;
			return this.pos.x == n.pos.x && this.pos.y == n.pos.y;
		}
		return false;
	}

	@Override
	public int compareTo(Node o) {
		if (o == null)
			return 1;
		else if (equals(o))
			return 0;
		else
			return (h() < o.h()) ? -1 : 1;
	}

	public boolean addArc(Node n) {
		if (!equals(n))
			return arcs.add(n);
		return false;
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public Set<Node> getArcs() {
		return arcs;
	}

	public void setArcs(Set<Node> arcs) {
		this.arcs = arcs;
	}

	public int degree() {
		return arcs.size();
	}

	public Set<Node> neighbours(char[][] game) {
		if (game[Math.floorMod(pos.x + 1, game.length)][pos.y] != 'X')
			addArc(new Node(this, new Point(Math.floorMod(pos.x + 1, game.length), pos.y)));
		if (game[Math.floorMod(pos.x - 1, game.length)][pos.y] != 'X')
			addArc(new Node(this, new Point(Math.floorMod(pos.x - 1, game.length), pos.y)));
		if (game[pos.x][Math.floorMod(pos.y - 1, game[0].length)] != 'X')
			addArc(new Node(this, new Point(pos.x, Math.floorMod(pos.y - 1, game[0].length))));
		if (game[pos.x][Math.floorMod(pos.y + 1, game[0].length)] != 'X')
			addArc(new Node(this, new Point(pos.x, Math.floorMod(pos.y + 1, game[0].length))));
		return arcs;
	}

	@Override
	public int compare(Node arg0, Node arg1) {
		return arg0.compareTo(arg1);
	}

	public Point getGoal() {
		return goal;
	}

	public void setGoal(Point goal) {
		this.goal = goal;
	}

}
