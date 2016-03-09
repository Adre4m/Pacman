package en.window;

public class NodeScore {

	private String num;
	private String pseudo;
	private String score;

	public NodeScore(){
		num = null;
		pseudo = null;
		score = null;
	}

	public NodeScore(String n, String p, String s) {
		num = n;
		pseudo = p;
		score = s;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "NodeScore [num=" + num + ", pseudo=" + pseudo + ", score="
				+ score + "]";
	}
}
