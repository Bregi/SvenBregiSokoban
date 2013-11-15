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

/**
 * @author marcoberger
 * @since 24/10/13 14:29
 */
public class StartScreen {

	private LevelService levelService = new LevelServiceImpl();
	private String levelName;
	private JFrame frame;

	//public GameController gameController;

	private BoardView view;
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

				//GameController controller = new GameController();
				//gameController = controller;
				BoardController board = levelService
						.getLevel(new File("src/test/resources/ch/bfh/ti/projekt1/sokoban/level1.xml"));

				BoardView view = (BoardView) board.getView();
				frame.setContentPane(view);
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
					levelName = file.toString();
					// TODO: (also validate)
					BoardController board = levelService
							.getLevel(new File("src/test/resources/ch/bfh/ti/projekt1/sokoban/level1.xml"));

					BoardView view = (BoardView) board.getView();
				//	view = controller.loadLevel(file.toString());
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

	public void saveGameMenu() {

	}

	public void restartLevelMenu() {

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

		aaaBar.add(menuFile);
		aaaBar.add(menuEdit);
		aaaBar.add(menuLevelEditor);

		// what happens when the user clicks on start new game
		itmNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// LevelDimensionDialog.showDimensionDialog(frame);

				// TODO unschön
				BoardController board = levelService
						.getLevel(new File("src/test/resources/ch/bfh/ti/projekt1/sokoban/level1.xml"));

				BoardView view = (BoardView) board.getView();

				//view = controller.loadLevel("src/test/resources/ch/bfh/ti/projekt1/sokoban/level1.xml");

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
				// LevelDimensionDialog.showDimensionDialog(frame);

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
							.getLevel(new File("src/test/resources/ch/bfh/ti/projekt1/sokoban/level1.xml"));

					BoardView view = (BoardView) board.getView();
					
					//view = controller
					//		.loadLevel("src/test/resources/ch/bfh/ti/projekt1/sokoban/level1.xml");

					//gameController = controller;

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

				//GameController controller = gameController;
				//controller.saveLevelProgress(controller.getBoard());
			}
		});
		
		// start the level editor
		itmLevelEditorStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
	           SokobanEditor editor =  new SokobanEditor();
	           //frame.setContentPane(editor.);
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

					// TODO unschön
				//	GameController controller = new GameController();
					

					JFileChooser jFileChooser = new JFileChooser(
							"src/test/resources/ch/bfh/ti/projekt1/sokoban/generated");

					jFileChooser.showOpenDialog(null);
					BoardController board = levelService
							.getLevel(jFileChooser.getSelectedFile());

					BoardView view = (BoardView) board.getView();
				//	view = controller.loadLevel(jFileChooser
				//			.getSelectedFile());
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
		
		itmStatistics.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int steps = view.getStepsUsed();
				JOptionPane.showMessageDialog(null ,"Schritte:"+steps+"\nFortschritt:\n");
			}
		});
		
		itmBest.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "Level: 10";
				JOptionPane.showMessageDialog(null ,message);

			}
		});
		return aaaBar;
	}
}
