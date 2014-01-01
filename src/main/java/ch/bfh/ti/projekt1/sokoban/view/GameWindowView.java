package ch.bfh.ti.projekt1.sokoban.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import ch.bfh.ti.projekt1.sokoban.controller.AbstractController;
import ch.bfh.ti.projekt1.sokoban.controller.BoardController;
import ch.bfh.ti.projekt1.sokoban.core.LevelService;
import ch.bfh.ti.projekt1.sokoban.core.LevelServiceImpl;
import ch.bfh.ti.projekt1.sokoban.editor.SokobanEditor;
import ch.bfh.ti.projekt1.sokoban.model.Level;

/**
 * @author marcoberger
 * @since 24/10/13 14:29
 */
public class GameWindowView implements AbstractView {

	private LevelService levelService = new LevelServiceImpl();
	private String levelName;
	private Level levels;
	private int currentStoryLevel;
	private String currentLevel;
	private String basePath;
	private String player;
	private JFrame frame;

	private BoardView view;
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
	public GameWindowView() {
		levels = new Level();
		basePath = "src/test/resources/ch/bfh/ti/projekt1/sokoban/";
		frame = new JFrame("Sokoban");
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		menuFile = new JMenu("Game");
		menuLevelEditor = new JMenu("Leveleditor");
		menuBar.add(menuFile);
		menuBar.add(menuLevelEditor);

		menuFileNew = new JMenuItem("Neues Spiel");
		menuFileLoad = new JMenuItem("Lade Spiel");
		menuFile.add(menuFileNew);
		menuFile.add(menuFileLoad);

		menuStartLeveleditor = new JMenuItem("Starte Leveleditor");
		menuLevelEditor.add(menuStartLeveleditor);

		// what happens when the user clicks on start new game
		menuFileNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startNewGame();
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
					levelName = file.getName();
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
					board.addView(GameWindowView.this);

					view = (BoardView) board.getView(BoardView.class);

					frame.setSize(view.getWindowSizeX(), view.getWindowSizeY());
					revalidateLevel(frame);
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

	public void startNewGame() {

		PlayerName playerName = new PlayerName();
		player = playerName.showDimensionDialog(frame);

		// create profile directory
		createPlayerProfile(player);

		if (player != null) {

			// Initialize the level
			currentStoryLevel = 0;
			currentLevel = levels.getLevel(currentStoryLevel);
			levelName = levels.getLevelOnly(1);

			// And load it
			BoardController board = levelService.getLevel(new File(levels
					.getLevel(currentStoryLevel)));

			board.addView(GameWindowView.this);

			view = (BoardView) board.getView(BoardView.class);

			frame.setSize(view.getWindowSizeX(), view.getWindowSizeY());
			revalidateLevel(frame);
		}
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
		JMenuItem itmViewSolution = new JMenuItem("Lösung ansehen");
		JMenuItem itmClose = new JMenuItem("Spiel beenden");

		// optionen
		JMenu menuEdit = new JMenu("Optionen");
		JMenuItem itmStatistics = new JMenuItem("Levelstatistik anzeigen");
		JMenuItem itmBest = new JMenuItem("Bestergebnisse anzeigen");
		JMenuItem itmExportSol = new JMenuItem(
				"Exportiere beste Lösung dieses Levels");

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

		menuFile.add(itmViewSolution);
		menuFile.addSeparator();

		menuFile.add(itmClose);

		// optionen
		menuEdit.add(itmStatistics);
		menuEdit.addSeparator();
		menuEdit.add(itmBest);
		menuEdit.add(itmExportSol);

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
				startNewGame();
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
							currentLevel));
					board.addView(GameWindowView.this);

					view = (BoardView) board.getView(BoardView.class);
					revalidateLevel(frame);

				}
			}
		});
		// what happens when the user clicks on save progress
		itmSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// String that gets written in file
				String fileContent = player + ":"
						+ levels.getLevelHash(currentStoryLevel);
				JFileChooser chooser = new JFileChooser();
				int retrival = chooser.showSaveDialog(null);
				if (retrival == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fw = new FileWriter(basePath + player
								+ "/Spielstaende/" + player + ".sok");
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

					JFileChooser jFileChooser = new JFileChooser(
							basePath+ "/generated");

					jFileChooser.showOpenDialog(null);
					levelName = jFileChooser.getSelectedFile().toString();
					BoardController board = levelService.getLevel(jFileChooser
							.getSelectedFile());
					board.addView(GameWindowView.this);

					view = (BoardView) board.getView(BoardView.class);
					frame.setSize(view.getWindowSizeX(), view.getWindowSizeY());
					frame.setContentPane(view);
				}

				// load the game Menu
				loadGameMenu();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.getContentPane().revalidate();
			}
		});

		// View the solution from a file
		itmViewSolution.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Load the file
				// Create a file chooser
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter sokfilter = new FileNameExtensionFilter(
						"Sokoban solution files (*.sol)", "sol");
				// set the filter to only allow sol files
				fc.setFileFilter(sokfilter);
				fc.setDialogTitle("Open schedule file");
				// set selected filter
				fc.setFileFilter(sokfilter);
				// Handle open button action.
				int returnVal = fc.showOpenDialog(frame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					levelName = file.getName().substring(0,file.getName().length()-4);
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
					
					// Load it
					BoardController board = levelService.getLevel(new File(basePath+"generated/"+levelName+".xml"));
					BoardView solBoardView = (BoardView) board.getView(BoardView.class);
					SolutionView solView = new SolutionView(board, content[1]);
					JFrame soVi = solView.getFrame();
					soVi.setSize(solBoardView.getWindowSizeX()+80, solBoardView.getWindowSizeY());
				
					soVi.add(solBoardView);
				} else {
					// show that the file was not applicable in this case
				}
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
				String message = "Level: 10";// TODO: Load the level statistics
				JOptionPane.showMessageDialog(null, message);

			}
		});
		// Exports the best solution from this level - only works when
		itmExportSol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportSolution();
			}
		});
		return gameMenuBar;
	}

	public void exportSolution() {

		int steps = view.getStepsUsed();
		File file = new File(basePath + player + "/Solutions/" + levelName
				+ ".sol");

		// Create the file, if it doesn't already exist
		try {
			if (file.createNewFile()) {
				try {
					FileWriter fw = new FileWriter(file);
					fw.write("9999999:dummy");
					fw.close();
				} catch (Exception ex2) {
					ex2.printStackTrace();
				}
			}
		} catch (Exception ex) {
		}

		String fileContent = new String();
		try {
			fileContent = new String(Files.readAllBytes(Paths.get(file
					.toString())));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println(fileContent);
		String[] content = fileContent.split(":");

		// Check if the current solution is better than the existing.
		if (Integer.parseInt(content[0]) > steps) {
			String playerPath = view.board.getPlayerPath();
			String newFileContent = steps + ":" + playerPath;

			try {
				FileWriter fw = new FileWriter(file);
				fw.write(newFileContent);
				fw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Gets called when the model has changed, used to validate if the level is
	 * finished
	 * 
	 * @param evt
	 */
	public void modelPropertyChange(final PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(
				(AbstractController.PROPERTY_LEVEL_STATUS))) {
			if ((boolean) evt.getNewValue() == true) {
				exportSolution();
				loadNextLevel();
			}
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
		profile.mkdir();
		File spielstaende = new File(basePath + profileName + "/Spielstaende");
		spielstaende.mkdir();
		File highscore = new File(basePath + profileName + "/Highscore");
		highscore.mkdir();
		File solutions = new File(basePath + profileName + "/Solutions");
		solutions.mkdir();
	}

	private void loadNextLevel() {
		currentStoryLevel++;
		levelName = levels.getLevel(currentStoryLevel);
		BoardController board = levelService.getLevel(new File(levelName));
		board.addView(GameWindowView.this);

		view = (BoardView) board.getView(BoardView.class);
		frame.setSize(view.getWindowSizeX(), view.getWindowSizeY());
		revalidateLevel(frame);
	}

	/**
	 * Used for standard opeartions on the frame
	 * 
	 * @param frame
	 */
	public void revalidateLevel(JFrame frame) {
		frame.setLocationRelativeTo(null);
		frame.setContentPane(view);
		// load the game Menu
		loadGameMenu();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		view.requestFocusInWindow();
		frame.getContentPane().revalidate();
	}
}
