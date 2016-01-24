package en.master;

public class Ghost extends Character {

	private boolean isVunerable;
	
	public Ghost() {
		super();
		isVunerable = false;
	}

	public boolean isVunerable() {
		return isVunerable;
	}

	public void setVunerable(boolean isVunerable) {
		this.isVunerable = isVunerable;
	}
	
}
