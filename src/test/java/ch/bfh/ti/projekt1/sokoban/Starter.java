package ch.bfh.ti.projekt1.sokoban;

import javax.swing.JFrame;

import ch.bfh.ti.projekt1.sokoban.controller2.BoardController;
import ch.bfh.ti.projekt1.sokoban.model2.Board;
import ch.bfh.ti.projekt1.sokoban.model2.Diamond;
import ch.bfh.ti.projekt1.sokoban.model2.Field;
import ch.bfh.ti.projekt1.sokoban.model2.FieldState;
import ch.bfh.ti.projekt1.sokoban.model2.Wall;
import ch.bfh.ti.projekt1.sokoban.view2.BoardView;

/**
 * This class is only for testing purposes
 * 
 * @author svennyffenegger
 * 
 */
public class Starter {

	public static void main(String[] args) {
		System.out.println("Starting Demo");
		
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 300, 300);

		BoardController boardController = new BoardController();
		BoardView boardView = new BoardView(boardController);

		Board board = new Board();

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

		f1.setUpper(f4);
		f1.setLower(f5);
		f1.setLeft(f3);
		f1.setRight(f2);

		f5.setUpper(f1);
		f5.setLower(f6);
		f5.setLeft(f8);

		f6.setLeft(f7);
		f6.setUpper(f5);

		f7.setRight(f6);
		f7.setUpper(f8);

		f3.setRight(f1);
		f3.setLower(f8);

		f2.setLeft(f1);

		f4.setLower(f1);
		f4.setUpper(f9);

		f8.setUpper(f3);
		f8.setRight(f5);
		f8.setLower(f7);

		f9.setLower(f5);
		f9.setLeft(f11);
		f9.setRight(f10);

		f10.setLeft(f9);

		f11.setRight(f9);
		f11.setLeft(f12);

		f12.setRight(f11);

		board.setPosition(f1);

		boardController.addModel(board);
		boardController.addView(boardView);
		Diamond diamant = new Diamond();
		Wall wall = new Wall();
		diamant.setLocation(0,0);
		wall.setLocation(40,40);
		wall.initialize();
		diamant.initialize();
		frame.add(wall);
		boardView.add(diamant);
		//frame.add(boardView);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		boardView.requestFocusInWindow();
		frame.setVisible(true);

	}
}
