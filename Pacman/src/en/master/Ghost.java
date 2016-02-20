package en.master;

public abstract class Ghost extends Character {

	private boolean isVunerable;
	private boolean isFree;
	private boolean isReturningToJail;
	protected long jailed;
	private long vulnerable;
	private char old;
	private String vulnerableSprite;
	private String eyeSprite;
	// private int speed;

	public Ghost(String sprite) {
		super(sprite);
		vulnerableSprite = "Blue_ghost.gif";
		eyeSprite = "Ghost_eyes";
		isVunerable = false;
		isFree = false;
		isReturningToJail = false;
		jailed = Timer.PRISON;
	}

	public boolean isVunerable() {
		return isVunerable;
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
		isEaten();
		chased();
	}

	private void isEaten() {
		//System.out.println("a complete");
	}

	protected abstract void patrol();

	private void chased() {
		//System.out.println("a complete");
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

	public long getJailed() {
		return jailed;
	}

	public void setJailed(long jailed) {
		this.jailed = jailed;
	}

	public long getVulnerable() {
		return vulnerable;
	}

	public void setVulnerable(long vulnerable) {
		this.vulnerable = vulnerable;
		if (0 < vulnerable)
			this.isVunerable = true;
		else
			this.isVunerable = false;
	}

	public String getVulnerableSprite() {
		return vulnerableSprite;
	}

	public void setVulnerableSprite(String vulnerableSprite) {
		this.vulnerableSprite = vulnerableSprite;
	}

	public String getEyeSprite() {
		return eyeSprite;
	}

	public void setEyeSprite(String eyeSprite) {
		this.eyeSprite = eyeSprite;
	}

}
