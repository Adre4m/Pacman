package en.master;

public class Timer {

	// 1/FPS pour savoir s'il faut faire l'update
	public static final double FPS = 60.;
	// GMVPS/FPS pour savoir s'il faut faire bouger le fantome
	public static final double GMVPS = 2.;
	// PMVPS/FPS pour savoir s'il faut faire bouger Pacman
	public static final double PMVPS = 1.;
	// Le temps en secondes que les fantomes restent en prison au debut du jeu
	public static final double PRISON = 8.;
	// La durée d'efficacité d'une supergum
	public static final double SUPERGUM = 7.;

}
