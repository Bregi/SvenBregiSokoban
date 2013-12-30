package ch.bfh.ti.projekt1.sokoban.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

import ch.bfh.ti.projekt1.sokoban.controller.AbstractController;
import ch.bfh.ti.projekt1.sokoban.controller.BoardController;
import ch.bfh.ti.projekt1.sokoban.core.CoreConstants;
import ch.bfh.ti.projekt1.sokoban.core.Highscore;
import ch.bfh.ti.projekt1.sokoban.core.LevelService;
import ch.bfh.ti.projekt1.sokoban.core.LevelServiceImpl;
import ch.bfh.ti.projekt1.sokoban.core.XmlServiceImpl;
import ch.bfh.ti.projekt1.sokoban.editor.SokobanEditor;
import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.model.Level;

/**
 * @author marcoberger
 * @since 24/10/13 14:29
 */
public class StartScreen implements AbstractView {
	private static final Logger LOG = Logger.getLogger(StartScreen.class);

	private LevelService levelService = new LevelServiceImpl();
	private String levelName;
	private Level levels;
	private int currentStoryLevel;
	private String currentLevel;
	private String basePath;
	private String player;
	private final String FOLDER_HIGHSCORE = CoreConstants
			.getProperty("game.folder.highscore");
	private final String FOLDER_PROGRESS = CoreConstants
			.getProperty("game.folder.progress");
	private final String FOLDER_SOLUTIONS = CoreConstants
			.getProperty("game.folder.solutions");
	private JFrame frame;

	private BoardView view;
	private Board model;
	private JMenuBar menuBar;
	private JMenuBar gameMenuBar;

	private JMenu menuFile;
	private JMenu menuLevelEditor;

	private JMenuItem menuFileNew;
	private JMenuItem menuFileLoad;
	private JMenuItem menuFileLoadLevel;
	private JMenuItem menuStartLeveleditor;

	/**
	 * Method used to initialize the game screen
	 * 
	 */
	public StartScreen() {
		levels = new Level();
		basePath = CoreConstants.getProperty("game.basepath");
		frame = new JFrame(CoreConstants.getProperty("game.title"));
		frame.setSize(
				new Integer(CoreConstants.getProperty("game.window.width")),
				new Integer(CoreConstants.getProperty("game.window.height")));
		frame.setLocationRelativeTo(null);
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		menuFile = new JMenu("Game");
		menuLevelEditor = new JMenu("Leveleditor");
		menuBar.add(menuFile);
		menuBar.add(menuLevelEditor);

		menuFileNew = new JMenuItem("Neues Spiel");
		menuFileLoad = new JMenuItem("Lade Spiel");
		menuFileLoadLevel = new JMenuItem("Lade Level");
		menuFile.add(menuFileNew);
		menuFile.add(menuFileLoad);
		menuFile.add(menuFileLoadLevel);

		menuStartLeveleditor = new JMenuItem("Starte Leveleditor");
		menuLevelEditor.add(menuStartLeveleditor);

		// what happens when the user clicks on start new game
		menuFileNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				PlayerName playerName = new PlayerName();
				player = playerName.showDimensionDialog(frame);

				// create profile directory
				createPlayerProfile(player);

				if (player != null) {
					currentStoryLevel = 0;
					currentLevel = levels.getLevel(currentStoryLevel);

					BoardController board = levelService.getLevel(new File(
							levels.getLevel(currentStoryLevel)));
					board.addView(StartScreen.this);

					model = (Board) board.getModel(Board.class);
					view = (BoardView) board.getView(BoardView.class);
					frame.setSize(view.getWindowSizeX(), view.getWindowSizeY());
					frame.setLocationRelativeTo(null);
					frame.setContentPane(view);
					// load the game Menu
					loadGameMenu();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					view.requestFocusInWindow();
					frame.getContentPane().revalidate();

				}
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
				// set the filter to only allow sok files
				fc.setFileFilter(sokfilter);
				fc.setDialogTitle("Open schedule file");
				// set selected filter
				fc.setFileFilter(sokfilter);
				// Handle open button action.
				int returnVal = fc.showOpenDialog(frame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					// Get content from file
					String fileContent = new String();
					try {
						fileContent = new String(Files.readAllBytes(Paths
								.get(file.toString())));
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					System.out.println(fileContent);
					String[] content = fileContent.split(":");
					player = content[0];
					currentStoryLevel = levels.getLevelByHash(content[1]);

					currentLevel = levels.getLevel(currentStoryLevel);
					// TODO: (also validate)
					BoardController board = levelService.getLevel(new File(
							levels.getLevel(currentStoryLevel)));
					board.addView(StartScreen.this);

					view = (BoardView) board.getView(BoardView.class);
					model = (Board) board.getModel();
					frame.setSize(view.getWindowSizeX(), view.getWindowSizeY());
					frame.setLocationRelativeTo(null);
					frame.setContentPane(view);
					// load the game Menu
					loadGameMenu();
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
					board.addView(StartScreen.this);

					view = (BoardView) board.getView(BoardView.class);
					model = (Board) board.getModel();
					frame.setSize(view.getWindowSizeX(), view.getWindowSizeY());
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

		// Start the level editor
		menuStartLeveleditor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SokobanEditor editor = new SokobanEditor();
			}
		});
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public void loadGameMenu() {

		gameMenuBar = getGameMenuBar();
		this.frame.setJMenuBar(gameMenuBar);

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
		gameMenuBar = new JMenuBar();

		gameMenuBar.add(menuFile);
		gameMenuBar.add(menuEdit);
		gameMenuBar.add(menuLevelEditor);

		// what happens when the user clicks on start new game
		itmNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// LevelDimensionDialog.showDimensionDialog(frame);
				currentStoryLevel = 0;
				// TODO unschön
				BoardController board = levelService.getLevel(new File(levels
						.getLevel(currentStoryLevel)));
				board.addView(StartScreen.this);

				view = (BoardView) board.getView(BoardView.class);
				model = (Board) board.getModel();
				frame.setSize(view.getWindowSizeX(), view.getWindowSizeY());
				frame.setLocationRelativeTo(null);

				frame.setContentPane(view);

				// load the game Menu
				loadGameMenu();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.getContentPane().revalidate();
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
					BoardController board = levelService.getLevel(new File(
							levelName));
					board.addView(StartScreen.this);

					view = (BoardView) board.getView();
					model = (Board)board.getModel();
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
				// String that gets written in file
				System.out.println("TEST");
				String fileContent = player + ":"
						+ levels.getLevelHash(currentStoryLevel);
				JFileChooser chooser = new JFileChooser();
				int retrival = chooser.showSaveDialog(null);
				if (retrival == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fw = new FileWriter(basePath + player + "/"
								+ FOLDER_PROGRESS + "/" + player + ".sok");
						fw.write(fileContent);
						fw.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		// Start the level editor
		itmLevelEditorStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SokobanEditor editor = new SokobanEditor();
			}
		});
		// what happens when the user clicks on load a level
		itmLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Object[] options = { "Ja", "Nein" };
				int response = JOptionPane
						.showOptionDialog(
								frame,
								"Bist du sicher, dass du ein neues Spiel starten willst? Ungespeicherter Fortschritt geht dabei verloren. ",
								"Level neu starten", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[1]);

				if (response == JOptionPane.YES_OPTION) {

					JFileChooser jFileChooser = new JFileChooser(CoreConstants
							.getProperty("editor.basepath"));

					jFileChooser.showOpenDialog(null);
					levelName = jFileChooser.getSelectedFile().toString();
					BoardController board = levelService.getLevel(jFileChooser
							.getSelectedFile());
					board.addView(StartScreen.this);

					view = (BoardView) board.getView(BoardView.class);
					model = (Board) board.getModel();
					frame.setSize(view.getWindowSizeX(), view.getWindowSizeY());
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
				JDialog dialog = new JDialog(frame);
				Map<String, Integer> levelScoreMap = Highscore.getInstance().getHighscoreForPlayer(player);
				Map<String, String> levelNameMap = levelService.getLevelNameUUIDMap();
				
				dialog.setContentPane(new HighscorePanel(levelNameMap, levelScoreMap));
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		return gameMenuBar;
	}

	/**
	 * Gets called when the model has changed
	 * 
	 * @param evt
	 */
	public void modelPropertyChange(final PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case AbstractController.PROPERTY_LEVEL_STATUS:
			if ((boolean) evt.getNewValue() == true) {
				loadNextLevel();
			}
			break;
		case AbstractController.PROPERTY_LEVEL_SCORE:
			Highscore.getInstance().addHighscore(player, model.getUuid(),
					(Integer) evt.getNewValue());
			break;
		}

	}

	/**
	 * Creates all the needed folders used for the game
	 * 
	 * @param profileName
	 */
	public void createPlayerProfile(String profileName) {
		// TODO try catch
		File profile = new File(basePath + profileName);
		profile.mkdirs();
		File spielstaende = new File(basePath + profileName + "/"
				+ FOLDER_PROGRESS);
		spielstaende.mkdir();
		File highscore = new File(basePath + profileName + "/"
				+ FOLDER_HIGHSCORE);
		highscore.mkdir();
		File highscoreFile = new File(highscore,
				CoreConstants.getProperty("game.file.highscore"));
		try {
			highscoreFile.createNewFile();
		} catch (IOException e) {
			LOG.error(e);
		}
		File solutions = new File(basePath + profileName + "/"
				+ FOLDER_SOLUTIONS);
		solutions.mkdir();

	}

	private void loadNextLevel() {
		currentStoryLevel++;
		levelName = levels.getLevel(currentStoryLevel);
		BoardController board = levelService.getLevel(new File(levelName));
		board.addView(StartScreen.this);

		view = (BoardView) board.getView(BoardView.class);
		model = (Board) board.getModel();
		frame.setSize(view.getWindowSizeX(), view.getWindowSizeY());
		frame.setLocationRelativeTo(null);

		frame.setContentPane(view);

		// load the game Menu
		loadGameMenu();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().revalidate();
	}
}
