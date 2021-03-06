package ch.bfh.ti.projekt1.sokoban.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import org.apache.log4j.Logger;

import ch.bfh.ti.projekt1.sokoban.controller.BoardController;
import ch.bfh.ti.projekt1.sokoban.core.EditorService;
import ch.bfh.ti.projekt1.sokoban.editor.LevelDimensionDialog;

/**
 * The window where a solution can be seen
 * 
 * @author marcoberger
 * @since 24/12/13 14:29
 */
public class SolutionView {
	private static final Logger LOG = Logger.getLogger(SolutionView.class);

	private JFrame frame;

	private JButton nextButton;

	private int currentPos;

	/**
	 * @param board
	 * @param pathValues
	 */
	public SolutionView(final BoardController board, String pathValues) {
		frame = new JFrame("Solution");
		nextButton = new JButton(">");
		frame.add(nextButton, BorderLayout.LINE_START);
		frame.add(nextButton, BorderLayout.LINE_END);
		currentPos = 0;
		pathValues = pathValues.substring(1, pathValues.length()-1);
		final String[] path = pathValues.split(", ");
		
		// What happens when the user clicks on the next field
		nextButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(currentPos<path.length){
				switch (path[currentPos]) {
				case "UP":
					board.moveUp();
					break;
				case "DOWN":
					board.moveDown();
					break;
				case "LEFT":
					board.moveLeft();
					break;
				case "RIGHT":
					board.moveRight();
					break;

				default:
					break;
				}
				currentPos++;
				}
			}
		});
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * @return JFrame
	 */
	public JFrame getFrame() {
		return frame;
	}
}
