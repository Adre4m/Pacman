package en.master;

public enum Difficulty {
	EASY(0), NORMAL(1), HARD(2), HARDER(3), INSANE(4), SERIOUSLY(5), WTF(6), MEGALOVANIA(7);

	private final int level;

	private Difficulty(int level) {
		this.level = level;
	}

	int speedAdd() {
		return level;
	}
}
