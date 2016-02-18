package en.master;

public abstract class Ghost extends Character {

	private boolean isVunerable;
	private boolean isFree;
	private boolean isReturningToJail;
	private char old;
	// private int speed;

	public Ghost() {
		super();
		isVunerable = false;
		isFree = false;
		isReturningToJail = false;
	}

	public boolean isVunerable() {
		return isVunerable;
	}

	public void setVunerable(boolean isVunerable) {
		this.isVunerable = isVunerable;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public boolean isReturningToJail() {
		return isReturningToJail;
	}

	public void setReturningToJail(boolean isReturningToJail) {
		this.isReturningToJail = isReturningToJail;
	}

	// TODO IA
	// trois fonctions de l'ia, la fonction dans le cas ou le fantome est mangé
	// et ou il chasse sont communes

	public void ia() {

	}

	private void isEaten() {

	}

	protected abstract void patrol();

	private void chased() {

	}

	public char getOld() {
		return old;
	}

	public void setOld(char old) {
		this.old = old;
	}

	public char toChar() {
		if (isVunerable)
			return 'V';
		else if (isReturningToJail)
			return 'E';
		else
			return 'G';
	}

}
