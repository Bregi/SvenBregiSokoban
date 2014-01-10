package ch.bfh.ti.projekt1.sokoban.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ch.bfh.ti.projekt1.sokoban.controller.BoardController;
import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.model.Direction;
import ch.bfh.ti.projekt1.sokoban.model.Field;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.model.Position;
import ch.bfh.ti.projekt1.sokoban.view.BoardView;
import ch.bfh.ti.projekt1.sokoban.view.BoardView.Mode;
import ch.bfh.ti.projekt1.sokoban.view.GameWindowView;
import ch.bfh.ti.projekt1.sokoban.xml.Column;
import ch.bfh.ti.projekt1.sokoban.xml.Level;
import ch.bfh.ti.projekt1.sokoban.xml.Row;

/**
 * @author svennyffenegger
 * @since 04/11/13 21:16
 */
public class LevelService {

	private static LevelService instance;
	private static final Logger LOG = Logger.getLogger(GameWindowView.class);

	private LevelService() {
	}

	public static LevelService getInstance() {
		if (instance == null) {
			instance = new LevelService();
		}
		return instance;
	}

	public BoardController getLevel(File file) throws FileNotFoundException
	{
		Level level = XmlService.getInstance().getLevelFromFile(file);
		
		if(level!=null){
		Position startPos = new Position(level.getStartPosition().getColumn(),
				level.getStartPosition().getRow());

		Board board = new Board(XmlService.getInstance().getMaxColumnCount(level.getRow()),
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
		if (level.getMoves() != null) {
			board.setDiamondMoveCounter(level.getMoves());
		}
		BoardView boardView = new BoardView(board, boardController,
				board.getPosition(), level.getName(), Mode.WALKABLE);

		boardController.setModel(board);
		boardController.setView(boardView);

		return boardController;
		}else{
			throw new FileNotFoundException();
		}
	}

	public void saveLevelMoves(List<Direction> directionList, String levelName) {
		String homeFolder = CoreConstants.getProperty("game.homefolder");
		File f = new File(homeFolder + levelName);
		for (Direction direction : directionList) {

		}
	}

	public Map<String, String> getLevelNameUUIDMap() {
		Map<String, String> uuidNameMap = new HashMap<>();
		List<String> filesNames = fileList(CoreConstants
				.getProperty("game.levelspath"));
		for (String file : filesNames) {
			Level level = XmlService.getInstance().getLevelFromPath(file);
			uuidNameMap.put(level.getUuid(), level.getName());
		}
		return uuidNameMap;
	}

	private List<String> fileList(String directory) {
		List<String> fileNames = new ArrayList<>();
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(
				Paths.get(directory), "*.xml")) {
			for (Path path : directoryStream) {
				fileNames.add(path.toString());
			}
		} catch (IOException ex) {
		}
		return fileNames;
	}


	public void saveLevelProgress(Board board, String player)
			throws LevelMisconfigurationException {
		File parentFolder = new File(CoreConstants.getProperty("game.basepath")
				+ player + "/"
				+ CoreConstants.getProperty("game.folder.progress"));
		XmlService.getInstance().saveLevel(board, parentFolder);
	}

	public List<String> getProfiles() {
		List<String> dirList = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(
				Paths.get(CoreConstants.getProperty("game.basepath")),
				new DirectoryStream.Filter<Path>() {

					@Override
					public boolean accept(Path entry) throws IOException {
						return Files.isDirectory(entry);
					}
				})) {
			for (Path entry : stream) {
				dirList.add(entry.getFileName().toString());
			}
		} catch (IOException e) {
		}

		return dirList;
	}
	
	public BoardController getLevelProgressForUser(String user, String levelName) {
		Level level = XmlService.getInstance().getLevelFromPath(levelName);
		String path = CoreConstants.getProperty("game.basepath") + user + "/" + CoreConstants.getProperty("game.folder.progress");

		for (String fileStr : fileList(path)) {
			Level progress = XmlService.getInstance().getLevelFromPath(fileStr);
			if (progress.getUuid().equals(level.getUuid())) {
				try{
				return getLevel(new File(fileStr));
				}catch(FileNotFoundException e){
					LOG.error(e);
				}
			}
		}
		
		return null;
	}

}
