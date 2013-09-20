package view;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLabel;
import model.*;

public class GameView extends JFrame {

	public static void main(String args[]) {
		Game game = new Game();
		new GameView(game);
	}
	GameView(Game game) {
		JLabel jlbHelloWorld = new JLabel("Sokobaaaaaaaaan");
		add(jlbHelloWorld);
		this.setSize(game.getSizeX(), game.getSizeY());
		setVisible(true);
	}
}
