package en.master;

public class Game {
	
	private char[][] game;
	
	public Game() {
		//40 par 30 pour 4/3, il faut decide la taille du jeu
		//le tableau prend egalement en compte la zone score etc..
		game = new char[40][30];
	}

}
