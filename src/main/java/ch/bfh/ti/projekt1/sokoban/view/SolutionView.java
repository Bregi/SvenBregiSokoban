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
import ch.bfh.ti.projekt1.sokoban.core.EditorServiceImpl;
import ch.bfh.ti.projekt1.sokoban.editor.LevelDimensionDialog;
import ch.bfh.ti.projekt1.sokoban.model.Direction;

/**
 * @author marcoberger
 * @since 24/12/13 14:29
 */
public class SolutionView {
	private static final Logger LOG = Logger.getLogger(SolutionView.class);

	private JFrame frame;

	private JMenuBar menuBar;

	private JButton nextButton;

	private JButton backButton;
	private int currentPos;
	private JMenu menuFile;

	private JMenuItem menuFileNew;

	private JMenuItem menuFileSave;

	private JMenuItem menuFileLoad;

	// private EditorController controller;

	private EditorService editorService = new EditorServiceImpl();

	public SolutionView(final BoardController board, String pathValues) {
		frame = new JFrame("Solution");
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		nextButton = new JButton(">");
		backButton = new JButton("<");
		frame.add(nextButton, BorderLayout.LINE_START);
		frame.add(nextButton, BorderLayout.LINE_END);
		currentPos = 0;
		pathValues = pathValues.substring(1, pathValues.length()-1);
		final String[] path = pathValues.split(", ");
		

		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		menuFileLoad = new JMenuItem("Laden");
		menuFile.add(menuFileLoad);
		menuFileLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LevelDimensionDialog dialog = new LevelDimensionDialog();
				Dimension dim = dialog.showDimensionDialog(frame);

				if (dim != null) {
					frame.getContentPane().revalidate();
				}

			}
		});
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

	public JFrame getFrame() {
		return frame;
	}
}