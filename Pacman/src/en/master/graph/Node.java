package en.master.graph;

import java.awt.Point;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import en.master.characters.Ghost;

public class Node implements Comparable<Node>, Comparator<Node> {

	private Node father;
	private int cost;
	private Point pos;
	private Set<Node> arcs;
	private Point goal;
	private char dir;

	public Node() {
		father = null;
		cost = 0;
		arcs = new TreeSet<Node>();
		pos = new Point(1, 1);
		goal = new Point(0, 0);
	}

	public Node(Point pos) {
		father = null;
		cost = 0;
		arcs = new TreeSet<Node>();
		this.pos = pos;
		goal = new Point(0, 0);
	}

	public Node(Node father, Point pos) {
		this.father = father;
		cost = 0;
		goal = new Point(0, 0);
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
			return pos.x == n.pos.x && pos.y == n.pos.y;
		}
		return false;
	}

	@Override
	public int compareTo(Node o) {
		if (o == null)
			return 1;
		else if (equals(o))
			return 0;
		else {
			if (pos.x < o.pos.x)
				return -1;
			else if (o.pos.x < pos.x)
				return 1;
			else if (pos.y < o.pos.y)
				return -1;
			else
				return 1;
		}
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

	public void initiate(Set<Node> graph, char[][] game) {
		if (arcs.size() <= 0) {
			Node toGrow;
			if (father != null)
				arcs.add(father);
			if (game[Math.floorMod(pos.x + 1, game.length)][pos.y] != 'X') {
				if (father == null || Math.floorMod(pos.x + 1, game.length) != father.pos.x) {
					toGrow = new Node(this, new Point(Math.floorMod(pos.x + 1, game.length), pos.y));
					arcs.add(toGrow);
					toGrow.initiate(graph, game);
				}
			}
			if (game[Math.floorMod(pos.x - 1, game.length)][pos.y] != 'X') {
				if (father == null || Math.floorMod(pos.x - 1, game.length) != father.pos.x) {
					toGrow = new Node(this, new Point(Math.floorMod(pos.x - 1, game.length), pos.y));
					arcs.add(toGrow);
					toGrow.initiate(graph, game);
				}
			}
			if (game[pos.x][Math.floorMod(pos.y - 1, game[0].length)] != 'X') {
				if (father == null || Math.floorMod(pos.y - 1, game[0].length) != father.pos.y) {
					toGrow = new Node(this, new Point(pos.x, Math.floorMod(pos.y - 1, game[0].length)));
					arcs.add(toGrow);
					toGrow.initiate(graph, game);
				}
			}
			if (game[pos.x][Math.floorMod(pos.y + 1, game[0].length)] != 'X') {
				if (father == null || Math.floorMod(pos.y + 1, game[0].length) != father.pos.y) {
					toGrow = new Node(this, new Point(pos.x, Math.floorMod(pos.y + 1, game[0].length)));
					arcs.add(toGrow);
					toGrow.initiate(graph, game);
				}
			}
			graph.add(this);
		}
	}

	public Set<Node> neighbours() {
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

	public boolean noReturn(Node v) {
		if (equals(v))
			return true;
		else {
			switch (dir) {
			case 'u':
				return v.dir != 'd';
			case 'd':
				return v.dir != 'u';
			case 'r':
				return v.dir != 'l';
			case 'l':
				return v.dir != 'r';
			}
			return false;
		}
	}

	public void setDirection(Ghost g) {
		dir = g.getDir();
	}

	public void setDirection() {
		if (father != null) {
			if (pos.x - father.pos.x == -1)
				dir = 'u';
			else if (pos.x - father.pos.x == 1)
				dir = 'd';
			else if (pos.y - father.pos.y == -1)
				dir = 'l';
			else
				dir = 'r';
		}
	}

	public char getDirection() {
		return dir;
	}

	@Override
	public String toString() {
		return (father != null) ? father + "\t" : "" + "Position : " + pos;
	}

	public void update() {

	}

	public boolean equalsPos(Point p) {
		return pos.x == p.x && pos.y == p.y;
	}

}
