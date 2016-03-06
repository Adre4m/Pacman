package en.master.graph;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

import en.master.characters.Ghost;

public class Graph {

	private Set<Node> set;

	public Graph(char[][] game) {
		set = new TreeSet<Node>();
		initiate(game);
	}

	private void initiate(char[][] game) {
		Node n = new Node();
		System.out.println(n);
		n.initiate(set, game);
		System.out.println("Set : " + set);
	}

	// Algorithme d'A*
	// https://fr.wikipedia.org/wiki/Algorithme_A*
	public Node reach(Point goal, Point start, Ghost g) {
		PriorityQueue<Node> openList = new PriorityQueue<Node>();
		LinkedList<Node> closedList = new LinkedList<Node>();
		Node initial = getNode(start);
		initial.setGoal(goal);
		initial.setDirection(g);
		openList.add(initial);
		while (!openList.isEmpty()) {
			Node u = openList.poll();
			// System.out.println("U : " + u);
			if (u.getPos().x == goal.x && u.getPos().y == goal.y) {
				return u;
			} else {
				// System.out.println("OBJECTIF NON ATTEINT");
				Iterator<Node> it = u.neighbours().iterator();
				while (it.hasNext()) {
					Node v = it.next();
					if (u.noReturn(v)) {
						// System.out.println("V : " + v);
					}
					Node tmp = null;
					if (openList.contains(v)) {
						Iterator<Node> list = openList.iterator();
						while (list.hasNext()) {
							tmp = list.next();
							if (tmp.equals(v))
								break;
						}
					}
					if (closedList.contains(v) && closedList.get(closedList.indexOf(v)).compareTo(v) == -1
							|| openList.contains(v) && tmp.compareTo(v) == -1)
						continue;
					else {
						tmp.update();
						// openList.add(v);
					}
				}
				closedList.add(u);
			}
		}
		return null;
	}

	public Node getNode(Point p) {
		System.out.println(set);
		Iterator<Node> it = set.iterator();
		while (it.hasNext()) {
			Node n = it.next();
			System.out.println(n);
			if (n.equalsPos(p))
				return n;
		}
		return null;
	}
}
