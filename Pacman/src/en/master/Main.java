package en.master;
import en.controls.ControlsKey;
import en.window.Frame;

public class Main {

	public static void main(String[] args) {
		Frame f = new Frame();
		do {
			// Menu de f etc, jusqu'à l'initialisation du jeu
			// while (!f.gameStarted())
			final Game game = new Game();
			game.init();
			// Selon le choix utilisateur
			// si clavier
			// ici ce serai style f.getControlsChoice()
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					// Ajouter a la frame le nouveau controls key
					/* f.game.addKeyListener( */new ControlsKey(game)/* ) */;
				}
			});
			// sinon
			/*
			 * javax.swing.SwingUtilities.invokeLater(new Runnable() { public
			 * void run() { // Ajouter a la frame le nouveau controls key
			 * f.game.addMouseListener(new ControlsMouse(game) ); } });
			 */
			// va jouer tant que le joueur n'a pas gagné ou n'est pas mort
			game.play(f);
			// une fois fini afficher un choix : rejouer ou retour menu
			// revenir au menu et dans ce cas attendre les dirrectives du joueur
			// si le joueur choisi de rejouer laisse f.gameStarted a true sinon
			// le mettre a false
		} while (true);
	}

}
