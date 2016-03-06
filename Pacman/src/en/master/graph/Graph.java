package en.master.graph;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import en.master.Game;
import en.master.characters.Ghost;

public class Graph {

	private PriorityQueue<Node> openList;
	private Game game;

	public Graph(Game game) {
		this.game = game;
		openList = new PriorityQueue<Node>();
	}

	// Algorithme d'A*
	// https://fr.wikipedia.org/wiki/Algorithme_A*
	public void reach(Node goal, Point start, Ghost g) {
		LinkedList<Node> closedList = new LinkedList<Node>();
		openList.add(new Node(start));
		while (!openList.isEmpty()) {
			Node u = openList.poll();
			if (u.getPos().x == goal.getPos().x && u.getPos().y == goal.getPos().y) {
				// TODO reconstituer chemin
				// terminer sans erreur
			} else {
				Iterator<Node> it = u.neighbours(game.getLab()).iterator();
				for (Node v = it.next(); it.hasNext(); v = it.next()) {
					if (g.noReturn(v.getPos())) {
						if (closedList.contains(v) && closedList.get(closedList.indexOf(v)).compareTo(v) != -1
								|| openList.contains(v)) {
							Iterator<Node> list = openList.iterator();
							for (Node n = list.next(); list.hasNext(); n = list.next())
								if (n.equals(v))
									if (n.compareTo(v) != -1) {
										openList.add(v);
										break;
									}
						}
					}
				}
				closedList.add(u);
			}
		}
	}

}
