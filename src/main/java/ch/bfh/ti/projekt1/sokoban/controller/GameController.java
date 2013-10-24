package ch.bfh.ti.projekt1.sokoban.controller;

import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.model.Field;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.model.Position;
import ch.bfh.ti.projekt1.sokoban.view.BoardView;
import ch.bfh.ti.projekt1.sokoban.xml.ColumnType;
import ch.bfh.ti.projekt1.sokoban.xml.LevelType;
import ch.bfh.ti.projekt1.sokoban.xml.RowType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * @author svennyffenegger
 * @since 23.10.13 13:58
 */
public class GameController {
    public BoardView loadLevel(String path) {
        File file = new File(path);
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(LevelType.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            LevelType level = (LevelType) jaxbUnmarshaller.unmarshal(file);

            Position startPos = new Position(new Integer(level.getStartPosition().getColumn()), new Integer(level.getStartPosition().getRow()));

            Board board = new Board(getMaxColumnCount(level.getRow()), level.getRow().size(), startPos);

            BoardController boardController = new BoardController();
            BoardView boardView = new BoardView(boardController, board.getPosition(), level.getName());

            for (RowType rowType : level.getRow()) {
                for (ColumnType columnType : rowType.getColumn()) {

                    // TODO: beim schema integer definieren, nicht string
                    Field field = new Field(parseFieldState(columnType.getType()));
                    board.setField(new Integer(columnType.getId()), new Integer(rowType.getId()), field);
                }
            }
            board.setLevelName(level.getName());

            boardController.addModel(board);
            boardController.addView(boardView);


            return boardView;
        } catch (JAXBException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return null;
    }

    private int getMaxColumnCount(List<RowType> list) {
        int count = 0;
        for (RowType rowType : list) {
            if (rowType.getColumn().size() > count) {
                count = rowType.getColumn().size();
            }
        }
        return count;
    }

    private FieldState parseFieldState(String fieldState) {
        // TODO: beim laden des xml soll direkt enums erzeugt werden
        if (fieldState.equalsIgnoreCase(FieldState.DIAMOND.name())) {
            return FieldState.DIAMOND;
        } else if (fieldState.equalsIgnoreCase(FieldState.EMPTY.name())) {
            return FieldState.EMPTY;
        } else if (fieldState.equalsIgnoreCase(FieldState.COMPLETED.name())) {
            return FieldState.COMPLETED;
        } else if (fieldState.equalsIgnoreCase(FieldState.GOAL.name())) {
            return FieldState.GOAL;
        } else if (fieldState.equalsIgnoreCase(FieldState.PLAYER.name())) {
            return FieldState.PLAYER;
        } else if (fieldState.equalsIgnoreCase(FieldState.WALL.name())) {
            return FieldState.WALL;
        }
        return null;
    }
}
