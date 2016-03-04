package en.master.graph;

import java.awt.Point;
import java.util.ArrayList;

public class Node {

	private Node father;
	private int g;
	private Point currentPos;
	private ArrayList<Arc> arcs;
	private String name;

	public Node() {
		father = null;
		g = 0;
		arcs = new ArrayList<Arc>();
		this.name = "n0";
	}

	public Node(String name) {
		father = null;
		g = 0;
		arcs = new ArrayList<Arc>();
		this.name = name;
	}

	public Node(Node father) {
		if (father != null) {
			this.father = father;
			g = this.father.g + 1;
		} else
			g = 0;
		arcs = new ArrayList<Arc>();
	}

	private int md(Point goal) {
		return Math.abs(currentPos.x - goal.x) + Math.abs(currentPos.y - goal.y);
	}

	public int h(Point goal) {
		return g + md(goal);
	}

	public int compareTo(Object n, Point goal) {
		if (n instanceof Node) {
			int heuristic1 = this.h(goal);
			int heuristic2 = ((Node) n).h(goal);
			if (heuristic1 < heuristic2)
				return 1;
			else if (heuristic1 == heuristic2)
				return 0;
		}
		return -1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Node) {
			Node n = (Node) obj;
			return this.name.equals(n.name);
		}
		return false;
	}

	public boolean addArc(Node n) {
		if (this.equals(n))
			return false;
		else {
			Arc toAdd = new Arc(this, n, g + 1);
			if (arcs.contains(toAdd))
				return false;
			else {
				arcs.add(toAdd);
				return true;
			}
		}
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public Point getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(Point currentPos) {
		this.currentPos = currentPos;
	}

	public ArrayList<Arc> getArcs() {
		return arcs;
	}

	public void setArcs(ArrayList<Arc> arcs) {
		this.arcs = arcs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int degree() {
		return arcs.size();
	}

}
