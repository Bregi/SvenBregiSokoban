package ch.bfh.ti.projekt1.sokoban.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import ch.bfh.ti.projekt1.sokoban.controller.GameController;

/**
 * @author marcoberger
 * @since 24/10/13 14:29
 */
public class StartScreen {
    private JFrame frame;

    private JMenuBar menuBar;

    private JMenu menuFile;

    private JMenuItem menuFileNew;
    private JMenuItem menuFileLoad;

    public StartScreen() {
        frame = new JFrame("Sokoban");

        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        menuFile = new JMenu("Game");
        menuBar.add(menuFile);

        menuFileNew = new JMenuItem("New Game");
        menuFileLoad = new JMenuItem("Load Game");
        menuFile.add(menuFileNew);
        menuFile.add(menuFileLoad);
        menuFileNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //LevelDimensionDialog.showDimensionDialog(frame);

                GameController controller = new GameController();
                BoardView view = controller.loadLevel("src/test/resources/ch/bfh/ti/projekt1/sokoban/level1.xml");
                frame.setJMenuBar(new StartMenuView());
                frame.setContentPane(view);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                view.requestFocusInWindow();
               // frame.setJMenuBar(new StartMenuView());
               // frame.setVisible(true);
                //frame.setContentPane(new BoardView(5, 5));
                frame.getContentPane().revalidate();
            }
        });

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
