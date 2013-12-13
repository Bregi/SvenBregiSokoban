package ch.bfh.ti.projekt1.sokoban.core;

import ch.bfh.ti.projekt1.sokoban.controller.BoardController;
import ch.bfh.ti.projekt1.sokoban.model.*;
import ch.bfh.ti.projekt1.sokoban.view.BoardView;
import ch.bfh.ti.projekt1.sokoban.xml.Column;
import ch.bfh.ti.projekt1.sokoban.xml.Level;
import ch.bfh.ti.projekt1.sokoban.xml.Row;

import java.io.File;
import java.util.List;

/**
 * @author svennyffenegger
 * @since 04/11/13 21:16
 */
public class LevelServiceImpl implements LevelService {

    private XmlService xmlService = new XmlServiceImpl();

    @Override
    public BoardController getLevel(File file) {
        Level level = xmlService.getLevelFromFile(file);

        Position startPos = new Position(level.getStartPosition().getColumn(), level.getStartPosition().getRow());

        Board board = new Board(xmlService.getMaxColumnCount(level.getRow()), level.getRow().size(), startPos);

        BoardController boardControl§ler = new BoardController();

        for (Row rowType : level.getRow()) {
            for (Column columnType : rowType.getColumn()) {

                Field field = new Field(FieldState.parseXmlFieldType(columnType.getType()));
                board.setField(columnType.getId(), rowType.getId(), field);
            }
        }
        board.setLevelName(level.getName());
        BoardView boardView = new BoardView(board, boardController, board.getPosition(), level.getName());

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
}
