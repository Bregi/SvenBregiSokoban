package view;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLabel;


public class GameView extends JFrame {

	public static void main(String args[]) {
		model.Game game = new model.Game();
		new GameView(game);
	}
	GameView(model.Game game) {
		JLabel jlbHelloWorld = new JLabel("Sokobaaaaaaaaan");
		add(jlbHelloWorld);
		this.setSize(game.getSizeX(), game.getSizeY());
		setVisible(true);
	}
}
