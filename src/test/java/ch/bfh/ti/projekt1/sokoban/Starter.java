package ch.bfh.ti.projekt1.sokoban;

import javax.swing.JFrame;

import ch.bfh.ti.projekt1.sokoban.controller.GameController;
import ch.bfh.ti.projekt1.sokoban.view.BoardView;
import ch.bfh.ti.projekt1.sokoban.view.StartMenuView;

/**
 * This class is only for testing purposes
 *
 * @author svennyffenegger
 */
public class Starter {

    public static void main(String[] args) {
        System.out.println("Starting Demo");

        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 400, 400);

        GameController controller = new GameController();
        BoardView view = controller.loadLevel("src/test/resources/ch/bfh/ti/projekt1/sokoban/level1.xml");
        
        frame.add(view);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        view.requestFocusInWindow();
        frame.setJMenuBar(new StartMenuView());
        frame.setVisible(true);

    }
}
