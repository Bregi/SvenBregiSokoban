package ch.bfh.ti.projekt1.sokoban.controller;

import ch.bfh.ti.projekt1.sokoban.model.Board;
import ch.bfh.ti.projekt1.sokoban.model.Field;
import ch.bfh.ti.projekt1.sokoban.model.FieldState;
import ch.bfh.ti.projekt1.sokoban.model.Position;
import ch.bfh.ti.projekt1.sokoban.view.BoardView;
import ch.bfh.ti.projekt1.sokoban.xml.Column;
import ch.bfh.ti.projekt1.sokoban.xml.Level;
import ch.bfh.ti.projekt1.sokoban.xml.Row;
import ch.bfh.ti.projekt1.sokoban.xml.StartPosition;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author svennyffenegger
 * @since 23.10.13 13:58
 */
public class GameController {

	private String currentLevel;
	private Board gameBoard;
	private BoardController boardController;
	
    public BoardView loadLevel(File file) {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Level.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Level level = (Level) jaxbUnmarshaller.unmarshal(file);
            currentLevel = level.getName();
            System.out.println(level.getStartPosition());
            Position startPos = new Position(level.getStartPosition().getColumn(), level.getStartPosition().getRow());

            Board board = new Board(getMaxColumnCount(level.getRow()), level.getRow().size(), startPos);
            
            boardController = new BoardController();

            for (Row rowType : level.getRow()) {
                for (Column columnType : rowType.getColumn()) {

                    Field field = new Field(FieldState.parseXmlFieldType(columnType.getType()));
                    board.setField(columnType.getId(), rowType.getId(), field);
                }
            }
            board.setLevelName(level.getName());
            gameBoard = board;
            
            BoardView boardView = new BoardView(board, boardController, board.getPosition(), level.getName());

            boardController.addModel(board);
            boardController.addView(boardView);


            return boardView;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public BoardView loadLevel(String path) {
        File file = new File(path);
        return loadLevel(file);
    }
    /**
     * Gives the current gameboard
     * @return Board
     */
    public Board getBoard(){
    	return gameBoard;
    }
    
    /**
     * saves the current progress
     */
    public void saveLevelProgress(Board board){
    	//TODO
    	        Level level = new Level();

    	        Field[][] grid = board.getGrid();

    	        level.setName("Level" + board.getLevelName());

    	        //TODO
    	        StartPosition startPosition = new StartPosition();
    	        startPosition.setColumn(0);
    	        startPosition.setRow(0);

    	        level.setStartPosition(startPosition);

    	        //TODO ist noch verkehrt rum
    	        for (int i = 0; i < grid.length; i++) {
    	            Row row = new Row();
    	            row.setId(i);
    	            level.getRow().add(row);

    	            for (int j = 0; j < grid[i].length; j++) {
    	                Column column = new Column();
    	                column.setId(j);
    	                column.setType(FieldState.convertToXMLFieldType(grid[i][j].getState()));
    	                row.getColumn().add(column);
    	            }
    	        }

    	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    	        //TODO: Change the path and show a successfull save message
    	        File file = new File("src/test/resources/ch/bfh/ti/projekt1/sokoban/generated/" + simpleDateFormat.format(new Date()) + ".xml");
    	        try {
    	            JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
    	            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    	            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    	            jaxbMarshaller.marshal(level, file);
    	        } catch (JAXBException e) {
    	            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    	        }

    	    
    	System.out.println(currentLevel);
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
    public BoardController getBoardController(){
    	return boardController;
    }
}
