package ch.bfh.ti.projekt1.sokoban.view;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLabel;
import model.*;
import controller.*;

public class GameView extends JFrame {

	public static void main(String args[]) {
		Game game = new Game();
		new GameView(game);
	}
	GameView(Game game) {
		JLabel jlbSokoban = new JLabel("Sokobaaaaaaaaan");
		add(jlbSokoban);
		this.setSize(game.getSizeX(), game.getSizeY());
		setVisible(true);
	}
}
