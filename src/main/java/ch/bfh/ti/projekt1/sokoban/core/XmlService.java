package ch.bfh.ti.projekt1.sokoban.core;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.model.Field;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.xml.Column;
import ch.bfh.ti.projekt1.sokoban.xml.Level;
import ch.bfh.ti.projekt1.sokoban.xml.Row;
import ch.bfh.ti.projekt1.sokoban.xml.StartPosition;

/**
 * Provides functions to save and load level definitions from a xml file
 * 
 * @author svennyffenegger
 * @since 04/11/13 19:22
 */
public class XmlService {

	private static final Logger LOG = Logger.getLogger(XmlService.class);

	private static XmlService instance;

	private XmlService() {

	}

	/**
	 * Accessor of singleton
	 * 
	 * @return Singleton instance of this class
	 */
	public static synchronized XmlService getInstance() {
		if (instance == null) {
			instance = new XmlService();
		}
		return instance;
	}

	/**
	 * Loads and parses the file into an instance of Level
	 * 
	 * @param path
	 *            of the file
	 * @return Level instance
	 * @throws LevelMisconfigurationException
	 */
	public Level getLevelFromFile(File path)
			throws LevelMisconfigurationException {
		// if the file doesn't exist or is a folder instead
		if (path.exists() == false || path.isFile() == false) {
			LOG.error("The requested file doesn't exist: " + path);
			throw new LevelMisconfigurationException(
					"Fehler: Das angeforderte File existiert nicht oder ist keine Datei.");
		}

		/**
		 * try loading the xml file and marshal it (into a Level instance)
		 */
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Level level = (Level) jaxbUnmarshaller.unmarshal(path);
			LOG.debug("Successfully read level from Xml:" + path.getName());

			return level;
		} catch (JAXBException e) {
			LOG.error(e);
			throw new LevelMisconfigurationException(
					"Fehler: Der Inhalt des angeforderten Files ist nicht korrekt!");
		}

	}

	/**
	 * Finds the greatest column count inside the list
	 * 
	 * @param list
	 * @return count of greatest column
	 */
	public int getMaxColumnCount(List<Row> list) {
		int count = 0;
		for (Row rowType : list) {
			if (rowType.getColumn().size() > count) {
				count = rowType.getColumn().size();
			}
		}
		return count;
	}

	/**
	 * Saves the level (the board instance) into the folder
	 * 
	 * @param board
	 *            with the state to save
	 * @param parentFolder
	 *            where to put the file
	 * @throws LevelMisconfigurationException
	 */
	public void saveLevel(Board board, File parentFolder)
			throws LevelMisconfigurationException {
		Level level = new Level();

		Field[][] grid = board.getGrid();

		// generate the uuid
		if (board.getUuid() == null || board.getUuid().isEmpty()) {
			board.setUuid(UUID.randomUUID().toString());
		}

		level.setUuid(board.getUuid());
		level.setName(board.getLevelName());

		// set the moves (only if the board is from the editor)
		if (board.getDiamondMoveCounter() > 0) {
			level.setMoves(board.getDiamondMoveCounter());
		}

		/**
		 * evaluate the board constellation
		 */
		int countPlayer = 0;
		int countDiamonds = 0;
		int countGoals = 0;

		for (int i = 0; i < grid[0].length; i++) {
			Row row = new Row();
			row.setId(i);
			level.getRow().add(row);

			for (int j = 0; j < grid.length; j++) {
				Column column = new Column();
				column.setId(j);
				column.setType(FieldState.convertToXMLFieldType(grid[j][i]
						.getState()));
				row.getColumn().add(column);
				switch (grid[j][i].getState()) {
				case PLAYER:
					StartPosition startPosition = new StartPosition();
					startPosition.setColumn(j);
					startPosition.setRow(i);
					level.setStartPosition(startPosition);
					countPlayer++;

					break;
				case DIAMOND:
					countDiamonds++;
					break;
				case GOAL:
					countGoals++;
					break;
				default:
					break;
				}
			}
		}

		if (countDiamonds == 0 || countGoals == 0) {
			LOG.error("You have to provide at least one diamond and goal!");
			throw new LevelMisconfigurationException(
					"Fehler: Es muss mindestens ein Diamant und ein Ziel vorhanden sein!");
		}

		if (countDiamonds != countGoals) {
			LOG.error("You have to provide an equal number of diamonds and goals!");
			throw new LevelMisconfigurationException(
					"Fehler: Die Anzahl Diamanten entspricht nicht der Anzahl Ziele!");
		}

		if (countPlayer > 1) {
			LOG.error("More than one start position defined!");
			throw new LevelMisconfigurationException(
					"Fehler: Mehr als eine Startposition gefunden!");
		}

		if (level.getStartPosition() == null) {
			LOG.error("No Startposition defined!");
			throw new LevelMisconfigurationException(
					"Fehler: Es ist keine Startposition definiert!");
		}

		/**
		 * Save the Level instance to an Xml File
		 */
		File file = new File(parentFolder, level.getName() + ".xml");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // format
																				// the
																				// output
																				// file

			jaxbMarshaller.marshal(level, file);
		} catch (JAXBException e) {
			LOG.error(e);
			throw new LevelMisconfigurationException(
					"Fehler: Das speichern des XML ist fehlgeschlagen. Bitte prüfen sie ihre Konfiguration oder das Fehler-Log.");
		}
	}

}
