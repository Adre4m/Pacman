package en.master;

public class Timer {

	// 1/FPS pour savoir s'il faut faire l'update
	public static final long FPS = 60;
	// GMVPS/FPS pour savoir s'il faut faire bouger le fantome
	public static final long GMVPS = 5;
	public static final long VMVPS = 2;
	// PMVPS/FPS pour savoir s'il faut faire bouger Pacman
	public static final long PMVPS = 4;
	// Le temps en secondes que les fantomes restent en prison au debut du jeu
	public static final long PRISON = 8;
	// La durée d'efficacité d'une supergum
	public static final long SUPERGUM = 7;

}
