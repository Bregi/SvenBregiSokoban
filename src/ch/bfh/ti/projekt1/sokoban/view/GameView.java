package ch.bfh.ti.projekt1.sokoban.view;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLabel;
import ch.bfh.ti.projekt1.sokoban.model.*;
import ch.bfh.ti.projekt1.sokoban.controller.*;

public class GameView extends JFrame {

	public static void main(String args[]) {
		Game game = new Game();
		new GameView(game);
	}
	GameView(Game game) {
		JLabel jlbSokoban = new JLabel("Sokobaaaaaaaaan");
		add(jlbSokoban);

        SokoComponent sokoComponent = new SokoComponent();
        sokoComponent.setBounds(100,100,100,100);

        add(sokoComponent);
		this.setSize(game.getSizeX(), game.getSizeY());
		setVisible(true);
	}
}
