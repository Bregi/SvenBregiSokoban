package ch.bfh.ti.projekt1.sokoban.view;

import ch.bfh.ti.projekt1.sokoban.controller.BoardController;
import ch.bfh.ti.projekt1.sokoban.core.CoreConstants;
import ch.bfh.ti.projekt1.sokoban.core.LevelService;
import ch.bfh.ti.projekt1.sokoban.core.LevelServiceImpl;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author marcoberger
 * @since 24/10/13 14:29
 */
public class StartScreen {
    private LevelService levelService = new LevelServiceImpl();

    private JFrame frame;

    private JMenuBar menuBar;
    private JMenuBar aaaBar;

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

        // what happens when the user clicks on start new game
        menuFileNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // LevelDimensionDialog.showDimensionDialog(frame);

                File firstLevel = new File(CoreConstants.getProperty("game.firstlevel"));

                BoardController controller = levelService.getLevel(firstLevel);
                BoardView view = (BoardView) controller.getView();
                frame.setContentPane(view);  //TODO
                // load the game Menu
                loadGameMenu();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                view.requestFocusInWindow();
                frame.getContentPane().revalidate();
            }
        });

        // what happens when the user clicks on load a level
        menuFileLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Create a file chooser
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
                        "xml files (*.xml)", "xml");
                // set the filter to only allow xml files
                fc.setFileFilter(xmlfilter);
                fc.setDialogTitle("Open schedule file");
                // set selected filter
                fc.setFileFilter(xmlfilter);
                // Handle open button action.
                int returnVal = fc.showOpenDialog(frame);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    System.out.println(file.toString());
                    // TODO: (also validate)
                    BoardController controller = levelService.getLevel(file);

                    frame.setJMenuBar(new StartMenuView());
                    BoardView view = (BoardView) controller.getView();
                    frame.setContentPane(view); //TODO

                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    view.requestFocusInWindow();
                    frame.getContentPane().revalidate();
                } else {
                    // show that the file was not applicable in this case
                }

                frame.getContentPane().revalidate();
            }
        });
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void loadGameMenu() {

        // spiel
        JMenu menuFile = new JMenu("Spiel");
        JMenuItem itmNew = new JMenuItem("Neues Spiel starten");
        JMenuItem itmReload = new JMenuItem("Level neu starten");
        JMenuItem itmSave = new JMenuItem("Spiel speichern");
        JMenuItem itmLoad = new JMenuItem("Spiel laden");
        JMenuItem itmClose = new JMenuItem("Spiel beenden");

        // optionen
        JMenu menuEdit = new JMenu("Optionen");
        JMenuItem itmStatistics = new JMenuItem("Levelstatistik anzeigen");
        JMenuItem itmBest = new JMenuItem("Bestergebnisse anzeigen");

        // Leveleditor
        JMenu menuLevelEditor = new JMenu("Level Editor");
        JMenuItem itmLevelEditorStart = new JMenuItem("Level Editor Starten");

        // Spiel
        menuFile.add(itmNew);
        menuFile.addSeparator();

        menuFile.add(itmReload);
        menuFile.addSeparator();

        menuFile.add(itmSave);
        menuFile.addSeparator();

        menuFile.add(itmLoad);
        menuFile.addSeparator();

        menuFile.add(itmClose);

        // optionen
        menuEdit.add(itmStatistics);
        menuEdit.addSeparator();
        menuEdit.add(itmBest);

        // leveleditor
        menuLevelEditor.add(itmLevelEditorStart);

        aaaBar = new JMenuBar();
        this.frame.setJMenuBar(aaaBar);

        aaaBar.add(menuFile);
        aaaBar.add(menuEdit);
        aaaBar.add(menuLevelEditor);

        // what happens when the user clicks on start new game
        itmNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // LevelDimensionDialog.showDimensionDialog(frame);

                File firstLevel = new File(CoreConstants.getProperty("game.firstlevel"));

                BoardController controller = levelService.getLevel(firstLevel);

                BoardView view = (BoardView) controller.getView();

                frame.setContentPane(view);  //TODO
                // load the game Menu
                loadGameMenu();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                view.requestFocusInWindow();

                frame.getContentPane().revalidate();

            }
        });

    }
}
