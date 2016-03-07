package en.master;

public class Timer {

	// 1/FPS pour savoir s'il faut faire l'update
	public static final short FPS = 60;
	// GMVPS/FPS pour savoir s'il faut faire bouger le fantome
	public static final short GMVPS = 5;
	public static final short VMVPS = 2;
	// PMVPS/FPS pour savoir s'il faut faire bouger Pacman
	public static final short PMVPS = 4;
	// Le temps en secondes que les fantomes restent en prison au debut du jeu
	public static final short PRISON = 8;
	// La durée d'efficacité d'une supergum
	public static final short SUPERGUM = 7;

}
