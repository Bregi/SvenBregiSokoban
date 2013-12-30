package ch.bfh.ti.projekt1.sokoban.core;

import ch.bfh.ti.projekt1.sokoban.controller.BoardController;
import ch.bfh.ti.projekt1.sokoban.model.*;
import ch.bfh.ti.projekt1.sokoban.view.BoardView;
import ch.bfh.ti.projekt1.sokoban.xml.Column;
import ch.bfh.ti.projekt1.sokoban.xml.Level;
import ch.bfh.ti.projekt1.sokoban.xml.Row;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author svennyffenegger
 * @since 04/11/13 21:16
 */
public class LevelServiceImpl implements LevelService {

	private XmlService xmlService = new XmlServiceImpl();

	@Override
	public BoardController getLevel(File file) {
		Level level = xmlService.getLevelFromFile(file);

		Position startPos = new Position(level.getStartPosition().getColumn(),
				level.getStartPosition().getRow());

		Board board = new Board(xmlService.getMaxColumnCount(level.getRow()),
				level.getRow().size(), startPos);

		BoardController boardController = new BoardController();

		for (Row rowType : level.getRow()) {
			for (Column columnType : rowType.getColumn()) {

				Field field = new Field(FieldState.parseXmlFieldType(columnType
						.getType()));
				board.setField(columnType.getId(), rowType.getId(), field);
			}
		}
		board.setLevelName(level.getName());
		board.setUuid(level.getUuid());
		BoardView boardView = new BoardView(board, boardController,
				board.getPosition(), level.getName());

		boardController.setModel(board);
		boardController.setView(boardView);

		return boardController;
	}

	@Override
	public void saveLevelMoves(List<Direction> directionList, String levelName) {
		String homeFolder = CoreConstants.getProperty("game.homefolder");
		File f = new File(homeFolder + levelName);
		for (Direction direction : directionList) {

		}
	}

	@Override
	public Map<String, String> getLevelNameUUIDMap() {
		Map<String, String> uuidNameMap = new HashMap<>();
		List<String> filesNames = fileList(CoreConstants.getProperty("editor.basepath"));
		for (String file : filesNames) {
			Level level = xmlService.getLevelFromPath(file);
			uuidNameMap.put(level.getUuid(), level.getName());
		}
		return uuidNameMap;
	}

	private List<String> fileList(String directory) {
		List<String> fileNames = new ArrayList<>();
		try (DirectoryStream<Path> directoryStream = Files
				.newDirectoryStream(Paths.get(directory))) {
			for (Path path : directoryStream) {
				fileNames.add(path.toString());
			}
		} catch (IOException ex) {
		}
		return fileNames;
	}

}
