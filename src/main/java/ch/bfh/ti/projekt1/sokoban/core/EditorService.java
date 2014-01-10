package ch.bfh.ti.projekt1.sokoban.core;

import java.io.File;
import java.util.List;

import org.apache.log4j.xml.Log4jEntityResolver;

import ch.bfh.ti.projekt1.sokoban.controller.FieldController;
import ch.bfh.ti.projekt1.sokoban.core.dijkstra.Dijkstra;
import ch.bfh.ti.projekt1.sokoban.core.dijkstra.Dijkstra.Mode;
import ch.bfh.ti.projekt1.sokoban.core.dijkstra.Vertex;
import ch.bfh.ti.projekt1.sokoban.editor.DraggableElementDestination;
import ch.bfh.ti.projekt1.sokoban.editor.EditorController;
import ch.bfh.ti.projekt1.sokoban.editor.LevelEditorView;
import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.model.Field;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.model.Position;
import ch.bfh.ti.projekt1.sokoban.xml.Column;
import ch.bfh.ti.projekt1.sokoban.xml.Level;
import ch.bfh.ti.projekt1.sokoban.xml.Row;

/**
 * Provides basic functionalities for the editor. Is implemented as singleton.
 * 
 * @author svennyffenegger
 * @since 31/10/13 20:41
 */
public class EditorService {
	private static final Logger LOG = Logger.getLogger(EditorService.class);
	private static EditorService instance;

	private EditorService() {

	}

	/**
	 * Accessor to the instance of this class
	 * 
	 * @return singleton instance of this class
	 */
	public static synchronized EditorService getInstance() {
		if (instance == null) {
			instance = new EditorService();
		}
		return instance;
	}

	/**
	 * creates an empty board with the specified dimensions
	 * 
	 * @param columns
	 * @param rows
	 * @return the controller with a model and view attached
	 */
	public EditorController getNewLevel(int columns, int rows) {
		EditorController controller = new EditorController();
		Board currentLevel = new Board(columns, rows);

		controller.setModel(currentLevel);

		LevelEditorView editorView = new LevelEditorView(controller, columns,
				rows);
		controller.setView(editorView);

		// initialize fields with the empty state
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Field field = new Field(FieldState.EMPTY);
				currentLevel.setField(j, i, field);

				// we need the controller for each field for the drag and drop
				// functionality
				FieldController fieldController = new FieldController();
				DraggableElementDestination elementDestination = new DraggableElementDestination(
						fieldController);

				fieldController.setView(elementDestination);
				fieldController.setModel(field);

				editorView.addElement(elementDestination);
			}
		}
		return controller;
	}

	/**
	 * tries to save the game board of the editor.
	 * 
	 * @param board
	 * @throws LevelMisconfigurationException
	 *             if something is not valid in the created level
	 */
	public void saveLevel(Board board) throws LevelMisconfigurationException {
		// the parent folder where the file is created inside
		File parentFolder = new File(
				CoreConstants.getProperty("editor.basepath"));

		/**
		 * start to validate level configuration
		 */

		// list with positions of all diamonds
		List<Position> diamondPosList = board
				.getPositionsOfType(FieldState.DIAMOND);

		// list with positions of all goals
		List<Position> goalPosList = board.getPositionsOfType(FieldState.GOAL);

		// initialize dijkstra algorithm for this board
		Dijkstra dijkstra = new Dijkstra(board.getGrid(), Mode.EDITOR);

		/**
		 * check if all diamonds have a connection to any goal
		 */
		boolean diamondToGoals = checkConnections(dijkstra, diamondPosList,
				goalPosList);

		/**
		 * check if all goals have a connection to any diamond
		 */
		boolean goalToDiamonds = checkConnections(dijkstra, goalPosList,
				diamondPosList);

		if (!diamondToGoals) {
			LOG.error("Error in saving level: diamond without connection to goal!");
			throw new LevelMisconfigurationException(
					"Fehler: Es gibt einen Diamanten ohne Verbindung zu einem Ziel!");
		}

		if (!goalToDiamonds) {
			LOG.error("Error in saving level: goal without connection to diamond!");
			throw new LevelMisconfigurationException(
					"Fehler: Es gibt ein Ziel ohne Verbindung zu einem Diamanten!");
		}

		/**
		 * prevent to save a modified level with the same uuid again when a
		 * level is changed in the editor, then it needs a new uuid
		 */
		board.setUuid(null);

		/**
		 * now save the level to xml, everything should be valid
		 */
		XmlService.getInstance().saveLevel(board, parentFolder);
	}

	/**
	 * Checks if there is a connection for all base elements to any goals
	 * element using the provided dijkstra object
	 * 
	 * @param dijkstra
	 * @param base
	 *            list with initial positions
	 * @param goals
	 *            list with destination positions
	 * @return true if all elements in the base list have a connection to at
	 *         least one position in the goals list.
	 */
	private boolean checkConnections(Dijkstra dijkstra, List<Position> base,
			List<Position> goals) {
		for (Position diamondPos : base) {
			boolean foundWay = false;
			for (Position goalPos : goals) {
				List<Vertex> path = dijkstra.getPath(diamondPos, goalPos);
				// needs to be greater than 1
				if (path.size() > 1) {
					foundWay = true;
					break;
				}
			}

			/**
			 * if we have one element with no connections at all, we can return
			 * false
			 */
			if (foundWay == false) {
				return false;
			}
		}
		/**
		 * when we reach this point, we have found a connection for all
		 * positions
		 */
		return true;
	}

	/**
	 * Loads a level for the editor
	 * 
	 * @param file
	 *            to load the definition from
	 * @return a EditorController with initialized model and view
	 * @throws LevelMisconfigurationException 
	 */
	public EditorController getLevel(File file) throws LevelMisconfigurationException {
		// loads just the level itself from the xml
		Level level = XmlService.getInstance().getLevelFromFile(file);
		EditorController controller = new EditorController();
		int col = XmlService.getInstance().getMaxColumnCount(level.getRow());
		int row = level.getRow().size();

		Board board = new Board(col, row);

		LevelEditorView editorView = new LevelEditorView(controller, col, row);
		controller.setView(editorView);
		controller.setModel(board);

		/**
		 * initialize all fields with the correct field type
		 */
		for (Row rowType : level.getRow()) {
			for (Column columnType : rowType.getColumn()) {
				Field field = new Field(FieldState.parseXmlFieldType(columnType
						.getType()));

				board.setField(columnType.getId(), rowType.getId(), field);

				/**
				 * Controller fields for drag and drop
				 */
				FieldController fieldController = new FieldController();
				DraggableElementDestination elementDestination = new DraggableElementDestination(
						fieldController, field.getState());

				fieldController.setView(elementDestination);
				fieldController.setModel(field);

				editorView.addElement(elementDestination);
			}
		}

		board.setLevelName(level.getName());
		board.setUuid(level.getUuid());
		return controller;
	}

}
