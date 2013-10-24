package ch.bfh.ti.projekt1.sokoban.controller;

import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.model.Field;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.model.Position;
import ch.bfh.ti.projekt1.sokoban.view.BoardView;
import ch.bfh.ti.projekt1.sokoban.xml.Column;
import ch.bfh.ti.projekt1.sokoban.xml.Level;
import ch.bfh.ti.projekt1.sokoban.xml.Row;

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
            jaxbContext = JAXBContext.newInstance(Level.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Level level = (Level) jaxbUnmarshaller.unmarshal(file);
System.out.println(level.getStartPosition());
            Position startPos = new Position(level.getStartPosition().getColumn(), level.getStartPosition().getRow());

            Board board = new Board(getMaxColumnCount(level.getRow()), level.getRow().size(), startPos);

            BoardController boardController = new BoardController();
            BoardView boardView = new BoardView(boardController, board.getPosition(), level.getName());

            for (Row rowType : level.getRow()) {
                for (Column columnType : rowType.getColumn()) {

                    Field field = new Field(FieldState.parseXmlFieldType(columnType.getType()));
                    board.setField(columnType.getId(), rowType.getId(), field);
                }
            }
            board.setLevelName(level.getName());

            boardController.addModel(board);
            boardController.addView(boardView);


            return boardView;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    private int getMaxColumnCount(List<Row> list) {
        int count = 0;
        for (Row rowType : list) {
            if (rowType.getColumn().size() > count) {
                count = rowType.getColumn().size();
            }
        }
        return count;
    }
}
