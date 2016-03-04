package en.master.graph;

public class Arc {

	private Node n1;
	private Node n2;
	private int weight;

	public Arc(Node n1, Node n2, int weight) {
		this.n1 = n1;
		this.n2 = n2;
		this.weight = weight;
	}

	public Node getN1() {
		return n1;
	}

	public void setN1(Node n1) {
		this.n1 = n1;
	}

	public Node getN2() {
		return n2;
	}

	public void setN2(Node n2) {
		this.n2 = n2;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Arc) {
			Arc other = (Arc) obj;
			if ((n1.equals(other.n1) && n2.equals(other.n2)) || (n1.equals(other.n2) && n2.equals(other.n1)))
				return true;
		}
		return false;
	}
}
