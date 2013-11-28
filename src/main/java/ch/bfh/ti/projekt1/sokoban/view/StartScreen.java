package ch.bfh.ti.projekt1.sokoban.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import ch.bfh.ti.projekt1.sokoban.controller.BoardController;
import ch.bfh.ti.projekt1.sokoban.core.LevelService;
import ch.bfh.ti.projekt1.sokoban.core.LevelServiceImpl;
import ch.bfh.ti.projekt1.sokoban.editor.SokobanEditor;
import ch.bfh.ti.projekt1.sokoban.model.Level;

/**
 * @author marcoberger
 * @since 24/10/13 14:29
 */
public class StartScreen {

	private LevelService levelService = new LevelServiceImpl();
	private String levelName;
	private Level levels;
	private int currentStoryLevel;
	private String currentLevel;
	private JFrame frame;

	private BoardView view;
	private JMenuBar menuBar;
	private JMenuBar aaaBar;

	private JMenu menuFile;

	private JMenuItem menuFileNew;
	private JMenuItem menuFileLoad;
	private JMenuItem menuFileLoadLevel;

	public StartScreen() {
		levels = new Level();
		frame = new JFrame("Sokoban");
		frame.setSize(500,300);
		frame.setLocationRelativeTo(null);
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		menuFile = new JMenu("Game");
		menuBar.add(menuFile);

		menuFileNew = new JMenuItem("New Game");
		menuFileLoad = new JMenuItem("Load Game");
		menuFileLoadLevel = new JMenuItem("Load Level");
		menuFile.add(menuFileNew);
		menuFile.add(menuFileLoad);
		menuFile.add(menuFileLoadLevel);
		
		// what happens when the user clicks on start new game
		menuFileNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentStoryLevel = 0;
				currentLevel = levels.getLevel(currentStoryLevel);
				// LevelDimensionDialog.showDimensionDialog(frame);

				// GameController controller = new GameController();
				// gameController = controller;
				BoardController board = levelService.getLevel(new File(levels
						.getLevel(currentStoryLevel)));
				view = (BoardView) board.getView();
				frame.setSize(view.getWindowSizeX(),view.getWindowSizeY());
				frame.setLocationRelativeTo(null);
				frame.setContentPane(view);
				// load the game Menu
				loadGameMenu();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				view.requestFocusInWindow();
				frame.getContentPane().revalidate();
			}
		});

		// what happens when the user clicks on load game
		menuFileLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Create a file chooser
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter sokfilter = new FileNameExtensionFilter(
						"Sokoban game files (*.sok)", "sok");
				// set the filter to only allow xml files
				fc.setFileFilter(sokfilter);
				fc.setDialogTitle("Open schedule file");
				// set selected filter
				fc.setFileFilter(sokfilter);
				// Handle open button action.
				int returnVal = fc.showOpenDialog(frame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					System.out.println(file.toString());
					levelName = file.toString();
					// TODO: (also validate)
					BoardController board = levelService
							.getLevel(new File(
									"src/test/resources/ch/bfh/ti/projekt1/sokoban/level1.xml"));

					view = (BoardView) board.getView();
					frame.setSize(view.getWindowSizeX(),view.getWindowSizeY());
					frame.setLocationRelativeTo(null);
					// view = controller.loadLevel(file.toString());
					frame.setJMenuBar(new StartMenuView());
					frame.setContentPane(view);

					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					view.requestFocusInWindow();
					frame.getContentPane().revalidate();
				} else {
					// show that the file was not applicable in this case
				}

				frame.getContentPane().revalidate();
			}
		});
		
		// what happens when the user clicks on load a level
		menuFileLoadLevel.addActionListener(new ActionListener() {
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
					levelName = file.toString();
					// TODO: (also validate)
					BoardController board = levelService
							.getLevel(new File(
									"src/test/resources/ch/bfh/ti/projekt1/sokoban/level1.xml"));

					view = (BoardView) board.getView();
					frame.setSize(view.getWindowSizeX(),view.getWindowSizeY());
					frame.setLocationRelativeTo(null);
					// view = controller.loadLevel(file.toString());
					frame.setJMenuBar(new StartMenuView());
					frame.setContentPane(view);

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

		aaaBar = getGameMenuBar();
		this.frame.setJMenuBar(aaaBar);

	}

	/*
	 * provides the main functions inside the regular level view of the game
	 * 
	 * @return
	 */
	public JMenuBar getGameMenuBar() {

		// spiel
		JMenu menuFile = new JMenu("Spiel");
		JMenuItem itmNew = new JMenuItem("Neues Spiel starten");
		JMenuItem itmNext = new JMenuItem("Nächstes Level spielen");
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

		// itmNext.setEnabled(false);
		menuFile.add(itmNext);
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

		aaaBar.add(menuFile);
		aaaBar.add(menuEdit);
		aaaBar.add(menuLevelEditor);

		// what happens when the user clicks on start new game
		itmNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// LevelDimensionDialog.showDimensionDialog(frame);
				currentStoryLevel = 0;
				// TODO unschÃ¶n
				BoardController board = levelService.getLevel(new File(levels
						.getLevel(currentStoryLevel)));

				view = (BoardView) board.getView();
				frame.setSize(view.getWindowSizeX(),view.getWindowSizeY());
				frame.setLocationRelativeTo(null);

				// view =
				// controller.loadLevel("src/test/resources/ch/bfh/ti/projekt1/sokoban/level1.xml");

				frame.setContentPane(view);

				// load the game Menu
				loadGameMenu();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.getContentPane().revalidate();
			}
		});

		// what happens when the user clicks on play next level
		itmNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// LevelDimensionDialog.showDimensionDialog(frame);
				if ((!view.getIsLevelFinished())) {
					JOptionPane
							.showMessageDialog(
									null,
									"Sie müssen zuerst das aktuelle Level beenden um zum nächsten zu gelangen",
									"Access denied", JOptionPane.ERROR_MESSAGE);
				} else {
					levelName = levels.getLevel(currentStoryLevel+1);
					BoardController board = levelService.getLevel(new File(levelName));

					view = (BoardView) board.getView();
					frame.setSize(view.getWindowSizeX(),view.getWindowSizeY());
					frame.setLocationRelativeTo(null);

					frame.setContentPane(view);

					// load the game Menu
					loadGameMenu();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					frame.getContentPane().revalidate();
				}
			}
		});
		// what happens when the user clicks on restart level
		itmReload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Object[] options = { "Ja", "Nein" };
				int response = JOptionPane
						.showOptionDialog(
								frame,
								"Bist du sicher, dass du das Level neu laden willst? Ungespeicherter Fortschritt geht dabei verloren. ",
								"Level neu laden", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[1]);
				if (response == JOptionPane.YES_OPTION) {
					BoardController board = levelService
							.getLevel(new File(levelName));

					view = (BoardView) board.getView();
					
					
					frame.setContentPane(view);
					
					// load the game Menu
					loadGameMenu();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					view.requestFocusInWindow();
					frame.getContentPane().revalidate();

				}
			}
		});
		// what happens when the user clicks on save progress
		itmSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		// start the level editor
		itmLevelEditorStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SokobanEditor editor = new SokobanEditor();
				// frame.setContentPane(editor.);
			}
		});
		// what happens when the user clicks on load a level
		itmLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// LevelDimensionDialog.showDimensionDialog(frame);

				Object[] options = { "Ja", "Nein" };
				int response = JOptionPane
						.showOptionDialog(
								frame,
								"Bist du sicher, dass du ein neues Spiel starten willst? Ungespeicherter Fortschritt geht dabei verloren. ",
								"Level neu starten", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[1]);

				if (response == JOptionPane.YES_OPTION) {

					
					JFileChooser jFileChooser = new JFileChooser(
							"src/test/resources/ch/bfh/ti/projekt1/sokoban/generated");

					jFileChooser.showOpenDialog(null);
					levelName=jFileChooser.getSelectedFile().toString();
					BoardController board = levelService.getLevel(jFileChooser
							.getSelectedFile());

					view = (BoardView) board.getView();
					frame.setSize(view.getWindowSizeX(),view.getWindowSizeY());
					frame.setContentPane(view);
				}

				// load the game Menu
				loadGameMenu();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.getContentPane().revalidate();
			}
		});
		// exits the current game and return to windows
		itmClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				System.exit(0);
			}
		});

		itmStatistics.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int steps = view.getStepsUsed();
				JOptionPane.showMessageDialog(null, "Schritte:" + steps
						+ "\nFortschritt:\n");
			}
		});

		itmBest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "Level: 10";
				JOptionPane.showMessageDialog(null, message);

			}
		});
		return aaaBar;
	}
}
