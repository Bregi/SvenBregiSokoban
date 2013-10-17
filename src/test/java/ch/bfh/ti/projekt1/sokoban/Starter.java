package ch.bfh.ti.projekt1.sokoban;

import ch.bfh.ti.projekt1.sokoban.controller2.BoardController;
import ch.bfh.ti.projekt1.sokoban.model2.Board;
import ch.bfh.ti.projekt1.sokoban.model2.Field;
import ch.bfh.ti.projekt1.sokoban.model2.FieldState;
import ch.bfh.ti.projekt1.sokoban.model2.Position;
import ch.bfh.ti.projekt1.sokoban.view2.BoardView;

import javax.swing.*;
import java.io.File;

/**
 * This class is only for testing purposes
 *
 * @author svennyffenegger
 */
public class Starter {

    public static void main(String[] args) {
        System.out.println("Starting Demo");

        File f = new File("ch/bfh/ti/projekt1/sokoban/test.xml");
        System.out.print(f.exists());
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 1000, 1000);
        Board board = new Board(4, 5, new Position(2, 2));

        BoardController boardController = new BoardController();
        BoardView boardView = new BoardView(boardController, board.getPosition());

        /**
         * //TODO: if a linking field is set, then automatically set linking on
         * the other field Verlinkung:
         *
         *f12 - f11 - f9 - f10
         * 		      f4
         * 		 f3 - f1 - f2
         * 		 f8 - f5
         * 		 f7 - f6
         *
         *
         */
        Field f1 = new Field(FieldState.PLAYER);
        Field f2 = new Field(FieldState.EMPTY);
        Field f3 = new Field(FieldState.EMPTY);
        Field f4 = new Field(FieldState.EMPTY);
        Field f5 = new Field(FieldState.EMPTY);
        Field f6 = new Field(FieldState.EMPTY);
        Field f7 = new Field(FieldState.EMPTY);
        Field f8 = new Field(FieldState.EMPTY);
        Field f9 = new Field(FieldState.EMPTY);
        Field f10 = new Field(FieldState.EMPTY);
        Field f11 = new Field(FieldState.EMPTY);
        Field f12 = new Field(FieldState.EMPTY);

        board.setField(0, 0, f12);
        board.setField(1, 0, f11);
        board.setField(2, 0, f9);
        board.setField(3, 0, f10);
        board.setField(2, 1, f4);
        board.setField(1, 2, f3);
        board.setField(2, 2, f1);
        board.setField(3, 2, f2);
        board.setField(1, 3, f8);
        board.setField(2, 3, f5);
        board.setField(1, 4, f7);
        board.setField(2, 4, f6);

        boardController.addModel(board);
        boardController.addView(boardView);

        frame.add(boardView);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardView.requestFocusInWindow();
        frame.setVisible(true);

    }
}
