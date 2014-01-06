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
 * @author svennyffenegger
 * @since 04/11/13 19:22
 */
public class XmlService {

	private static final Logger LOG = Logger.getLogger(XmlService.class);

	private static XmlService instance;

	private XmlService() {

	}

	public static XmlService getInstance() {
		if (instance == null) {
			instance = new XmlService();
		}
		return instance;
	}

	public Level getLevelFromFile(File path) {
		if (path.exists()) {
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext
						.createUnmarshaller();
				Level level = (Level) jaxbUnmarshaller.unmarshal(path);
				LOG.debug("Successfully read level from Xml:" + path.getName());

				return level;
			} catch (JAXBException e) {
				LOG.error(e);
				return null;
			}
		} else {
			LOG.error("The requested file doesn't exist: " + path);
			return null;
		}
	}

	public Level getLevelFromPath(String path) {
		return getLevelFromFile(new File(path));
	}

	public Level changeLevelDimension(Level existingLevel, int columns, int rows) {
		return null;
	}

	public int getMaxColumnCount(List<Row> list) {
		int count = 0;
		for (Row rowType : list) {
			if (rowType.getColumn().size() > count) {
				count = rowType.getColumn().size();
			}
		}
		return count;
	}

	public void saveLevel(Board board, File parentFolder)
			throws LevelMisconfigurationException {
		Level level = new Level();

		Field[][] grid = board.getGrid();

		if (board.getUuid() == null || board.getUuid().isEmpty()) {
			board.setUuid(UUID.randomUUID().toString());
		}

		level.setUuid(board.getUuid());
		level.setName(board.getLevelName());

		if (board.getDiamondMoveCounter() > 0) {
			level.setMoves(board.getDiamondMoveCounter());
		}

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
				}
			}
		}

		if (countDiamonds == 0 || countGoals == 0) {
			throw new LevelMisconfigurationException(
					"You have to provide at least one diamond and goal!");
		}

		if (countDiamonds != countGoals) {
			throw new LevelMisconfigurationException(
					"You have to provide an equal number of diamonds and goals!");
		}

		if (countPlayer > 1) {
			throw new LevelMisconfigurationException(
					"More than one start position defined!");
		}

		if (level.getStartPosition() == null) {
			throw new LevelMisconfigurationException(
					"No Startposition defined!");
		}

		File file = new File(parentFolder, level.getName() + ".xml");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(level, file);
		} catch (JAXBException e) {
			LOG.error(e);
		}
	}

}
